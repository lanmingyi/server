package com.brightcomplete.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brightcomplete.modules.system.entity.SysAppDetailed;
import com.brightcomplete.modules.system.mapper.SysAppDetailedMapper;
import com.brightcomplete.modules.system.service.SysAppDetailedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysAppDetailedServiceImpl")
public class SysAppDetailedServiceImpl extends ServiceImpl<SysAppDetailedMapper,SysAppDetailed> implements SysAppDetailedService{
    @Autowired
    private SysAppDetailedMapper sysAppDetailedMapper;

    @Override
    public SysAppDetailed getDetailByBasicsUuid(String uuid) {
        return sysAppDetailedMapper.getDetailByBasicsUuid(uuid);
    }
}
