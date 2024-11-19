package com.brightcomplete.modules.system.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brightcomplete.common.system.base.entity.BaseEntity;
import com.brightcomplete.modules.system.service.SystemBaseService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 基础服务实现
 * ============================================================================
 * @author  lmy
 * @date    2022-11-18
 * @version 1.0
 * ============================================================================
 */

@Service("systemBaseServiceImpl")
public abstract class SystemBaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements SystemBaseService<T> {

}
