package com.brightcomplete.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brightcomplete.modules.system.entity.SysAppBasics;
import com.brightcomplete.modules.system.vo.TaleDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAppBasicsMapper extends BaseMapper <SysAppBasics> {

    List<TaleDetailVo> getColumnName(@Param("basicsTableName")String basicsTableName, @Param("basicsTableSchema") String basicsTableSchema);
}
