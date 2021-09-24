package priv.tjh.applet.model.dao;

import org.springframework.stereotype.Repository;
import priv.tjh.applet.model.pojo.Announcement;

import java.util.List;

@Repository
public interface AnnouncementMapper {
    int deleteByPrimaryKey(Integer ggtzdm);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Integer ggtzdm);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);

    int deleteAll();

    List<Announcement> selectList();

    List<Announcement> selectBycjsj(Integer count);
}