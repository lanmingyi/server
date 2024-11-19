package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_village;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface tb_villageDao extends JpaRepository<tb_village, String>, JpaSpecificationExecutor<tb_village> {

    @Query(value = "select u.pk,u.name from tb_village u")
    List getVillagePkAndName(Sort sort);
}
