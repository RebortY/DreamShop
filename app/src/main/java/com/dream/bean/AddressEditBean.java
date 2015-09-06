package com.dream.bean;

import java.io.Serializable;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/5 17:47
 * 编辑收获地址
 */
public class AddressEditBean implements Serializable{

    int id;

    String sheng;

    String shi;

    String xian;

    String jiedao;

    String shouhuoren;

    String mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getJiedao() {
        return jiedao;
    }

    public void setJiedao(String jiedao) {
        this.jiedao = jiedao;
    }

    public String getShouhuoren() {
        return shouhuoren;
    }

    public void setShouhuoren(String shouhuoren) {
        this.shouhuoren = shouhuoren;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
