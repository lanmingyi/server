package com.brightcomplete.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brightcomplete.modules.system.entity.SysAppBasics;
import com.brightcomplete.modules.system.mapper.SysAppBasicsMapper;
import com.brightcomplete.modules.system.service.SysAppBasicsService;
import com.brightcomplete.modules.system.vo.TaleDetailVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysAppBasicsServiceImpl extends ServiceImpl<SysAppBasicsMapper, SysAppBasics> implements SysAppBasicsService {

    @Resource
    private SysAppBasicsMapper sysAppBasicsMapper;

    @Override
    public List<TaleDetailVo> getColumnName(String basicsTableName, String basicsTableSchema) {
        return sysAppBasicsMapper.getColumnName(basicsTableName, basicsTableSchema);
    }
}
