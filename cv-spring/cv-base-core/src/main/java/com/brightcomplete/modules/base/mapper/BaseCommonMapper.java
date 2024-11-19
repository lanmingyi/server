package com.brightcomplete.modules.base.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.brightcomplete.common.api.dto.LogDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: BaseCommonMapper
 * @author lmy
 */
public interface BaseCommonMapper {

    /**
     * 保存日志
     * @param dto
     */
    @InterceptorIgnore(illegalSql = "true", tenantLine = "true")
    void saveLog(@Param("dto") LogDTO dto);

}
