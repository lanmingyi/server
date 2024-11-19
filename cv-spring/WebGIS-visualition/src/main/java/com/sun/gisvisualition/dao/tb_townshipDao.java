package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_township;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface tb_townshipDao extends JpaRepository<tb_township, String>, JpaSpecificationExecutor<tb_township> {
    @Query(value = "select u.pk,u.name from tb_township u")
    List getTownPkAndName(Sort sort);
}
