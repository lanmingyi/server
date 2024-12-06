package com.bright.system.mapper;

//import com.bright.system.bean.CommonMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bright.system.model.*;
import com.bright.system.vo.AppMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lmy
 * @email
 * @date 2018-06-26 10:50:51
 */


public interface AuthAccessViewMapper extends BaseMapper<AuthAccessView> {



//    //获取分页集
//    List<Config> getPageSet(@Param("filterSort") String filterSort);
//
//    int executeSave(SysFdField var1);
//
//    int executeSave(Map<String, Object> var1);
//
//    //void executeSaveBatch(List<SysFdField> var1);
//
//    int executeUpdate(SysFdField var1);
//
//    int executeUpdate(Map<String, Object> var1);
//
//    int executeDelete(Object var1);
//
//    int executeDelete(Map<String, Object> var1);
//
//    int executeDeleteBatch(Object[] var1);
//
//    SysFdField queryObject(Object var1);
//
//    List<SysFdField> queryList(Map<String, Object> var1);
//
//    List<SysFdField> queryList(Object var1);
//
//    int queryTotal(Map<String, Object> var1);
//
//    int queryTotal();


    List<AuthAccessView> getPageSet(@Param("filterSort") String filterSort);

    List<Map<String,Object>> getListByJdbc(@Param("list") List<String> list, @Param("codeSetId") String codeSetId, @Param("levelId") String levelId, @Param("menuId") String menuId);

    List<Map<String,Object>> getListByRoleIdAndCodeSetIdAndMenuId2(@Param("list") List<String> list, @Param("codeSetId") String codeSetId, @Param("menuId") String menuId);

    List<AuthAccessView> getListByRoleId(@Param("list") List<String> list);

    List<Map<String,Object>> getListByRoleIdAndPid(@Param("list") List<String> list, @Param("pid") String pid);

    List<AuthAccessView> getListByRoleIdAndCodeSetIdAndLevelId3(@Param("list") List<String> list, @Param("codeSetId") String codeSetId, @Param("levelId") String levelId);

    List<AuthAccess> getListByRoleIdAllMenu(@Param("roleId") String roleId, @Param("roleType")String roleType);

    UserInfo getUserInForAndButtonPermissions(@Param("userNameId") String userNameId);

    List<Permissions> getPermissions(Object[] var1);

    List<ActionEntitySet>  getActionEntitySet(Object[] var1);

    List<AppMenuVo> getListAppMenuByRoleId(String[] split);

    List<AuthAccess> getListByRoleIdAllMenuData(@Param("roleId") String roleId,@Param("type")  String type);

    List<AuthAccess> getListByRoleIdAllMenuAndUuid(@Param("roleId") String roleId,@Param("app")  String app,@Param("uuid")  String uuid);
}
