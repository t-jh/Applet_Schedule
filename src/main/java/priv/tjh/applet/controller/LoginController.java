package priv.tjh.applet.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.tjh.applet.common.ApiRestResponse;
import priv.tjh.applet.common.Constant;
import priv.tjh.applet.exception.AppletException;
import priv.tjh.applet.exception.AppletExceptionEnum;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class LoginController {

    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestParam("account") String account, @RequestParam("pwd") String pwd) {
        HttpClient httpClient = Constant.httpClient;
        HttpResponse response;
        String result = "";
        String verifycode = getVerifyCode(httpClient);
        HttpPost httpPost = new HttpPost("发送登录的post请求");
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("account", account));
        String encodePwd = Base64.getEncoder().encodeToString(pwd.getBytes(StandardCharsets.UTF_8));
        nvps.add(new BasicNameValuePair("pwd", encodePwd));
        nvps.add(new BasicNameValuePair("verifycode", verifycode));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        Map<String, String> resultMap = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
//            System.out.println("-->" + result);
            ObjectMapper objectMapper = new ObjectMapper();
            resultMap = objectMapper.readValue(result, new TypeReference<Map<String, String>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resultMap.get("status").equals("n")) {
            return ApiRestResponse.error(10004, resultMap.get("msg"));
        }
        Constant.checkLogin = true;
        return ApiRestResponse.success();
    }

    private String getVerifyCode(HttpClient httpClient) {
        HttpGet loginPage = new HttpGet("登录的页面");
        HttpResponse response;
        FileOutputStream fileOutputStream = null;
        String verifycode = "";
        try {
            httpClient.execute(loginPage);
            HttpGet httpGet = new HttpGet("验证码的地址");
            response = httpClient.execute(httpGet);
            UUID uuid = UUID.randomUUID();
            String verifycodeFileName = uuid.toString() + ".jpg";
            fileOutputStream = new FileOutputStream(new File(Constant.FILE_UPLOAD_DIR + verifycodeFileName));
            if (fileOutputStream == null) {
                throw new AppletException(AppletExceptionEnum.MKDIR_FAILED);
            }
            response.getEntity().writeTo(fileOutputStream);

            //verifyCode() 方法是用于验证码识别的，来自第三方的Api接口调用。
            //可以直接在百度上面找验证码打码平台即可，这里就不一一列举了，都是简单调用一些API即可。
//            verifycode = verifyCode(verifycodeFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (verifycode == null) {
            throw new AppletException(AppletExceptionEnum.NOT_VERIFYCODE);
        }
        return verifycode;
    }


}
