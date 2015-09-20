package com.dream.bean;


/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/19 22:05
 * 上传头像
 */
public class UpLoadHeadBean {


    /**
     * code : A00000
     * data : {"path":"http://m.1yuanmeng.com/statics/uploads/","ok":true,"url":"touimg/20150919/610426767.jpg","size":3627}
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
         * path : http://m.1yuanmeng.com/statics/uploads/
         * ok : true
         * url : touimg/20150919/610426767.jpg
         * size : 3627
         */

        private String path;
        private boolean ok;
        private String url;
        private int size;

        public void setPath(String path) {
            this.path = path;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getPath() {
            return path;
        }

        public boolean getOk() {
            return ok;
        }

        public String getUrl() {
            return url;
        }

        public int getSize() {
            return size;
        }
    }
}
