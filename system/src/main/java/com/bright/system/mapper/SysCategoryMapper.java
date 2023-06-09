package com.bright.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bright.system.entity.SysCategory;
import com.bright.system.model.TreeSelectModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description: 分类字典
 */
public interface SysCategoryMapper extends BaseMapper<SysCategory> {
	
	/**
	 * 根据父级ID查询树节点数据
	 * @param pid
     * @param query
	 * @return
	 */
	public List<TreeSelectModel> queryListByPid(@Param("pid")  String pid,@Param("query") Map<String, String> query);

    /**
     * 通过code查询分类字典表
     * @param code
     * @return
     */
	@Select("SELECT ID FROM sys_category WHERE CODE = #{code,jdbcType=VARCHAR}")
	public String queryIdByCode(@Param("code")  String code);
	

}
