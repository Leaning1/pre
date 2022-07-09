package com.xd.pre.log.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.pre.log.domain.SysLog;
import com.xd.pre.log.logDTO.LogDTO;
import com.xd.pre.log.mapper.SysLogMapper;
import com.xd.pre.log.service.ISysLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-27
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public IPage<SysLog> selectLogList(Integer page, Integer pageSize, Integer type, String userName) {
        Page<SysLog> logPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<SysLog> sysLogLambdaQueryWrapper ;
        if(type==0) {

             sysLogLambdaQueryWrapper = Wrappers.<SysLog>lambdaQuery().ne(SysLog::getType, type).orderByDesc(SysLog::getStartTime);

        }
        else {

            sysLogLambdaQueryWrapper = Wrappers.<SysLog>lambdaQuery().eq(SysLog::getType, type).orderByDesc(SysLog::getStartTime);
        }
        if (StrUtil.isNotEmpty(userName)) {
            sysLogLambdaQueryWrapper.like(SysLog::getUserName, userName);
        }
        return baseMapper.selectPage(logPage, sysLogLambdaQueryWrapper);
    }
    @Override
    public List<Integer> getLastId1(){
        return baseMapper.getLastId1();
    }


    public   long getTimeDiff(LocalDateTime startTime, LocalDateTime finishTime){
        return baseMapper.getTimeDiff(startTime,finishTime);
    }
    @Transactional(rollbackFor = Exception.class)

    public boolean updateLog(LogDTO logDto) {
        SysLog sysLog = new SysLog();
        LocalDateTime st=logDto.getStartTime();
        LocalDateTime ft=logDto.getFinishTime();
        sysLog.setConsumingTime(getTimeDiff(st,ft));
        BeanUtil.copyProperties(logDto, sysLog);
        return updateById(sysLog);
    }


}
