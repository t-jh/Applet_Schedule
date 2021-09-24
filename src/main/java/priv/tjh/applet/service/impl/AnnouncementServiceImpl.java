package priv.tjh.applet.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import priv.tjh.applet.common.ApiRestResponse;
import priv.tjh.applet.common.Constant;
import priv.tjh.applet.exception.AppletException;
import priv.tjh.applet.exception.AppletExceptionEnum;
import priv.tjh.applet.model.dao.AnnouncementMapper;
import priv.tjh.applet.model.pojo.Announcement;
import priv.tjh.applet.service.AnnouncementService;
import sun.applet.AppletIllegalArgumentException;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.locks.Condition;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    AnnouncementMapper announcementMapper;

    private boolean unLinkStatus = false;

    @Override
    public List<Announcement> getAnnouncement(Integer count) {
        update();
        List<Announcement> announcementList = announcementList(count);
        return announcementList;
    }

    private void update() {
        unLinkStatus = false;
        List<Announcement> announcementListNew = announcementList(0);
        List<Announcement> announcementListOld = announcementMapper.selectList();
        if (announcementListNew.size() != announcementListOld.size() && !unLinkStatus) {
            insert(announcementListOld, announcementListNew);
        }
        for (Announcement announcement : announcementListNew) {
            announcementMapper.updateByPrimaryKeySelective(announcement);
        }
    }

    private List<Announcement> announcementList(Integer count) {
        HttpClient httpClient = Constant.httpClient;
        HttpGet httpGet = new HttpGet("获取通知公告请求");
        List<Announcement> announcementList = null;
        try {
            HttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
            } catch (UnknownHostException e) {
                List<Announcement> announcements = announcementMapper.selectBycjsj(count);
                unLinkStatus = true;
                return announcements;
            }
            String json = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            try {
                announcementList = new ObjectMapper().readValue(json, new TypeReference<List<Announcement>>() {
                });
            } catch (JsonProcessingException e) {
                throw new AppletException(AppletExceptionEnum.NEED_LOGIN);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(announcementList)) {
            throw new AppletException(AppletExceptionEnum.ACQUIRE_FAILED);
        }
        if (count != 0) {
            List<Announcement> announcements = announcementMapper.selectBycjsj(count);
            return announcements;
        }
        return announcementList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(List<Announcement> announcementListOld, List<Announcement> announcementListNew) {
        int i = announcementMapper.deleteAll();
        if (announcementListOld.size() != 0 && i == 0) {
            throw new AppletException(AppletExceptionEnum.DELETE_FAILED);
        }
        for (Announcement announcement : announcementListNew) {
            int i1 = announcementMapper.insertSelective(announcement);
            if (i1 == 0) {
                throw new AppletException(AppletExceptionEnum.SAVE_FAILED);
            }
        }
    }
}
