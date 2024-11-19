package com.brightcomplete.common.system.base.service.impl;

import com.brightcomplete.common.system.base.entity.BaseEntity;
import com.brightcomplete.common.system.base.service.BaseService;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: ServiceImpl基类
 * @Author: lmy
 * @Date: 2022-11-18
 * @Version: 1.0
 */
@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

}
