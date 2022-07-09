package com.xd.pre.modules.sys.mapper;

import com.xd.pre.modules.sys.domain.SysTenant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 租户表 Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-08-10
 */
public interface SysTenantMapper extends BaseMapper<SysTenant> {

    @Select("select id from sys_tenant\n" +
            "ORDER BY id DESC\n" +
            "LIMIT 1")
    int getLastId();


}
