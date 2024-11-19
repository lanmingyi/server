package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_user_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface tb_user_infoDao extends JpaRepository<tb_user_info, String>, JpaSpecificationExecutor<tb_user_info> {
    tb_user_info findByUserPk(String userPk);
}
