package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_meteorological;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface tb_meteorologicalDao extends JpaRepository<tb_meteorological, String>, JpaSpecificationExecutor<tb_meteorological> {

    @Query(value = "select a.*,b.latitude,b.longitude from tb_meteorological a inner join tb_village b on a.cjqydm=b.cjqydm where a.date= ?1",nativeQuery = true)
    List<tb_meteorological> get(String date);

    //关联表查询所有数据
    @Query(value = "select a.*,b.latitude,b.longitude from tb_meteorological a inner join tb_village b on a.cjqydm=b.cjqydm",nativeQuery = true)
    List<tb_meteorological> getAll();
}
