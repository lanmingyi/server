package com.sun.gisvisualition.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_remote")
public class tb_remote {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")//这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    private String pk;
    private String date;
    @Column(name = "date_type")
    private String dataType;
    @Column(name = "img_base64")
    private String imgBase64;
    @Column(name = "min_data")
    private Double minData;
    @Column(name = "max_data")
    private Double maxData;
    private boolean active;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public Double getMinData() {
        return minData;
    }

    public void setMinData(Double minData) {
        this.minData = minData;
    }

    public Double getMaxData() {
        return maxData;
    }

    public void setMaxData(Double maxData) {
        this.maxData = maxData;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
