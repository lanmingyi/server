package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface tb_cropDao extends JpaRepository<tb_crop, String>, JpaSpecificationExecutor<tb_crop> {
}
