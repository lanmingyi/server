package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_remote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface tb_remoteDao extends JpaRepository<tb_remote, String>, JpaSpecificationExecutor<tb_remote> {

   @Query(nativeQuery = true,value = "select a.date,a.pk from tb_remote a where a.active=true order by a.date ASC ")
    List getAllRemote();
}
