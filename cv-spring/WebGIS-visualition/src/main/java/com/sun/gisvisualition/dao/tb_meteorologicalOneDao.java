package com.sun.gisvisualition.dao;


import com.sun.gisvisualition.entity.tb_meteorologicalOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface tb_meteorologicalOneDao extends JpaRepository<tb_meteorologicalOne, String>, JpaSpecificationExecutor<tb_meteorologicalOne> {
}
