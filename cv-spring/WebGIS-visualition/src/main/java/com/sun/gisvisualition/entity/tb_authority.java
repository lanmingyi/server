package com.sun.gisvisualition.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_authority")
public class tb_authority {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")//这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    private String pk;
    private String authority;
    private String mark;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
