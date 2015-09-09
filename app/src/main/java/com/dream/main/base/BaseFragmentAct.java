package com.dream.main.base;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.R;

import java.util.List;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/9 19:59
 */
public abstract class BaseFragmentAct extends BaseActivity {

    private ViewPager viewPager;// 页卡内容
    private ImageView imageView;// 动画图片
    private TextView tab1;// 推荐
    private TextView tab2;// 商品

    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private int selectedColor;
    private int unSelectedColor;
    /**
     * 页卡总数
     **/
    private static final int pageSize = 2;


    @Override
    public int getLayoutId() {
        return R.layout.base_fragment_act;
    }

    @Override
    public void initView() {
        selectedColor = getResources()
                .getColor(R.color.black);
        unSelectedColor = getResources().getColor(
                R.color.gray_row_color);

        initImageView();
        initTextView(initTabText());
        initViewPager(initFrament());

    }

    public abstract int[] initTabText();

    public abstract List<Fragment> initFrament();

    /**
     * 初始化Viewpager页
     */
    private void initViewPager(List<Fragment> fragments) {
        viewPager = (ViewPager) findViewById(R.id.vPager);
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),
                fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化头标
     */
    public void initTextView(int[] res) {

        tab1 = (TextView) findViewById(R.id.tab_1);
        tab2 = (TextView) findViewById(R.id.tab_2);

        tab1.setTextColor(selectedColor);
        tab2.setTextColor(unSelectedColor);

        tab1.setText(res[0]);
        tab2.setText(res[1]);

        tab1.setOnClickListener(new MyOnClickListener(0));
        tab2.setOnClickListener(new MyOnClickListener(1));
    }

    /**
     * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果
     */

    private void initImageView() {
        imageView = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(),
                R.drawable.tab_focus).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / pageSize - bmpW) / 2;// 计算偏移量--(屏幕宽度/页卡总数-图片实际宽度)/2
        // = 偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
    }

    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {

            switch (index) {
                case 0:
                    tab1.setTextColor(selectedColor);
                    tab2.setTextColor(unSelectedColor);
                    break;
                case 1:
                    tab2.setTextColor(selectedColor);
                    tab1.setTextColor(unSelectedColor);
                    break;
            }
            viewPager.setCurrentItem(index);
        }

    }

    /**
     * 为选项卡绑定监听器
     */
    public class MyOnPageChangeListener implements OnPageChangeListener {

        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量

        public void onPageScrollStateChanged(int index) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int index) {
            Animation animation = new TranslateAnimation(one * currIndex, one
                    * index, 0, 0);
            currIndex = index;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            imageView.startAnimation(animation);

            switch (index) {
                case 0:
                    tab1.setTextColor(selectedColor);
                    tab2.setTextColor(unSelectedColor);
                    break;
                case 1:
                    tab2.setTextColor(selectedColor);
                    tab1.setTextColor(unSelectedColor);
                    break;
            }
        }
    }

    /**
     * 定义适配器
     */
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        /**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }

    public interface BaseFragmentInterFace {

    }

}
