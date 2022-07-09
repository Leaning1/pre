package com.xd.pre.log.controller;


import com.xd.pre.log.aspect.annotation.SysOperaLog;
import com.xd.pre.log.domain.SysLog;
import com.xd.pre.log.logDTO.LogDTO;
import com.xd.pre.log.service.ISysLogService;
import com.xd.pre.common.utils.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-27
 */
@RestController
@RequestMapping("/log")
public class SysLogController {

    @Resource
    private ISysLogService logService;

    /**
     *
     * 分页查询日志列表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('sys:log:view')")
    public R selectLog(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestParam("type") Integer type, @RequestParam String userName){
        return R.ok(logService.selectLogList(page, pageSize,type,userName));
    }


    @SysOperaLog(descrption = "删除日志")
    @DeleteMapping("/{logId}")
    @PreAuthorize("hasAuthority('sys:log:delete')")
    public R delete(@PathVariable("logId") Integer logId) {
        return R.ok(logService.removeById(logId));
    }


    @SysOperaLog(descrption = "删除所有的异常日志")
    @DeleteMapping("/deleteLast")
    @PreAuthorize("hasAuthority('sys:log:delete')")
    public R removeLast() {
        List<Integer> id=logService.getLastId1();
        return R.ok(logService.removeByIds(id));
    }


    /**
     * 添加日志信息
     *
     * @param sysLog
     * @return
     */
    @SysOperaLog(descrption = "添加日志信息")
    @PreAuthorize("hasAuthority('sys:dict:add')")
    @PostMapping
    public R add(@RequestBody SysLog sysLog) {
        sysLog.setType(2);
        sysLog.setIp("127.0.0.1");
        sysLog.setLocation("0|0|0|内网IP|内网IP");
        sysLog.setOs("Windows 10 or Windows Server 2016");
        sysLog.setBrowser("Chrome");
        sysLog.setRequestMethod("POST");
        LocalDateTime st=sysLog.getStartTime();
        LocalDateTime ft=sysLog.getFinishTime();
        sysLog.setConsumingTime(logService.getTimeDiff(st,ft));
        return R.ok(logService.save(sysLog));
    }



    /**
     * 更新日志
     *
     * @param logDto
     * @return
     */
    @SysOperaLog(descrption = "更新日志")
    @PreAuthorize("hasAuthority('sys:dict:edit')")
    @PutMapping
    public R update(@RequestBody LogDTO logDto) {

        return R.ok(logService.updateLog(logDto));
    }



}

