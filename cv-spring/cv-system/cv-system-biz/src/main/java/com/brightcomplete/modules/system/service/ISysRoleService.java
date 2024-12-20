package com.brightcomplete.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.modules.system.entity.SysRole;
import com.brightcomplete.poi.excel.entity.ImportParams;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @Author: lmy
 * @since 2018-12-19
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 导入 excel ，检查 roleCode 的唯一性
     *
     * @param file
     * @param params
     * @return
     * @throws Exception
     */
    Result importExcelCheckRoleCode(MultipartFile file, ImportParams params) throws Exception;

    /**
     * 删除角色
     * @param roleid
     * @return
     */
    public boolean deleteRole(String roleid);

    /**
     * 批量删除角色
     * @param roleids
     * @return
     */
    public boolean deleteBatchRole(String[] roleids);

}
