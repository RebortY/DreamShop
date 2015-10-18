package com.dream.main.infoview;

import com.dream.bean.goodinfo.GoodInfo;

import org.robobinding.annotation.PresentationModel;

/**
 * @author yangll
 */
@PresentationModel
public class GoingFragmentPM {

    GoodInfo info = null;
    private int imax = 0;
    private int imin = 0;
    private int iprogress;
    private String jiazhi;

    public GoingFragmentPM(GoodInfo info) {
        this.info  = info;
    }

    public int getImax() {
        return info.getZongrenshu();
    }

    public void setImax(int imax) {
        this.imax = imax;
    }

    public int getImin() {
        return imin;
    }

    public void setImin(int imin) {
        this.imin = imin;
    }

    public int getIprogress() {
        return info.getCanyurenshu();
    }

    public void setIprogress(int iprogress) {
        this.iprogress = iprogress;
    }

    public String getJiazhi() {
        return "价值:"+info.getMoney();
    }
}
