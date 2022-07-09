package com.xd.pre.log.logDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Classname DictDTO
 * @Description 字典dto
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-06-02 09:36
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogDTO {


    private Integer id;

    private String userName;


    private String description;

    private String actionUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTime;

    }