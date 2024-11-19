package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface tb_authorityDao extends JpaRepository<tb_authority, String>, JpaSpecificationExecutor<tb_authority> {
    @Query(value = "select a.* from tb_authority a where a.authority= ?1 or  a.authority= ?2",nativeQuery = true)
    List<tb_authority> getPk(String authority, String authority2);

    tb_authority findByAuthority(String authority);
}
