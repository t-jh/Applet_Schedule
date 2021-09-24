package priv.tjh.applet.service;

import priv.tjh.applet.model.pojo.Announcement;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> getAnnouncement(Integer count);
}
