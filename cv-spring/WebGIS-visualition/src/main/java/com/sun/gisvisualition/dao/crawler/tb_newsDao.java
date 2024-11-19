package com.sun.gisvisualition.dao.crawler;

import com.sun.gisvisualition.entity.crawler.tb_news;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface tb_newsDao extends JpaRepository<tb_news,String> , JpaSpecificationExecutor<tb_news> {
}
