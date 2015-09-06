package com.dream.main.tabme;

import java.util.List;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/4 21:49
 */
public class AddressListItemBean {
    /**
     * code : A00000
     * data : {"list":[{"uid":1,"id":59,"time":1439634172,"default":"N","youbian":10200,"xian":"东城区","tell":"","jiedao":"西祠胡同","shouhuoren":"王咚咚","shi":"北京市","sheng":"北京市","qq":"","mobile":"18347945300"},{"uid":1,"id":71,"time":1441368688,"default":"Y","youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"},{"uid":1,"id":72,"time":1441368771,"default":"N","youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"},{"uid":1,"id":73,"time":1441369413,"default":null,"youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"},{"uid":1,"id":74,"time":1441370829,"default":null,"youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"},{"uid":1,"id":75,"time":1441377528,"default":null,"youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"}]}
     */

    private String code;
    private DataEntity data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * list : [{"uid":1,"id":59,"time":1439634172,"default":"N","youbian":10200,"xian":"东城区","tell":"","jiedao":"西祠胡同","shouhuoren":"王咚咚","shi":"北京市","sheng":"北京市","qq":"","mobile":"18347945300"},{"uid":1,"id":71,"time":1441368688,"default":"Y","youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"},{"uid":1,"id":72,"time":1441368771,"default":"N","youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"},{"uid":1,"id":73,"time":1441369413,"default":null,"youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"},{"uid":1,"id":74,"time":1441370829,"default":null,"youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"},{"uid":1,"id":75,"time":1441377528,"default":null,"youbian":0,"xian":"海淀区","tell":"","jiedao":"小南庄","shouhuoren":"刘佳","shi":"北京","sheng":"北京","qq":"","mobile":"13401165595"}]
         */

        private List<ListEntity> list;

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            /**
             * uid : 1
             * id : 59
             * time : 1439634172
             * default : N
             * youbian : 10200
             * xian : 东城区
             * tell :
             * jiedao : 西祠胡同
             * shouhuoren : 王咚咚
             * shi : 北京市
             * sheng : 北京市
             * qq :
             * mobile : 18347945300
             */

            private int uid;
            private int id;
            private int time;
            private String defaulted;
            private int youbian;
            private String xian;
            private String tell;
            private String jiedao;
            private String shouhuoren;
            private String shi;
            private String sheng;
            private String qq;
            private String mobile;

            public void setUid(int uid) {
                this.uid = uid;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setTime(int time) {
                this.time = time;
            }


            public void setYoubian(int youbian) {
                this.youbian = youbian;
            }

            public void setXian(String xian) {
                this.xian = xian;
            }

            public void setTell(String tell) {
                this.tell = tell;
            }

            public void setJiedao(String jiedao) {
                this.jiedao = jiedao;
            }

            public void setShouhuoren(String shouhuoren) {
                this.shouhuoren = shouhuoren;
            }

            public void setShi(String shi) {
                this.shi = shi;
            }

            public void setSheng(String sheng) {
                this.sheng = sheng;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getUid() {
                return uid;
            }

            public int getId() {
                return id;
            }

            public int getTime() {
                return time;
            }

            public String getDefaulted() {
                return defaulted;
            }

            public void setDefaulted(String defaulted) {
                this.defaulted = defaulted;
            }

            public int getYoubian() {
                return youbian;
            }

            public String getXian() {
                return xian;
            }

            public String getTell() {
                return tell;
            }

            public String getJiedao() {
                return jiedao;
            }

            public String getShouhuoren() {
                return shouhuoren;
            }

            public String getShi() {
                return shi;
            }

            public String getSheng() {
                return sheng;
            }

            public String getQq() {
                return qq;
            }

            public String getMobile() {
                return mobile;
            }
        }
    }
}
