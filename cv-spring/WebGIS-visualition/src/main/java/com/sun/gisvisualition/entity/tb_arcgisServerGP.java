package com.sun.gisvisualition.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_gis_server_gp")
public class tb_arcgisServerGP {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")//这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    private String pk;
    @Column(name = "gp_url")
    private String gpUrl;
    @Column(name = "gp_mapServer")
    private String gpMapServer;
    private String type;
    @Column(name = "page_name")
    private String pageName;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getGpUrl() {
        return gpUrl;
    }

    public void setGpUrl(String gpUrl) {
        this.gpUrl = gpUrl;
    }

    public String getGpMapServer() {
        return gpMapServer;
    }

    public void setGpMapServer(String gpMapServer) {
        this.gpMapServer = gpMapServer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
