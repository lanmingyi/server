package com.bright.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bright.system.entity.SysThirdAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 第三方登录账号表
 */
public interface SysThirdAccountMapper extends BaseMapper<SysThirdAccount> {

    /**
     * 通过 sysUsername 集合批量查询
     *
     * @param sysUsernameArr username集合
     * @param thirdType       第三方类型
     * @return
     */
    List<SysThirdAccount> selectThirdIdsByUsername(@Param("sysUsernameArr") String[] sysUsernameArr, @Param("thirdType") String thirdType);

}
