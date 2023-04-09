package com.bright.modules.base.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.bright.common.api.dto.LogDTO;
import org.apache.ibatis.annotations.Param;

/**
 * BaseCommonMapper
 */
public interface BaseCommonMapper {

    /**
     * 保存日志
     * @param dto
     */
    @InterceptorIgnore(illegalSql = "true", tenantLine = "true")
    void saveLog(@Param("dto") LogDTO dto);

}
