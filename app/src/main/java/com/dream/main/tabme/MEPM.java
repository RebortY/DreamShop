package com.dream.main.tabme;

import android.content.Context;
import android.content.Intent;

import com.dream.R;
import com.dream.bean.AuthUser;
import com.dream.bean.UpLoadHeadBean;
import com.dream.main.DreamApplication;
import com.dream.main.login.LoginAct;
import com.dream.main.login.LoginPM;
import com.dream.main.login.RegAct;
import com.dream.main.shopcart.ShopCartActivity;
import com.dream.main.tabme.account.AccountAct;
import com.dream.main.tabme.address.AddressActivity;
import com.dream.main.tabme.prize.MyPrizeAct;
import com.dream.main.tabme.record.MyDreamRecordAct;
import com.dream.main.tabme.set.SetAct;
import com.dream.main.tabshow.me.ShowMeActivity;
import com.dream.net.NetResponse;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.util.ToastUtil;
import com.dream.util.UplodUtil;
import com.dream.views.AbstractPM;
import com.github.snowdream.android.util.Log;

import org.apache.commons.lang.StringUtils;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.view.ClickEvent;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/22.
 */
@PresentationModel
public class MEPM extends AbstractPM implements HasPresentationModelChangeSupport {

    public static PresentationModelChangeSupport changeSupport;

    MeFragmentView meFragmentView;
    Context mContext;

    private String userName;//用户名

    private String userTag;//用户头衔

    private String userMoey;//余额

    private boolean circle = true;

    private String userScore;//圆梦币

    String url = "file://drawable/R.drawable.img_hand_def";//头像url

    public MEPM(Context context, MeFragmentView meView) {
        this.meFragmentView = meView;
        this.mContext = context;
        changeSupport = new PresentationModelChangeSupport(this);
        DreamApplication.getApp().eventBus().register(this);

        goLogin();
    }

    public void goLogin(){
        //获取当前用户，直接登录
        AuthUser au = LoginHandler.getinstance().getLastLoginUser();
        if (au != null && au.getMobile() != null && au.getPassword() != null)
            LoginHandler.getinstance().login(LoginHandler.LOGIN_PHONE, au.getMobile(), au.getPassword());
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public String getUserTag() {
        return userTag;
    }

    public String getUrl() {
        return url;
    }

    public boolean isCircle() {
        return circle;
    }

    public String getUserMoey() {
        return userMoey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void onClicks(ClickEvent event) {

        if (event.getView().getId() == R.id.tv_login || event.getView().getId() == R.id.tv_reg) {
            switch (event.getView().getId()) {
                case R.id.tv_login:
                    mContext.startActivity(new Intent(mContext, LoginAct.class));
                    break;
                case R.id.tv_reg:
                    mContext.startActivity(new Intent(mContext, RegAct.class));
                    break;
            }
        } else {
            if (DreamApplication.getApp().getUser() != null) {
                if (DreamApplication.getApp().getUser().isLogin()) {

                    meFragmentView.setOnClickView(event.getView());

                    switch (event.getView().getId()) {
                        case R.id.tv_pay:
                            mContext.startActivity(new Intent(mContext, AccountPayAct.class));
                            break;
                        case R.id.img_hand:
//                            mContext.startActivity(new Intent(mContext, UserInfoAct.class));
                            break;
                        case R.id.layoutItem_address:
                            mContext.startActivity(new Intent(mContext, AddressActivity.class));
                            break;
                        case R.id.tv_my_dream_shoptv_my:
                            mContext.startActivity(new Intent(mContext, MyDreamRecordAct.class));
                            break;
                        case R.id.layoutItem_user:
                            mContext.startActivity(new Intent(mContext, AccountAct.class));
                            break;
                        case R.id.layoutItem_set:
                            mContext.startActivity(new Intent(mContext, SetAct.class));
                            break;
                        case R.id.tv_my_shop_buy:
                            mContext.startActivity(new Intent(mContext, MyPrizeAct.class));
                            break;
                        case R.id.tv_my_shop_card:
                            mContext.startActivity(new Intent(mContext, ShopCartActivity.class));
                            break;
                        case R.id.tv_my_shop_pub:
                            mContext.startActivity(new Intent(mContext, ShowMeActivity.class));
                            break;
                    }
                } else {
                    ToastUtil.show("未登录");
                }
            } else {
                ToastUtil.show("未登录");
            }
        }
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }


    /**
     * 登录后头像及信息
     *
     * @param resp
     */
    @Subcriber(tag = LoginTag.LOGIN, threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp) {

        setUserInfo(resp);
    }

    /**
     * QQ登录后头像及信息
     *
     * @param resp
     */
    @Subcriber(tag = LoginTag.LOGIN_QQ, threadMode = ThreadMode.MainThread)
    public void loginRespHandlerQQ(LoginResp resp) {
        DreamApplication.getApp().getUser().setImg(LoginPM.QQ_HEAD_URL);
        setUserInfo(resp);
    }

    private void setUserInfo(LoginResp resp){

        if (RespCode.SUCCESS.equals(resp.getErrorCode())) {

            url = DreamApplication.getApp().getUser().getImg();

            if (StringUtils.isEmpty(DreamApplication.getApp().getUser().getUsername())) {
                userName = DreamApplication.getApp().getUser().getMobile();
            } else {
                userName = DreamApplication.getApp().getUser().getUsername();
            }
            userTag = DreamApplication.getApp().getUser().getYungoudj();
            if(DreamApplication.getApp().getUser().getMoney() != 0){
                userMoey = mContext.getResources().getString(R.string.tv_balance, String.valueOf(DreamApplication.getApp().getUser().getMoney()));
            }else{
                userMoey = "余额：0元";
            }
            int score = DreamApplication.getApp().getUser().getScore() / DreamApplication.getApp().getLoginBean().getFufen_yuan();
            userScore = mContext.getResources().getString(R.string.tv_score, String.valueOf(score));//圆梦币计算结果

            refresh();
            meFragmentView.onClickView(1);
        } else {
            ToastUtil.show(resp.getErrorMsg());
        }
    }

    public void refresh(){

        changeSupport.firePropertyChange("url");
        changeSupport.firePropertyChange("userName");
        changeSupport.firePropertyChange("userMoey");
        changeSupport.firePropertyChange("userTag");
        changeSupport.firePropertyChange("userScore");
    }

    /**
     * 退出登录返回处理
     */
    @Subcriber(tag = LoginTag.LOGIN_OUT_PHONE, threadMode = ThreadMode.MainThread)
    public void respLogout(NetResponse response) {

        url = "file://drawable/R.drawable.img_hand_def";
        userName = "";
        userTag = "";
        userMoey = "";
        changeSupport.firePropertyChange("url");
        changeSupport.firePropertyChange("userName");
        changeSupport.firePropertyChange("userMoey");
        changeSupport.firePropertyChange("userTag");
        changeSupport.firePropertyChange("userScore");
        meFragmentView.onClickView(0);
    }

}
