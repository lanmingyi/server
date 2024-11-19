package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_villagePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface tb_villagePointDao extends JpaRepository<tb_villagePoint, String>, JpaSpecificationExecutor<tb_villagePoint> {


    @Query(value = "select a.pk,a.latitude,a.longitude,a.name from tb_village a", nativeQuery = true)
    List<tb_villagePoint> getAllVillagePoint();
}
