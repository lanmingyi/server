package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_soilNutrients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface tb_soilNutrientsDao extends JpaRepository<tb_soilNutrients, String>, JpaSpecificationExecutor<tb_soilNutrients> {

    @Query(value = "select a.date from tb_soil_nutrients a group by  a.date DESC having count(distinct (a.date))", nativeQuery = true)
    List getTime();

    @Query(value = "select u.name as name,u.url as url from tb_soil_nutrients u where u.date = ?1", nativeQuery = true)
    List<Map<String, Object>> getUrl(String date);
}