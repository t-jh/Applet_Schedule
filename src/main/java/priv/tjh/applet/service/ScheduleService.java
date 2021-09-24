package priv.tjh.applet.service;

import priv.tjh.applet.model.pojo.Schedule;
import priv.tjh.applet.model.vo.ScheduleVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

    List<Schedule> getPersonalSchedule(String xnxqdm, Integer zc);

    List<Schedule> getClassSchedule(String xnxqdm, Integer zc);

    List<ScheduleVO> getTodaySchedule(Date date);

    Map<Integer, Map<Integer, List<Integer>>> getNoSchedule();
}
