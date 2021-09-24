package priv.tjh.applet.model.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import priv.tjh.applet.model.pojo.Schedule;

import java.util.List;

@Repository
public interface ScheduleMapper {
    int deleteByPrimaryKey(Integer dgksdm);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(Integer dgksdm);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);

    int deleteByxnxqdm(@Param("xnxqdm") String xnxqdm);

    List<Schedule> selectByxnxqdm(@Param("xnxqdm") String xnxqdm);

    List<Schedule> selectByzc(@Param("xnxqdm") String xnxqdm, @Param("zc") Integer zc);

    List<Integer> table(@Param("xq") Integer xq, @Param("jcdm") Integer jcdm);
}