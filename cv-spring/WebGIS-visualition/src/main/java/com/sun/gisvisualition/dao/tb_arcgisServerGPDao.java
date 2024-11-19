package com.sun.gisvisualition.dao;

import com.sun.gisvisualition.entity.tb_arcgisServerGP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface tb_arcgisServerGPDao extends JpaRepository<tb_arcgisServerGP, String>, JpaSpecificationExecutor<tb_arcgisServerGP> {

    @Query(value = "select a.pk as pk,a.gp_map_server as gpMapServer, a.gp_url as gpUrl, a.page_name as pageName,a.type as type from tb_gis_server_gp a where a.page_name = ?1", nativeQuery = true)
    List<Map<String, Object>> getAllGP(String page_name);
}
