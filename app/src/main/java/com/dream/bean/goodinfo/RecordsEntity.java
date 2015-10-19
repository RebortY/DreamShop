package com.dream.bean.goodinfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangll on 15/9/13.
 */
public class RecordsEntity implements Parcelable {

    /**
     * uid : 1233
     * company_code : null
     * moneycount : 1
     * company_money : 0
     * status : 已付款,未发货,未完成
     * code : A14391926835533339
     * shopname : 20元支付宝红包
     * gonumber : 1
     * ip :
     * id : 2375
     * goucode : 10000017
     * shopid : 383
     * time : 1439192683.553
     * username : 186****9798
     * shopqishu : 8
     * huode : 0
     * pay_type : 账户
     * company : null
     * uphoto : http://m.1yuanmeng.com/statics/uploads/photo/member.jpg
     * code_tmp : 0
     */


    private int uid;
    private String company_code;
    private int moneycount;
    private int company_money;
    private String status;
    private String code;
    private String shopname;
    private int gonumber;
    private String ip;
    private int id;
    private String goucode;
    private int shopid;
    private String time;
    private String username;
    private int shopqishu;
    private String huode;
    private String pay_type;
    private String company;
    private String uphoto;
    private int code_tmp;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public void setMoneycount(int moneycount) {
        this.moneycount = moneycount;
    }

    public void setCompany_money(int company_money) {
        this.company_money = company_money;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public void setGonumber(int gonumber) {
        this.gonumber = gonumber;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGoucode(String goucode) {
        this.goucode = goucode;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setShopqishu(int shopqishu) {
        this.shopqishu = shopqishu;
    }

    public void setHuode(String huode) {
        this.huode = huode;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setUphoto(String uphoto) {
        this.uphoto = uphoto;
    }

    public void setCode_tmp(int code_tmp) {
        this.code_tmp = code_tmp;
    }

    public int getUid() {
        return uid;
    }

    public String getCompany_code() {
        return company_code;
    }

    public int getMoneycount() {
        return moneycount;
    }

    public int getCompany_money() {
        return company_money;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getShopname() {
        return shopname;
    }

    public int getGonumber() {
        return gonumber;
    }

    public String getIp() {
        return ip;
    }

    public int getId() {
        return id;
    }

    public String getGoucode() {
        return goucode;
    }

    public int getShopid() {
        return shopid;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public int getShopqishu() {
        return shopqishu;
    }

    public String getHuode() {
        return huode;
    }

    public String getPay_type() {
        return pay_type;
    }

    public String getCompany() {
        return company;
    }

    public String getUphoto() {
        return uphoto;
    }

    public int getCode_tmp() {
        return code_tmp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.company_code);
        dest.writeInt(this.moneycount);
        dest.writeInt(this.company_money);
        dest.writeString(this.status);
        dest.writeString(this.code);
        dest.writeString(this.shopname);
        dest.writeInt(this.gonumber);
        dest.writeString(this.ip);
        dest.writeInt(this.id);
        dest.writeString(this.goucode);
        dest.writeInt(this.shopid);
        dest.writeString(this.time);
        dest.writeString(this.username);
        dest.writeInt(this.shopqishu);
        dest.writeString(this.huode);
        dest.writeString(this.pay_type);
        dest.writeString(this.company);
        dest.writeString(this.uphoto);
        dest.writeInt(this.code_tmp);
    }

    public RecordsEntity() {
    }

    protected RecordsEntity(Parcel in) {
        this.uid = in.readInt();
        this.company_code = in.readString();
        this.moneycount = in.readInt();
        this.company_money = in.readInt();
        this.status = in.readString();
        this.code = in.readString();
        this.shopname = in.readString();
        this.gonumber = in.readInt();
        this.ip = in.readString();
        this.id = in.readInt();
        this.goucode = in.readString();
        this.shopid = in.readInt();
        this.time = in.readString();
        this.username = in.readString();
        this.shopqishu = in.readInt();
        this.huode = in.readString();
        this.pay_type = in.readString();
        this.company = in.readString();
        this.uphoto = in.readString();
        this.code_tmp = in.readInt();
    }

    public static final Creator<RecordsEntity> CREATOR = new Creator<RecordsEntity>() {
        public RecordsEntity createFromParcel(Parcel source) {
            return new RecordsEntity(source);
        }

        public RecordsEntity[] newArray(int size) {
            return new RecordsEntity[size];
        }
    };
}
