package priv.tjh.applet.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import priv.tjh.applet.common.Constant;
import priv.tjh.applet.exception.AppletException;
import priv.tjh.applet.exception.AppletExceptionEnum;
import priv.tjh.applet.model.dao.ScheduleMapper;
import priv.tjh.applet.model.pojo.Schedule;
import priv.tjh.applet.model.vo.ScheduleVO;
import priv.tjh.applet.service.ScheduleService;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScheduleImpl implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    private boolean unLinkStatus = false;

    @Override
    public List<Schedule> getPersonalSchedule(String xnxqdm, Integer zc) {
//        System.out.println("xnxqdm-->" + xnxqdm);
        update(xnxqdm);
        List<Schedule> scheduleList = scheduleList(xnxqdm, zc);
        return scheduleList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(String xnxqdm) {
        unLinkStatus = false;
        List<Schedule> scheduleAllListNew = scheduleList(xnxqdm, 0);
        List<Schedule> scheduleListOld = scheduleMapper.selectByxnxqdm(xnxqdm);
        if (scheduleAllListNew.size() != scheduleListOld.size() && !unLinkStatus) {
            insert(xnxqdm, scheduleAllListNew, scheduleListOld);
        }
        for (Schedule schedule : scheduleAllListNew) {
            scheduleMapper.updateByPrimaryKeySelective(schedule);
        }
    }

    private List<Schedule> scheduleList(String xnxqdm, Integer zc) {
        HttpClient httpClient = Constant.httpClient;
        String url = "获取课表请求地址" + xnxqdm + "第几周";
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;
        String resultJson = null;
        try {
            try {
                response = httpClient.execute(httpGet);
            } catch (UnknownHostException e) {
                List<Schedule> UnLinkScheduleList = scheduleMapper.selectByzc(xnxqdm, zc);
                unLinkStatus = true;
                return UnLinkScheduleList;
            }
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new AppletException(AppletExceptionEnum.ERROR_LINK);
            }
            resultJson = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("json字符串" + resultJson);
        String substring = resultJson.substring(1, resultJson.lastIndexOf(","));
//        System.out.println("格式后的字符串：" + substring);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Schedule> scheduleList = null;
        try {
            scheduleList = objectMapper.readValue(substring, new TypeReference<List<Schedule>>() {
            });
        } catch (JsonProcessingException e) {
            throw new AppletException(AppletExceptionEnum.NEED_LOGIN);
        }
        if (zc != 0) {
            List<Schedule> schedules = scheduleMapper.selectByzc(xnxqdm, zc);
            return schedules;
        }
        return scheduleList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(String xnxqdm, List<Schedule> scheduleList, List<Schedule> scheduleListOld) {
        int deleteCount = scheduleMapper.deleteByxnxqdm(xnxqdm);
        if (scheduleListOld.size() != 0 && deleteCount == 0) {
            throw new AppletException(AppletExceptionEnum.DELETE_FAILED);
        }
        for (Schedule schedule : scheduleList) {
            int i = scheduleMapper.insertSelective(schedule);
            if (i == 0) {
                throw new AppletException(AppletExceptionEnum.SAVE_FAILED);
            }
        }

    }


    @Override
    public List<Schedule> getClassSchedule(String xnxqdm, Integer zc) {
        HttpClient httpClient = Constant.httpClient;
        HttpGet httpGet = new HttpGet("获取班级课表请求页面");
        HttpResponse response = null;
        String bjdmPage = null;
        try {
            response = httpClient.execute(httpGet);
            bjdmPage = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = Jsoup.parse(bjdmPage);
        String bjdm = "";
        Elements select = document.select("#bjdm > option");
        for (Element element : select) {
            if (element.hasAttr("selected")) {
                bjdm = element.val();
            }
        }
        HttpGet classScheduleUrl = new HttpGet("获取班级课表请求" + xnxqdm + "&zc=" + zc + "&bjdm=" + bjdm);
        List<Schedule> scheduleList = null;
        try {
            HttpResponse classScheduleResponse = httpClient.execute(classScheduleUrl);
            String classScheduleJsonString = EntityUtils.toString(classScheduleResponse.getEntity(), Consts.UTF_8);
//            System.out.println("原始json字符串——>" + classScheduleJsonString);
            String substring = classScheduleJsonString.substring(1, classScheduleJsonString.lastIndexOf("["));
            String jsonString = substring.substring(0, substring.lastIndexOf(","));
//            System.out.println("——>" + jsonString);
            ObjectMapper objectMapper = new ObjectMapper();
            scheduleList = objectMapper.readValue(jsonString, new TypeReference<List<Schedule>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(scheduleList)) {
            throw new AppletException(AppletExceptionEnum.ACQUIRE_FAILED);
        }
        return scheduleList;
    }


    @Override
    public List<ScheduleVO> getTodaySchedule(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String rq = sdf.format(date);
        HttpGet httpGet = new HttpGet("获取今日课表的请求" + rq);
        HttpClient httpClient = Constant.httpClient;
        List<ScheduleVO> scheduleVOList = null;
        try {
            String todayScheduleJson = EntityUtils.toString(httpClient.execute(httpGet).getEntity(), Consts.UTF_8);
            scheduleVOList = new ObjectMapper().readValue(todayScheduleJson, new TypeReference<List<ScheduleVO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(scheduleVOList)) {
            throw new AppletException(AppletExceptionEnum.ACQUIRE_FAILED);
        }
        return scheduleVOList;
    }

    @Override
    public Map<Integer, Map<Integer, List<Integer>>> getNoSchedule() {
        Map<Integer, Integer> jcdmMap = new HashMap<>();
        jcdmMap.put(1, 102);
        jcdmMap.put(2, 304);
        jcdmMap.put(3, 506);
        jcdmMap.put(4, 708);
        jcdmMap.put(5, 910);
        Map<Integer, Map<Integer, List<Integer>>> table = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            Map<Integer, List<Integer>> jcdmTozcNoLesson = new HashMap<>();
            for (int j = 1; j <= 5; j++) {
                List<Integer> zcTable = scheduleMapper.table(i, jcdmMap.get(j));
                List<Integer> zcNoLesson = new ArrayList<>();
                for (int k = 1; k <= 17; k++) {
                    if (!zcTable.contains(k)) {
                        zcNoLesson.add(k);
                    }
                }
                jcdmTozcNoLesson.put(j, zcNoLesson);
            }
            table.put(i, jcdmTozcNoLesson);
        }
//        System.out.println(table);
        return table;
    }

}
