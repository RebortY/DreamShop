package com.dream.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dream.R;
import com.dream.main.seach.SeachActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;


public class MainActivity extends FragmentActivity implements MainLogicListener {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.rg_tab)
    RadioGroup rgTab;
    @Bind(R.id.image_menu)
    ImageButton menu;
    @Bind(R.id.image_seach)
    ImageButton seach;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DreamApplication.getApp().eventBus().register(this);
        View view = DreamApplication.getApp().inflateViewAndBind(this, R.layout.activity_main, new MainPM(this));
        setContentView(view);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DreamApplication.getApp().eventBus().unregister(this);
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
        mNavigationDrawerFragment.close();
    }

    @OnClick({R.id.image_menu,R.id.image_seach})
    public void onClick(View view){
          switch (view.getId()){
              case R.id.image_seach:
                  Intent intent = new Intent(this , SeachActivity.class);
                  startActivity(intent);
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
                showtitle(true);
                break;
            case R.id.tab_publish://揭晓
                position = 1;
                showtitle(false);
                break;
            case R.id.tab_show://晒单
                position = 2;
                showtitle(false);
                break;
            case R.id.tab_account://我的
                position = 3;
                showtitle(false);
                break;
        }
        switchPage(position);
    }

    private void showtitle(boolean isv){
        seach.setVisibility(isv ? View.VISIBLE : View.INVISIBLE);
        menu.setVisibility(isv ? View.VISIBLE : View.INVISIBLE);
    }

    @Subcriber(tag = "showpublishall" , threadMode = ThreadMode.MainThread)
    public void showPublishAll(String args){
        radioChange(R.id.tab_publish);
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
