package com.dream.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dream.R;
import com.dream.net.NetSuccess;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, MainLogicListener {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.rg_tab)
    RadioGroup rgTab;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = DreamApplication.getApp().inflateViewAndBind(this, R.layout.activity_main, new MainPM(this));
        setContentView(view);
        ButterKnife.bind(this);
        initView();

        //注册到eventBus
        DreamApplication.getApp().eventBus().register(this);
        LoginHandler.getinstance().login("13401165595", "123456");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //切记在 onDestroy 的时候，取消注册。 否则会造成内存泄露 ，在Fragment 中，如果fragment 关闭也要执行此方法
        DreamApplication.getApp().eventBus().unregister(this);
    }

    //此方法是 测试范例， 请最后将此登录处理，放到 登录页面中
    @Subcriber(tag = LoginTag.LOGIN , threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp){
        if(RespCode.SUCCESS.equals(resp.getErrorCode())){
            Toast.makeText(this , "登录成功", Toast.LENGTH_SHORT).show();;
        }
    }

    private void initView(){
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        FragmentManager fm = getSupportFragmentManager();
        viewpager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int index) {
                return AbstractTabFragment.newInstance(index, getLogicInstance());
            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switchRadio(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewpager.setOffscreenPageLimit(4);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //TODO 选择栏目后从此处处理
    }

    @OnClick({R.id.image_menu,R.id.image_seach})
    public void onClick(View view){
          switch (view.getId()){
              case R.id.image_seach:
                  Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();
                  break;
              case R.id.image_menu:
                  mNavigationDrawerFragment.open();
                  break;
          }
    }

    @Override
    public void radioChange(int id) {
        int position = 0 ;
        switch (id) {
            case R.id.tab_main://首页
                position = 0;
                break;
            case R.id.tab_publish://揭晓
                position = 1;
                break;
            case R.id.tab_show://晒单
                position = 2;
                break;
            case R.id.tab_account://我的
                position = 3;
                break;
        }
        switchPage(position);
    }

    private void switchPage(int position){
        viewpager.setCurrentItem(position);
    }

    private void switchRadio(int radioPosition){
        ((RadioButton)rgTab.getChildAt(radioPosition)).toggle();
    }

    private MainLogicListener getLogicInstance(){
        return this;
    }

}
