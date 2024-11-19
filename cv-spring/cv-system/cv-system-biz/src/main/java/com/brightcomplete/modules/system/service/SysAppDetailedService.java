package com.brightcomplete.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brightcomplete.modules.system.entity.SysAppDetailed;

public interface SysAppDetailedService extends IService<SysAppDetailed> {
    //根据uuid查询详情
    SysAppDetailed getDetailByBasicsUuid(String uuid);
}
