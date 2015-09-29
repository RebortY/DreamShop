package com.dream.main.tabme.account;

import android.support.v4.app.Fragment;

import com.dream.R;
import com.dream.main.base.BaseFragmentAct;
import com.dream.main.tabme.record.MyDreamRecordingFragment;
import com.dream.main.tabme.record.MyDreamRecordunFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/13 15:53
 * 账户明细
 */
public class AccountAct  extends BaseFragmentAct {


    @Override
    public int[] initTabText() {
        return new int[]{R.string.tv_account_chongzhi_detail, R.string.tv_account_xiaofei_detail};
    }

    @Override
    public List<Fragment> initFrament() {
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new ChongzhiDetailFragment());
        list.add(new XiaofeiDetailFragment());
        return list;
    }

    @Override
    public Object initPM() {
        return null;
    }

    @Override
    public int titleValue() {
        return R.string.tv_user_detail;
    }
}
