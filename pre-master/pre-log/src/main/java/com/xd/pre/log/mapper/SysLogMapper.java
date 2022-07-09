package com.xd.pre.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pre.log.domain.SysLog;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-27
 */
public interface SysLogMapper extends BaseMapper<SysLog> {
    @Select("select id from sys_log\n" +
            "where type=2\n" +
            "ORDER BY id")
    List<Integer> getLastId1();
    @Select("SELECT TIMESTAMPDIFF(SECOND,#{startTime},#{finishTime})\n")
    long getTimeDiff(LocalDateTime startTime,LocalDateTime finishTime);
}
