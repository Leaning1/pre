package com.xd.pre.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pre.log.domain.SysLog;
import com.xd.pre.log.logDTO.LogDTO;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-27
 */
public interface ISysLogService extends IService<SysLog> {


    List<Integer> getLastId1();


    /**
     * 分页查询日志
     * @param page
     * @param pageSize
     * @param type
     * @return
     */
    IPage<SysLog> selectLogList(Integer page, Integer pageSize, Integer type, String userName);

    /**
     * 修改字典
     * @param logDto
     * @return
     */
    boolean updateLog(LogDTO logDto);

    long getTimeDiff(LocalDateTime startTime, LocalDateTime finishTime);

}
