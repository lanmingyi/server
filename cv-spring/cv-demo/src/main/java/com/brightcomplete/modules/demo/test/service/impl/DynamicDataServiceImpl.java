package com.brightcomplete.modules.demo.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brightcomplete.modules.demo.test.entity.Demo;
import com.brightcomplete.modules.demo.test.mapper.DemoMapper;
import com.brightcomplete.modules.demo.test.service.IDynamicDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 动态数据源测试
 * @author lmy
 * @Date:2020-04-21
 */
@Service
public class DynamicDataServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDynamicDataService {

    @Override
    public List<Demo> selectSpelByHeader() {
        return list();
    }

    @Override
    public List<Demo> selectSpelByKey(String dsName) {
        return list();
    }
}
