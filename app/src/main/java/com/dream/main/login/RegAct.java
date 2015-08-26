package com.dream.main.login;

import android.view.View;

import com.dream.R;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.util.StringUtils;
import com.dream.util.ToastUtil;


/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/24 20:19
 * 注册
 */
public class RegAct extends BaseActivity implements BaseActView {

    RegPM regPM;

    @Override
    public int getLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    public Object initPM() {
        regPM = new RegPM(this);
        return regPM;
    }

    @Override
    public void setOnClickView(View view) {

        switch (view.getId()) {
            case R.id.bt_get_code:
                if(StringUtils.isEmpty(regPM.getSmsCode())){
                    ToastUtil.show(this, R.string.tv_input_code);
                }else{

                }
                break;
            case R.id.bt_reg:
                if(isChickText()){

                }
                break;
        }
    }

    private boolean isChickText(){

        if(StringUtils.isEmpty(regPM.getUserPhone())){
            ToastUtil.show(this, R.string.tv_input_phone);
            return false;
        }
        if(StringUtils.isEmpty(regPM.getSmsCode())){
            ToastUtil.show(this, R.string.tv_input_code);
            return false;
        }
        if(StringUtils.isEmpty(regPM.getPsd())){
            ToastUtil.show(this, R.string.tv_input_psd);
            return false;
        }
        if(StringUtils.isEmpty(regPM.getPsdConfirm())){
            ToastUtil.show(this, R.string.tv_input_psd2);
            return false;
        }
        if(!regPM.getPsd().equals(regPM.getPsdConfirm())){
            ToastUtil.show(this, R.string.tv_input_psd_dif);
            return false;
        }
        return true;
    }
}
