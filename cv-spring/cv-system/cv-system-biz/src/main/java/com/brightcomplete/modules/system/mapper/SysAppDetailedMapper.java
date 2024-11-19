package com.brightcomplete.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brightcomplete.modules.system.entity.SysAppDetailed;
import org.apache.ibatis.annotations.Param;

public interface SysAppDetailedMapper extends BaseMapper<SysAppDetailed> {
    SysAppDetailed getDetailByBasicsUuid(@Param("basicsUuid" ) String basicsUuid);
}
