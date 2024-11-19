package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_remote_url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface tb_remote_urlDao extends JpaRepository<tb_remote_url,String>, JpaSpecificationExecutor<tb_remote_url> {
}
