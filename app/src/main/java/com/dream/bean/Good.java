package com.dream.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by yangll on 15/8/24.
 * 商品结构
 */
@Table(value = "goods")
public class Good implements Parcelable {

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
//    @Ignore
    private String q_counttime;
//    @Ignore
    private String q_uid;
//    @Ignore
    private String q_end_time;
    private String q_sscopen;
    private String q_sscphase;
    private String brandid;
    private String pos;
    @NotNull
    @PrimaryKey(PrimaryKey.AssignType.BY_MYSELF)
    private String id;
    private String sid;
    private String q_ssccode;
    //    @Ignore
    private String q_user;
    private String title;
    private String time;
    @Column(value = "gorder")
    private String order;
    private String description;
    private String money;
    private String zongrenshu;
    private String canyurenshu;
    private String yunjiage;
    private String cateid;
    private String renqi;
    private String keywords;
    private String qishu;
    @Ignore
    private String q_user_code;
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
    @Ignore
    private boolean check;
    @Ignore
    private int addCount; //加入购物车的次数

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

    public String getQ_counttime() {
        return q_counttime;
    }

    public void setQ_counttime(String q_counttime) {
        this.q_counttime = q_counttime;
    }

    public String getQ_uid() {
        return q_uid;
    }

    public void setQ_uid(String q_uid) {
        this.q_uid = q_uid;
    }

    public String getQ_end_time() {
        return q_end_time;
    }

    public void setQ_end_time(String q_end_time) {
        this.q_end_time = q_end_time;
    }

    public String getQ_user_code() {
        return q_user_code;
    }

    public void setQ_user_code(String q_user_code) {
        this.q_user_code = q_user_code;
    }

    public String getQ_user() {
        return q_user;
    }

    public void setQ_user(String q_user) {
        this.q_user = q_user;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getAddCount() {
        return addCount;
    }

    public void setAddCount(int addCount) {
        this.addCount = addCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.q_counttime);
        dest.writeString(this.q_uid);
        dest.writeString(this.q_end_time);
        dest.writeString(this.q_sscopen);
        dest.writeString(this.q_sscphase);
        dest.writeString(this.brandid);
        dest.writeString(this.pos);
        dest.writeString(this.id);
        dest.writeString(this.sid);
        dest.writeString(this.q_ssccode);
        dest.writeString(this.q_user);
        dest.writeString(this.title);
        dest.writeString(this.time);
        dest.writeString(this.order);
        dest.writeString(this.description);
        dest.writeString(this.money);
        dest.writeString(this.zongrenshu);
        dest.writeString(this.canyurenshu);
        dest.writeString(this.yunjiage);
        dest.writeString(this.cateid);
        dest.writeString(this.renqi);
        dest.writeString(this.keywords);
        dest.writeString(this.qishu);
        dest.writeString(this.q_user_code);
        dest.writeString(this.title2);
        dest.writeString(this.q_djstime);
        dest.writeString(this.codes_table);
        dest.writeString(this.def_renshu);
        dest.writeString(this.title_style);
        dest.writeString(this.shenyurenshu);
        dest.writeString(this.q_showtime);
        dest.writeString(this.xsjx_time);
        dest.writeString(this.maxqishu);
        dest.writeString(this.thumb);
        dest.writeInt(this.page);
        dest.writeInt(this.sum);
        dest.writeByte(check ? (byte) 1 : (byte) 0);
    }

    public Good() {
    }

    protected Good(Parcel in) {
        this.q_counttime = in.readString();
        this.q_uid = in.readString();
        this.q_end_time = in.readString();
        this.q_sscopen = in.readString();
        this.q_sscphase = in.readString();
        this.brandid = in.readString();
        this.pos = in.readString();
        this.id = in.readString();
        this.sid = in.readString();
        this.q_ssccode = in.readString();
        this.q_user = in.readString();
        this.title = in.readString();
        this.time = in.readString();
        this.order = in.readString();
        this.description = in.readString();
        this.money = in.readString();
        this.zongrenshu = in.readString();
        this.canyurenshu = in.readString();
        this.yunjiage = in.readString();
        this.cateid = in.readString();
        this.renqi = in.readString();
        this.keywords = in.readString();
        this.qishu = in.readString();
        this.q_user_code = in.readString();
        this.title2 = in.readString();
        this.q_djstime = in.readString();
        this.codes_table = in.readString();
        this.def_renshu = in.readString();
        this.title_style = in.readString();
        this.shenyurenshu = in.readString();
        this.q_showtime = in.readString();
        this.xsjx_time = in.readString();
        this.maxqishu = in.readString();
        this.thumb = in.readString();
        this.page = in.readInt();
        this.sum = in.readInt();
        this.check = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Good> CREATOR = new Parcelable.Creator<Good>() {
        public Good createFromParcel(Parcel source) {
            return new Good(source);
        }

        public Good[] newArray(int size) {
            return new Good[size];
        }
    };
}
