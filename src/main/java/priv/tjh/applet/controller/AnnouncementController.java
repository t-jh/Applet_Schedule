package priv.tjh.applet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.tjh.applet.common.ApiRestResponse;
import priv.tjh.applet.model.pojo.Announcement;
import priv.tjh.applet.service.AnnouncementService;

import java.util.List;

@RestController
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @GetMapping("/announcement")
    public ApiRestResponse announcement(@RequestParam("count") Integer count) {
        List<Announcement> announcementList = announcementService.getAnnouncement(count);
        return ApiRestResponse.success(announcementList);
    }
}
