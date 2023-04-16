package com.bright.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import com.bright.message.entity.SysMessageTemplate;

import java.util.List;

/**
 * @Description: 消息模板
 */
public interface SysMessageTemplateMapper extends BaseMapper<SysMessageTemplate> {

    /**
     * 通过模板CODE查询消息模板
     * @param code 模板CODE
     * @return List<SysMessageTemplate>
     */
    @Select("SELECT * FROM SYS_SMS_TEMPLATE WHERE TEMPLATE_CODE = #{code}")
    List<SysMessageTemplate> selectByCode(String code);
}
