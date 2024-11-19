package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_2018_soil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface tb_2018_soilDao extends JpaRepository<tb_2018_soil, String>, JpaSpecificationExecutor<tb_2018_soil> {
}
