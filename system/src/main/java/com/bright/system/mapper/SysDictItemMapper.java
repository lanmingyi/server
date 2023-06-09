package com.bright.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bright.system.entity.SysDictItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 */
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {

    /**
     * 通过字典id查询字典项
     * @param mainId 字典id
     * @return
     */
    @Select("SELECT * FROM sys_dict_item WHERE DICT_ID = #{mainId} order by sort_order asc, item_value asc")
    public List<SysDictItem> selectItemsByMainId(String mainId);
}
