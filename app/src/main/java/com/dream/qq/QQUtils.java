package com.dream.qq;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.github.snowdream.android.util.Log;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/30 21:19
 */
public class QQUtils {

    static int shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;

    public static void initOpenidAndToken(Tencent mTencent, JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    public static boolean ready(Tencent mTencent, Context context) {
        if (mTencent == null) {
            return false;
        }
        boolean ready = mTencent.isSessionValid()
                && mTencent.getQQToken().getOpenId() != null;
        if (!ready) {
            Toast.makeText(context, "login and get openId first, please!",
                    Toast.LENGTH_SHORT).show();
        }
        return ready;
    }


    /****************QQ分享******************/

    /**
     * QQ分享
     */
    public static void doShareToQQ(final Context mContext, final Tencent mTencent) {
        // QQ分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {

            @Override
            public void run() {
                if (null != mTencent) {
                    mTencent.shareToQQ((Activity) mContext, setParams(), qqShareListener);
                }
            }
        });
    }

    static IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {
            if (shareType != QQShare.SHARE_TO_QQ_TYPE_IMAGE) {
                Log.d("1***********" + "onCancel: ");
            }
        }

        @Override
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
            Log.d("2***********" + "onCancel: " + response.toString());
        }

        @Override
        public void onError(UiError e) {
            // TODO Auto-generated method stub
            Log.d("3***********" + "onCancel: " + e.errorMessage);
        }
    };


    /**
     * 设置分享内容
     */
    public static Bundle setParams() {

        Bundle params = new Bundle();

        if (shareType != QQShare.SHARE_TO_QQ_TYPE_IMAGE) {
            params.putString(QQShare.SHARE_TO_QQ_TITLE, "标题");
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://WWW.BAIDU.COM");
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "内容");
        }

        return params;
    }
}
