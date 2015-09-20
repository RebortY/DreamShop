package com.dream.bean.goodinfo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangll on 15/9/13.
 */
public class GoodInfo implements Serializable, Parcelable {


    /**
     * q_uid : 1
     * q_counttime : 15411009147
     * q_end_time : 1439194896.326
     * me_gonumber : 0
     * q_sscopen : 0
     * q_sscphase : 0
     * brandid : 113
     * pos : 0
     * id : 383
     * title : 20元支付宝红包
     * time : 1439188440
     * q_user : null
     * q_ssccode : 0
     * order : 1
     * description :
     * money : 22
     * zongrenshu : 22
     * q_content : null
     * yunjiage : 1
     * canyurenshu : 22
     * qishulist : [{"id":569,"gonumber_total":16,"q_uid":0,"q_user":null,"q_end_time":null,"q_user_code":null,"qiname":"第56期"},{"id":566,"gonumber_total":22,"q_uid":2,"q_user":null,"q_end_time":"1441864308.087","q_user_code":"10000010","qiname":"第55期"},{"id":560,"gonumber_total":22,"q_uid":18,"q_user":{"uid":"18","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18947102346"},"q_end_time":"1441726828.117","q_user_code":"10000020","qiname":"第54期"},{"id":556,"gonumber_total":22,"q_uid":1715,"q_user":{"uid":"1715","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"13833809763"},"q_end_time":"1441609919.768","q_user_code":"10000012","qiname":"第53期"},{"id":551,"gonumber_total":22,"q_uid":1715,"q_user":{"uid":"1715","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"13833809763"},"q_end_time":"1441473846.013","q_user_code":"10000020","qiname":"第52期"},{"id":544,"gonumber_total":22,"q_uid":1645,"q_user":{"uid":"1645","username":"18698402477","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18698402477"},"q_end_time":"1441450439.710","q_user_code":"10000014","qiname":"第51期"},{"id":540,"gonumber_total":22,"q_uid":18,"q_user":{"uid":"18","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18947102346"},"q_end_time":"1441372132.070","q_user_code":"10000021","qiname":"第50期"},{"id":536,"gonumber_total":22,"q_uid":1645,"q_user":{"uid":"1645","username":"18698402477","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18698402477"},"q_end_time":"1441363606.742","q_user_code":"10000021","qiname":"第49期"},{"id":512,"gonumber_total":22,"q_uid":1656,"q_user":{"uid":"1656","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"13071479689"},"q_end_time":"1441357260.729","q_user_code":"10000016","qiname":"第48期"},{"id":503,"gonumber_total":22,"q_uid":18,"q_user":{"uid":"18","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18947102346"},"q_end_time":"1441270862.682","q_user_code":"10000012","qiname":"第47期"},{"id":501,"gonumber_total":22,"q_uid":18,"q_user":{"uid":"18","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18947102346"},"q_end_time":"1441246692.384","q_user_code":"10000001","qiname":"第46期"},{"id":495,"gonumber_total":22,"q_uid":18,"q_user":{"uid":"18","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18947102346"},"q_end_time":"1441206413.420","q_user_code":"10000022","qiname":"第45期"},{"id":493,"gonumber_total":22,"q_uid":90,"q_user":{"uid":"90","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"15047833449"},"q_end_time":"1441203342.047","q_user_code":"10000022","qiname":"第44期"},{"id":492,"gonumber_total":22,"q_uid":90,"q_user":{"uid":"90","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"15047833449"},"q_end_time":"1441190565.184","q_user_code":"10000015","qiname":"第43期"},{"id":484,"gonumber_total":22,"q_uid":90,"q_user":{"uid":"90","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"15047833449"},"q_end_time":"1441188399.088","q_user_code":"10000002","qiname":"第42期"},{"id":483,"gonumber_total":22,"q_uid":1226,"q_user":null,"q_end_time":"1441164210.065","q_user_code":"10000012","qiname":"第41期"},{"id":476,"gonumber_total":22,"q_uid":1226,"q_user":null,"q_end_time":"1441162444.665","q_user_code":"10000012","qiname":"第40期"},{"id":473,"gonumber_total":22,"q_uid":18,"q_user":{"uid":"18","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18947102346"},"q_end_time":"1441033551.152","q_user_code":"10000015","qiname":"第39期"},{"id":472,"gonumber_total":22,"q_uid":18,"q_user":{"uid":"18","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18947102346"},"q_end_time":"1441031660.877","q_user_code":"10000006","qiname":"第38期"},{"id":469,"gonumber_total":22,"q_uid":1192,"q_user":null,"q_end_time":"1441031221.561","q_user_code":"10000009","qiname":"第37期"},{"id":464,"gonumber_total":22,"q_uid":481,"q_user":{"uid":"481","username":"","email":"wadodor@sina.com","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":""},"q_end_time":"1441029563.271","q_user_code":"10000018","qiname":"第36期"},{"id":457,"gonumber_total":22,"q_uid":18,"q_user":{"uid":"18","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18947102346"},"q_end_time":"1441023999.924","q_user_code":"10000002","qiname":"第35期"},{"id":452,"gonumber_total":22,"q_uid":1544,"q_user":null,"q_end_time":"1440917629.199","q_user_code":"10000007","qiname":"第34期"},{"id":449,"gonumber_total":22,"q_uid":1226,"q_user":null,"q_end_time":"1440846592.973","q_user_code":"10000002","qiname":"第33期"},{"id":443,"gonumber_total":22,"q_uid":18,"q_user":{"uid":"18","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18947102346"},"q_end_time":"1440825922.924","q_user_code":"10000022","qiname":"第32期"},{"id":442,"gonumber_total":22,"q_uid":1192,"q_user":null,"q_end_time":"1440773395.416","q_user_code":"10000005","qiname":"第31期"},{"id":438,"gonumber_total":22,"q_uid":2,"q_user":null,"q_end_time":"1440755727.310","q_user_code":"10000022","qiname":"第30期"},{"id":437,"gonumber_total":22,"q_uid":14,"q_user":{"uid":"14","username":"SHARK011","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":""},"q_end_time":"1440663042.715","q_user_code":"10000010","qiname":"第29期"},{"id":433,"gonumber_total":22,"q_uid":14,"q_user":{"uid":"14","username":"SHARK011","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":""},"q_end_time":"1440662741.086","q_user_code":"10000005","qiname":"第28期"},{"id":426,"gonumber_total":22,"q_uid":1192,"q_user":null,"q_end_time":"1440632473.023","q_user_code":"10000018","qiname":"第27期"},{"id":424,"gonumber_total":22,"q_uid":1398,"q_user":{"uid":"1398","username":"","email":"","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18903247060"},"q_end_time":"1440212403.860","q_user_code":"10000003","qiname":"第26期"},{"id":422,"gonumber_total":22,"q_uid":1192,"q_user":null,"q_end_time":"1440118614.657","q_user_code":"10000015","qiname":"第25期"},{"id":419,"gonumber_total":22,"q_uid":1192,"q_user":null,"q_end_time":"1439989921.465","q_user_code":"10000003","qiname":"第24期"},{"id":418,"gonumber_total":22,"q_uid":483,"q_user":null,"q_end_time":"1439927354.736","q_user_code":"10000002","qiname":"第23期"},{"id":413,"gonumber_total":22,"q_uid":1435,"q_user":{"uid":"1435","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18515490885"},"q_end_time":"1439921626.025","q_user_code":"10000002","qiname":"第22期"},{"id":411,"gonumber_total":22,"q_uid":1416,"q_user":{"uid":"1416","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"13530395496"},"q_end_time":"1439631787.042","q_user_code":"10000015","qiname":"第21期"},{"id":404,"gonumber_total":22,"q_uid":1399,"q_user":{"uid":"1399","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"15100011609"},"q_end_time":"1439627529.703","q_user_code":"10000007","qiname":"第20期"},{"id":403,"gonumber_total":22,"q_uid":1192,"q_user":null,"q_end_time":"1439396790.136","q_user_code":"10000018","qiname":"第19期"},{"id":402,"gonumber_total":22,"q_uid":446,"q_user":null,"q_end_time":"1439384416.582","q_user_code":"10000018","qiname":"第18期"},{"id":400,"gonumber_total":22,"q_uid":437,"q_user":null,"q_end_time":"1439354490.680","q_user_code":"10000008","qiname":"第17期"},{"id":399,"gonumber_total":22,"q_uid":1308,"q_user":{"uid":"1308","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"13527645826"},"q_end_time":"1439305040.110","q_user_code":"10000015","qiname":"第16期"},{"id":397,"gonumber_total":22,"q_uid":805,"q_user":null,"q_end_time":"1439301990.023","q_user_code":"10000016","qiname":"第15期"},{"id":396,"gonumber_total":22,"q_uid":446,"q_user":null,"q_end_time":"1439279511.062","q_user_code":"10000022","qiname":"第14期"},{"id":394,"gonumber_total":22,"q_uid":1259,"q_user":{"uid":"1259","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"13527615184"},"q_end_time":"1439269254.968","q_user_code":"10000008","qiname":"第13期"},{"id":389,"gonumber_total":22,"q_uid":1192,"q_user":null,"q_end_time":"1439221890.472","q_user_code":"10000022","qiname":"第12期"},{"id":388,"gonumber_total":22,"q_uid":1192,"q_user":null,"q_end_time":"1439202690.104","q_user_code":"10000009","qiname":"第11期"},{"id":387,"gonumber_total":22,"q_uid":1238,"q_user":{"uid":"1238","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18265855973"},"q_end_time":"1439202090.178","q_user_code":"10000002","qiname":"第10期"},{"id":385,"gonumber_total":22,"q_uid":2,"q_user":null,"q_end_time":"1439201502.429","q_user_code":"10000007","qiname":"第9期"},{"id":383,"gonumber_total":22,"q_uid":1,"q_user":null,"q_end_time":"1439194896.326","q_user_code":"10000018","qiname":"第8期"},{"id":382,"gonumber_total":22,"q_uid":1211,"q_user":{"uid":"1211","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18633473743"},"q_end_time":"1439188440.372","q_user_code":"10000015","qiname":"第7期"},{"id":381,"gonumber_total":22,"q_uid":2,"q_user":null,"q_end_time":"1439179226.454","q_user_code":"10000019","qiname":"第6期"},{"id":377,"gonumber_total":22,"q_uid":456,"q_user":{"uid":"456","username":"","email":"527042118@qq.com","img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":""},"q_end_time":"1439132189.542","q_user_code":"10000015","qiname":"第5期"},{"id":373,"gonumber_total":22,"q_uid":1,"q_user":null,"q_end_time":"1439121102.536","q_user_code":"10000018","qiname":"第4期"},{"id":370,"gonumber_total":22,"q_uid":1214,"q_user":{"uid":"1214","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"13550497712"},"q_end_time":"1439054431.002","q_user_code":"10000010","qiname":"第3期"},{"id":367,"gonumber_total":22,"q_uid":1211,"q_user":{"uid":"1211","username":"","email":null,"img":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","mobile":"18633473743"},"q_end_time":"1438958493.244","q_user_code":"10000015","qiname":"第2期"},{"id":365,"gonumber_total":22,"q_uid":1125,"q_user":null,"q_end_time":"1438846889.764","q_user_code":"10000012","qiname":"第1期"}]
     * cateid : 15
     * sid : 365
     * renqi : 1
     * picarr : ["http://m.1yuanmeng.com/statics/uploads/shopimg/20150806/77453684829421.jpg"]
     * keywords :
     * qishu : 8
     * q_user_code : 10000018
     * title2 : 发货时间每天上午10点，周日除外
     * q_djstime : 0
     * codes_table : shopcodes_1
     * def_renshu : 0
     * meRecords : []
     * title_style : color:#FF0000;
     * content : 元梦购-1元成就梦想奖品会在第二天上午十点统一发放，望各位朋友填好收获选项后耐心等待。谢谢各位朋友的配合，祝各位好运。
     * shenyurenshu : 0
     * q_showtime : N
     * xsjx_time : 0
     * maxqishu : 9999
     * records : [{"uid":1233,"company_code":null,"moneycount":1,"company_money":0,"status":"已付款,未发货,未完成","code":"A14391926835533339","shopname":"20元支付宝红包","gonumber":1,"ip":"","id":2375,"goucode":"10000017","shopid":383,"time":"1439192683.553","username":"186****9798","shopqishu":8,"huode":"0","pay_type":"账户","company":null,"uphoto":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","code_tmp":0},{"uid":446,"company_code":null,"moneycount":5,"company_money":0,"status":"已付款,未发货,未完成","code":"A14391940932207619","shopname":"20元支付宝红包","gonumber":5,"ip":"内蒙古自治区呼和浩特市,123.179.6.135","id":2379,"goucode":"10000019,10000020,10000007,10000016,10000015","shopid":383,"time":"1439194093.221","username":"誓言","shopqishu":8,"huode":"0","pay_type":"账户","company":null,"uphoto":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","code_tmp":0},{"uid":1230,"company_code":null,"moneycount":7,"company_money":0,"status":"已付款,未发货,未完成","code":"A14391941932182679","shopname":"20元支付宝红包","gonumber":7,"ip":"","id":2380,"goucode":"10000001,10000010,10000008,10000005,10000004,10000022,10000021","shopid":383,"time":"1439194193.218","username":"188****4303","shopqishu":8,"huode":"0","pay_type":"账户","company":null,"uphoto":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","code_tmp":0},{"uid":1234,"company_code":null,"moneycount":1,"company_money":0,"status":"已付款,未发货,未完成","code":"A14391942095092167","shopname":"20元支付宝红包","gonumber":1,"ip":"内蒙古自治区呼和浩特市,111.127.242.126","id":2381,"goucode":"10000003","shopid":383,"time":"1439194209.509","username":"185****1043","shopqishu":8,"huode":"0","pay_type":"账户","company":null,"uphoto":"http://m.1yuanmeng.com/statics/uploads/photo/member.jpg","code_tmp":0},{"uid":1,"company_code":"","moneycount":8,"company_money":0.01,"status":"已付款,已发货,已完成","code":"A14391944749737652","shopname":"20元支付宝红包","gonumber":8,"ip":"内蒙古自治区呼和浩特市,121.57.230.211","id":2382,"goucode":"10000014,10000013,10000006,10000018,10000009,10000002,10000011,10000012","shopid":383,"time":"1439194474.974","username":"哎呦我去又中了","shopqishu":8,"huode":"10000018","pay_type":"账户","company":"","uphoto":"http://m.1yuanmeng.com/statics/uploads/touimg/20150616/96380253432584.jpg","code_tmp":0}]
     * thumb : http://m.1yuanmeng.com/statics/uploads/shopimg/20150806/19969844829409.jpg
     */
    private int q_uid;
    private String q_counttime;
    private String q_end_time;
    private int me_gonumber;
    private String q_sscopen;
    private String q_sscphase;
    private int brandid;
    private int pos;
    private int id;
    private String title;
    private int time;
    private User q_user;
    private String q_ssccode;
    private int order;
    private String description;
    private int money;
    private int zongrenshu;
    private String q_content;
    private int yunjiage;
    private int canyurenshu;
    private ArrayList<QishulistEntity> qishulist;
    private int cateid;
    private int sid;
    private int renqi;
    private List<String> picarr;
    private String keywords;
    private int qishu;
    private String q_user_code;
    private String title2;
    private String q_djstime;
    private String codes_table;
    private int def_renshu;
    private ArrayList<RecordsEntity> meRecords;
    private String title_style;
    private String content;
    private int shenyurenshu;
    private String q_showtime;
    private int xsjx_time;
    private int maxqishu;
    private ArrayList<RecordsEntity> records;
    private String thumb;

    public void setQ_uid(int q_uid) {
        this.q_uid = q_uid;
    }

    public void setQ_counttime(String q_counttime) {
        this.q_counttime = q_counttime;
    }

    public void setQ_end_time(String q_end_time) {
        this.q_end_time = q_end_time;
    }

    public void setMe_gonumber(int me_gonumber) {
        this.me_gonumber = me_gonumber;
    }

    public void setQ_sscopen(String q_sscopen) {
        this.q_sscopen = q_sscopen;
    }

    public void setQ_sscphase(String q_sscphase) {
        this.q_sscphase = q_sscphase;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public void setQ_ssccode(String q_ssccode) {
        this.q_ssccode = q_ssccode;
    }

    public ArrayList<QishulistEntity> getQishulist() {
        return qishulist;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setZongrenshu(int zongrenshu) {
        this.zongrenshu = zongrenshu;
    }

    public void setQ_content(String q_content) {
        this.q_content = q_content;
    }

    public void setYunjiage(int yunjiage) {
        this.yunjiage = yunjiage;
    }

    public void setCanyurenshu(int canyurenshu) {
        this.canyurenshu = canyurenshu;
    }


    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setRenqi(int renqi) {
        this.renqi = renqi;
    }

    public void setPicarr(List<String> picarr) {
        this.picarr = picarr;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setQishu(int qishu) {
        this.qishu = qishu;
    }

    public void setQ_user_code(String q_user_code) {
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

    public void setDef_renshu(int def_renshu) {
        this.def_renshu = def_renshu;
    }


    public void setTitle_style(String title_style) {
        this.title_style = title_style;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setShenyurenshu(int shenyurenshu) {
        this.shenyurenshu = shenyurenshu;
    }

    public void setQ_showtime(String q_showtime) {
        this.q_showtime = q_showtime;
    }

    public void setXsjx_time(int xsjx_time) {
        this.xsjx_time = xsjx_time;
    }

    public void setMaxqishu(int maxqishu) {
        this.maxqishu = maxqishu;
    }


    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getQ_uid() {
        return q_uid;
    }

    public String getQ_counttime() {
        return q_counttime;
    }

    public String getQ_end_time() {
        return q_end_time;
    }

    public int getMe_gonumber() {
        return me_gonumber;
    }

    public String getQ_sscopen() {
        return q_sscopen;
    }

    public String getQ_sscphase() {
        return q_sscphase;
    }

    public int getBrandid() {
        return brandid;
    }

    public int getPos() {
        return pos;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getTime() {
        return time;
    }

    public User getQ_user() {
        return q_user;
    }

    public void setQ_user(User q_user) {
        this.q_user = q_user;
    }

    public String getQ_ssccode() {
        return q_ssccode;
    }

    public int getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }

    public int getMoney() {
        return money;
    }

    public int getZongrenshu() {
        return zongrenshu;
    }

    public String getQ_content() {
        return q_content;
    }

    public int getYunjiage() {
        return yunjiage;
    }

    public int getCanyurenshu() {
        return canyurenshu;
    }

    public int getCateid() {
        return cateid;
    }

    public int getSid() {
        return sid;
    }

    public int getRenqi() {
        return renqi;
    }

    public List<String> getPicarr() {
        return picarr;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getQishu() {
        return qishu;
    }

    public String getQ_user_code() {
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

    public int getDef_renshu() {
        return def_renshu;
    }

    public String getTitle_style() {
        return title_style;
    }

    public String getContent() {
        return content;
    }

    public int getShenyurenshu() {
        return shenyurenshu;
    }

    public String getQ_showtime() {
        return q_showtime;
    }

    public int getXsjx_time() {
        return xsjx_time;
    }

    public int getMaxqishu() {
        return maxqishu;
    }

    public String getThumb() {
        return thumb;
    }

    public void setQishulist(ArrayList<QishulistEntity> qishulist) {
        this.qishulist = qishulist;
    }

    public ArrayList<RecordsEntity> getMeRecords() {
        return meRecords;
    }

    public void setMeRecords(ArrayList<RecordsEntity> meRecords) {
        this.meRecords = meRecords;
    }

    public ArrayList<RecordsEntity> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<RecordsEntity> records) {
        this.records = records;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.q_uid);
        dest.writeString(this.q_counttime);
        dest.writeString(this.q_end_time);
        dest.writeInt(this.me_gonumber);
        dest.writeString(this.q_sscopen);
        dest.writeString(this.q_sscphase);
        dest.writeInt(this.brandid);
        dest.writeInt(this.pos);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.time);
        dest.writeParcelable(this.q_user, 0);
        dest.writeString(this.q_ssccode);
        dest.writeInt(this.order);
        dest.writeString(this.description);
        dest.writeInt(this.money);
        dest.writeInt(this.zongrenshu);
        dest.writeString(this.q_content);
        dest.writeInt(this.yunjiage);
        dest.writeInt(this.canyurenshu);
        dest.writeTypedList(qishulist);
        dest.writeInt(this.cateid);
        dest.writeInt(this.sid);
        dest.writeInt(this.renqi);
        dest.writeStringList(this.picarr);
        dest.writeString(this.keywords);
        dest.writeInt(this.qishu);
        dest.writeString(this.q_user_code);
        dest.writeString(this.title2);
        dest.writeString(this.q_djstime);
        dest.writeString(this.codes_table);
        dest.writeInt(this.def_renshu);
        dest.writeTypedList(meRecords);
        dest.writeString(this.title_style);
        dest.writeString(this.content);
        dest.writeInt(this.shenyurenshu);
        dest.writeString(this.q_showtime);
        dest.writeInt(this.xsjx_time);
        dest.writeInt(this.maxqishu);
        dest.writeTypedList(records);
        dest.writeString(this.thumb);
    }

    public GoodInfo() {
    }

    protected GoodInfo(Parcel in) {
        this.q_uid = in.readInt();
        this.q_counttime = in.readString();
        this.q_end_time = in.readString();
        this.me_gonumber = in.readInt();
        this.q_sscopen = in.readString();
        this.q_sscphase = in.readString();
        this.brandid = in.readInt();
        this.pos = in.readInt();
        this.id = in.readInt();
        this.title = in.readString();
        this.time = in.readInt();
        this.q_user = in.readParcelable(User.class.getClassLoader());
        this.q_ssccode = in.readString();
        this.order = in.readInt();
        this.description = in.readString();
        this.money = in.readInt();
        this.zongrenshu = in.readInt();
        this.q_content = in.readString();
        this.yunjiage = in.readInt();
        this.canyurenshu = in.readInt();
        this.qishulist = in.createTypedArrayList(QishulistEntity.CREATOR);
        this.cateid = in.readInt();
        this.sid = in.readInt();
        this.renqi = in.readInt();
        this.picarr = in.createStringArrayList();
        this.keywords = in.readString();
        this.qishu = in.readInt();
        this.q_user_code = in.readString();
        this.title2 = in.readString();
        this.q_djstime = in.readString();
        this.codes_table = in.readString();
        this.def_renshu = in.readInt();
        this.meRecords = in.createTypedArrayList(RecordsEntity.CREATOR);
        this.title_style = in.readString();
        this.content = in.readString();
        this.shenyurenshu = in.readInt();
        this.q_showtime = in.readString();
        this.xsjx_time = in.readInt();
        this.maxqishu = in.readInt();
        this.records = in.createTypedArrayList(RecordsEntity.CREATOR);
        this.thumb = in.readString();
    }

    public static final Parcelable.Creator<GoodInfo> CREATOR = new Parcelable.Creator<GoodInfo>() {
        public GoodInfo createFromParcel(Parcel source) {
            return new GoodInfo(source);
        }

        public GoodInfo[] newArray(int size) {
            return new GoodInfo[size];
        }
    };
}
