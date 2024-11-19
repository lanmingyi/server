package com.brightcomplete.modules.system.service.impl;
import javax.annotation.Resource;

import com.brightcomplete.modules.system.entity.SysAppClassification;
import com.brightcomplete.modules.system.mapper.SysAppClassificationMapper;
import com.brightcomplete.modules.system.service.SysAppClassificationService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class SysAppClassificationServiceImpl extends ServiceImpl<SysAppClassificationMapper, SysAppClassification> implements SysAppClassificationService {


    @Resource
    private SysAppClassificationMapper sysAppClassificationMapper;

//    //分页
//    @Override
//    public PageSet<SysAppClassification> getPageSet(PageParam pageParam, String filterSort) {
//        PageHelper.startPage(pageParam.getPageNo(), pageParam.getPageSize());
//        List<SysAppClassification> list = sysAppClassificationMapper.getPageSet(filterSort);
//        PageInfo<SysAppClassification> pageInfo = new PageInfo<>(list);
//        return PageUtils.getPageSet(pageInfo);
//    }
//
//    //删除
//    @Override
//    public int executeDeleteBatch(String[] uuid) {
//        return sysAppClassificationMapper.executeDeleteBatch(uuid);
//    }


}
