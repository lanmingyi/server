package com.sun.gisvisualition.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_2018_soil")
public class tb_2018_soil {

    //这里可以看见小驼峰的用法
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")//这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    private String pk;
    private double latitude;
    private double longitude;
    private double organic;
    private double n;
    private double p;
    private double k;
    private double jjN;
    private double ph;
    private double wc;
    private double yxCu;
    private double yxZn;
    private double yxFe;
    private double yxMn;
    private double yxB;
    private double yxS;
    private double yxCa;
    private double yxMg;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getOrganic() {
        return organic;
    }

    public void setOrganic(double organic) {
        this.organic = organic;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getJjN() {
        return jjN;
    }

    public void setJjN(double jjN) {
        this.jjN = jjN;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getWc() {
        return wc;
    }

    public void setWc(double wc) {
        this.wc = wc;
    }

    public double getYxCu() {
        return yxCu;
    }

    public void setYxCu(double yxCu) {
        this.yxCu = yxCu;
    }

    public double getYxZn() {
        return yxZn;
    }

    public void setYxZn(double yxZn) {
        this.yxZn = yxZn;
    }

    public double getYxFe() {
        return yxFe;
    }

    public void setYxFe(double yxFe) {
        this.yxFe = yxFe;
    }

    public double getYxMn() {
        return yxMn;
    }

    public void setYxMn(double yxMn) {
        this.yxMn = yxMn;
    }

    public double getYxB() {
        return yxB;
    }

    public void setYxB(double yxB) {
        this.yxB = yxB;
    }

    public double getYxS() {
        return yxS;
    }

    public void setYxS(double yxS) {
        this.yxS = yxS;
    }

    public double getYxCa() {
        return yxCa;
    }

    public void setYxCa(double yxCa) {
        this.yxCa = yxCa;
    }

    public double getYxMg() {
        return yxMg;
    }

    public void setYxMg(double yxMg) {
        this.yxMg = yxMg;
    }
}
