package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface tb_userDao extends JpaRepository<tb_user, String>, JpaSpecificationExecutor<tb_user> {

    tb_user findByUsernameAndPassword(String username, String password);

    tb_user findByPhoneAndPassword(String phone, String password);

    int countByPhone(String phone);

    int countByUsername(String username);
}
