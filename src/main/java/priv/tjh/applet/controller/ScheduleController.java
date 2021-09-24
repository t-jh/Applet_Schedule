package priv.tjh.applet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.tjh.applet.common.ApiRestResponse;
import priv.tjh.applet.common.Constant;
import priv.tjh.applet.model.pojo.Schedule;
import priv.tjh.applet.model.vo.ScheduleVO;
import priv.tjh.applet.service.ScheduleService;
import priv.tjh.applet.service.impl.ScheduleImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/personalSchedule")
    public ApiRestResponse PersonalSchedule(@RequestParam("zc") Integer zc) {
        List<Schedule> scheduleList = scheduleService.getPersonalSchedule(Constant.xnxqdm, zc);
        return ApiRestResponse.success(scheduleList);
    }

    @GetMapping("/user/classSchedule")
    public ApiRestResponse classSchedule(@RequestParam("zc") Integer zc) {
        List<Schedule> scheduleList = scheduleService.getClassSchedule(Constant.xnxqdm, zc);
        return ApiRestResponse.success(scheduleList);
    }

    @GetMapping("/user/todaySchedule")
    public ApiRestResponse todaySchedule() {
        List<ScheduleVO> scheduleVOList = scheduleService.getTodaySchedule(new Date());
        return ApiRestResponse.success(scheduleVOList);
    }

    @GetMapping("/user/noSchedule")
    public ApiRestResponse noSchedule() {
        Map<Integer, Map<Integer, List<Integer>>> table = scheduleService.getNoSchedule();
        return ApiRestResponse.success(table);
    }

}
