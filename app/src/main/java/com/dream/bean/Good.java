package com.dream.bean;

import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by yangll on 15/8/24.
 * 商品结构
 */
@Table(value = "goods")
public class Good {

    /**
     * q_counttime : {}
     * q_uid : {}
     * q_end_time : {}
     * q_sscopen :
     * q_sscphase :
     * brandid : 109
     * pos : 0
     * id : 414
     * q_ssccode :
     * q_user : {}
     * title : 20cm可爱糖宝公仔
     * time : 1439648054
     * order : 1
     * description :
     * money : 28.00
     * zongrenshu : 28
     * canyurenshu : 8
     * yunjiage : 1.00
     * cateid : 14
     * sid : 325
     * renqi : 1
     * keywords :
     * qishu : 5
     * q_user_code : {}
     * title2 : 花灵虫毛绒玩具娃娃创意生日礼物女生千玩偶毛毛虫骨
     * q_djstime :
     * codes_table : shopcodes_1
     * def_renshu : 0
     * title_style : color:#FF0000;
     * shenyurenshu : 20
     * q_showtime : N
     * xsjx_time : 0
     * maxqishu : 9999
     * thumb : shopimg/20150727/88165575970630.png
     */
    @Ignore
    private QCounttimeEntity q_counttime;
    @Ignore
    private QUidEntity q_uid;
    @Ignore
    private QEndTimeEntity q_end_time;
    private String q_sscopen;
    private String q_sscphase;
    private String brandid;
    private String pos;

    @NotNull
    @PrimaryKey(PrimaryKey.AssignType.BY_MYSELF)
    private String id;
    private String q_ssccode;
    @Ignore
    private QUserEntity q_user;
    private String title;
    private String time;
    private String order;
    private String description;
    private String money;
    private String zongrenshu;
    private String canyurenshu;
    private String yunjiage;
    private String cateid;
    private String sid;
    private String renqi;
    private String keywords;
    private String qishu;
    @Ignore
    private QUserCodeEntity q_user_code;
    private String title2;
    private String q_djstime;
    private String codes_table;
    private String def_renshu;
    private String title_style;
    private String shenyurenshu;
    private String q_showtime;
    private String xsjx_time;
    private String maxqishu;
    private String thumb;
    @Ignore
    private int  page;
    @Ignore
    private int  sum;

    public void setQ_counttime(QCounttimeEntity q_counttime) {
        this.q_counttime = q_counttime;
    }

    public void setQ_uid(QUidEntity q_uid) {
        this.q_uid = q_uid;
    }

    public void setQ_end_time(QEndTimeEntity q_end_time) {
        this.q_end_time = q_end_time;
    }

    public void setQ_sscopen(String q_sscopen) {
        this.q_sscopen = q_sscopen;
    }

    public void setQ_sscphase(String q_sscphase) {
        this.q_sscphase = q_sscphase;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQ_ssccode(String q_ssccode) {
        this.q_ssccode = q_ssccode;
    }

    public void setQ_user(QUserEntity q_user) {
        this.q_user = q_user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setZongrenshu(String zongrenshu) {
        this.zongrenshu = zongrenshu;
    }

    public void setCanyurenshu(String canyurenshu) {
        this.canyurenshu = canyurenshu;
    }

    public void setYunjiage(String yunjiage) {
        this.yunjiage = yunjiage;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setRenqi(String renqi) {
        this.renqi = renqi;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public void setQ_user_code(QUserCodeEntity q_user_code) {
        this.q_user_code = q_user_code;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public void setQ_djstime(String q_djstime) {
        this.q_djstime = q_djstime;
    }

    public void setCodes_table(String codes_table) {
        this.codes_table = codes_table;
    }

    public void setDef_renshu(String def_renshu) {
        this.def_renshu = def_renshu;
    }

    public void setTitle_style(String title_style) {
        this.title_style = title_style;
    }

    public void setShenyurenshu(String shenyurenshu) {
        this.shenyurenshu = shenyurenshu;
    }

    public void setQ_showtime(String q_showtime) {
        this.q_showtime = q_showtime;
    }

    public void setXsjx_time(String xsjx_time) {
        this.xsjx_time = xsjx_time;
    }

    public void setMaxqishu(String maxqishu) {
        this.maxqishu = maxqishu;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public QCounttimeEntity getQ_counttime() {
        return q_counttime;
    }

    public QUidEntity getQ_uid() {
        return q_uid;
    }

    public QEndTimeEntity getQ_end_time() {
        return q_end_time;
    }

    public String getQ_sscopen() {
        return q_sscopen;
    }

    public String getQ_sscphase() {
        return q_sscphase;
    }

    public String getBrandid() {
        return brandid;
    }

    public String getPos() {
        return pos;
    }

    public String getId() {
        return id;
    }

    public String getQ_ssccode() {
        return q_ssccode;
    }

    public QUserEntity getQ_user() {
        return q_user;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }

    public String getMoney() {
        return money;
    }

    public String getZongrenshu() {
        return zongrenshu;
    }

    public String getCanyurenshu() {
        return canyurenshu;
    }

    public String getYunjiage() {
        return yunjiage;
    }

    public String getCateid() {
        return cateid;
    }

    public String getSid() {
        return sid;
    }

    public String getRenqi() {
        return renqi;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getQishu() {
        return qishu;
    }

    public QUserCodeEntity getQ_user_code() {
        return q_user_code;
    }

    public String getTitle2() {
        return title2;
    }

    public String getQ_djstime() {
        return q_djstime;
    }

    public String getCodes_table() {
        return codes_table;
    }

    public String getDef_renshu() {
        return def_renshu;
    }

    public String getTitle_style() {
        return title_style;
    }

    public String getShenyurenshu() {
        return shenyurenshu;
    }

    public String getQ_showtime() {
        return q_showtime;
    }

    public String getXsjx_time() {
        return xsjx_time;
    }

    public String getMaxqishu() {
        return maxqishu;
    }

    public String getThumb() {
        return thumb;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public static class QCounttimeEntity {
    }

    public static class QUidEntity {
    }

    public static class QEndTimeEntity {
    }

    public static class QUserEntity {
    }

    public static class QUserCodeEntity {
    }
}
