package priv.tjh.applet.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.tjh.applet.common.ApiRestResponse;
import priv.tjh.applet.common.Constant;
import priv.tjh.applet.model.vo.StudyPlanVO;
import priv.tjh.applet.model.vo.TrainingProgramVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TrainingProgramController {

    @PostMapping("/user/trainingProgram")
    public ApiRestResponse trainingProgram(@RequestParam("nd") String nd, @RequestParam("yxdm") String yxdm
            , @RequestParam("zydm") String zydm, @RequestParam("jhlx") String jhlx, @RequestParam("bbmc") String bbmc
            , @RequestParam("page") String page, @RequestParam("rows") String rows) {
        List<TrainingProgramVO> trainingProgramVOList = getTrainingProgram(nd, yxdm, zydm, jhlx, bbmc, page, rows);
        return ApiRestResponse.success(trainingProgramVOList);
    }

    private List<TrainingProgramVO> getTrainingProgram(String nd, String yxdm, String zydm, String jhlx, String bbmc
            , String page, String rows) {
        HttpClient httpClient = Constant.httpClient;
        HttpPost httpPost = new HttpPost("培养方案页面");
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("nd", nd));
        nvps.add(new BasicNameValuePair("yxdm", yxdm));
        nvps.add(new BasicNameValuePair("zydm", zydm));
        nvps.add(new BasicNameValuePair("jhlx", jhlx));
        nvps.add(new BasicNameValuePair("bbmc", bbmc));
        nvps.add(new BasicNameValuePair("page", page));
        nvps.add(new BasicNameValuePair("rows", rows));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        List<TrainingProgramVO> trainingProgramVOList = null;
        try {
            HttpResponse response = httpClient.execute(httpPost);
            String jxjhdmJson = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            String jxjhdm = jxjhdmJson.substring(30, 37);
//            System.out.println("-->" + jxjhdm);
            HttpPost trainingProgramRequest = new HttpPost("获取培养方案请求" + jxjhdm);
            List<NameValuePair> trainingProgramList = new ArrayList<>();
            trainingProgramList.add(new BasicNameValuePair("primarySort", "rwdm asc"));
            trainingProgramList.add(new BasicNameValuePair("page", page));
            trainingProgramList.add(new BasicNameValuePair("rows", rows));
            trainingProgramList.add(new BasicNameValuePair("sort", "kkxqmc1"));
            trainingProgramList.add(new BasicNameValuePair("order", "asc"));
            trainingProgramRequest.setEntity(new UrlEncodedFormEntity(trainingProgramList, Consts.UTF_8));
            HttpResponse trainingProgramResponse = httpClient.execute(trainingProgramRequest);
            String trainingProgramJson = EntityUtils.toString(trainingProgramResponse.getEntity(), Consts.UTF_8);
            String substring = trainingProgramJson.substring(trainingProgramJson.indexOf("["), trainingProgramJson.lastIndexOf("]") + 1);
            trainingProgramVOList = new ObjectMapper().readValue(substring, new TypeReference<List<TrainingProgramVO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trainingProgramVOList;
    }

    @PostMapping("/user/studyPlan")
    public ApiRestResponse studyPlan(@RequestParam("sydx") Integer sydx, @RequestParam("page") String page, @RequestParam("rows") String rows) {
        List<StudyPlanVO> studyPlanVOList = getStudyPlan(sydx, page, rows);
        return ApiRestResponse.success(studyPlanVOList);
    }

    private List<StudyPlanVO> getStudyPlan(Integer sydx, String page, String rows) {
        HttpClient httpClient = Constant.httpClient;
        HttpPost httpPost = new HttpPost("获取学习计划页面的参数请求");
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("page", page));
        nvps.add(new BasicNameValuePair("rows", rows));
        nvps.add(new BasicNameValuePair("sort", "jxjhbh"));
        nvps.add(new BasicNameValuePair("order", "asc"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        String jxjhdm = null;
        try {
            HttpResponse response = httpClient.execute(httpPost);
            String json = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            String substring = json.substring(json.indexOf("["), json.lastIndexOf("]"));
//            System.out.println("-->" + substring);
            if (sydx == 0) {
                jxjhdm = substring.substring(substring.indexOf(":") + 2, 19);
//                System.out.println(jxjhdm);
            } else if (sydx == 1) {
                jxjhdm = substring.substring(substring.substring(2).indexOf("{") + 13, substring.substring(2).indexOf("{") + 20);
//                System.out.println(jxjhdm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<StudyPlanVO> studyPlanVOList = common(httpClient, jxjhdm, page, rows);
        return studyPlanVOList;
    }

    private List<StudyPlanVO> common(HttpClient httpClient, String jxjhdm, String page, String rows) {
        HttpPost httpPost = new HttpPost("获取学习计划请求" + jxjhdm);
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("primarySort", "rwdm asc"));
        nvps.add(new BasicNameValuePair("page", page));
        nvps.add(new BasicNameValuePair("rows", rows));
        nvps.add(new BasicNameValuePair("sort", "kkxqmc1"));
        nvps.add(new BasicNameValuePair("order", "asc"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        List<StudyPlanVO> studyPlanVOList = null;
        try {
            HttpResponse response = httpClient.execute(httpPost);
            String jsonString = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            String substring = jsonString.substring(jsonString.indexOf("["), jsonString.lastIndexOf("]") + 1);
//            System.out.println("-->" + substring);
            studyPlanVOList = new ObjectMapper().readValue(substring, new TypeReference<List<StudyPlanVO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studyPlanVOList;
    }
}
