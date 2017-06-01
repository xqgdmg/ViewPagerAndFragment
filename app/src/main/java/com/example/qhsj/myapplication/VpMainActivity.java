package com.example.qhsj.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VpMainActivity extends FragmentActivity {

    private FrameLayout mFlContainer;
    private RadioButton mRadioButton01;
    private RadioButton mRadioButton02;
    private RadioButton mRadioButton03;
    private RadioButton mRadioButton04;
    private ViewPager mViewPager;
    private List<Fragment> fragments;
    // MyPagerAdapter 里面只能放 View，不能是 Viewgroup
    private List<View> views = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_main);

        initView();

        initListener();

        init();

    }

    private void init() {

         // MyPagerAdapter 用到
        for (int i = 0;i < 4 ; i ++){
            TextView tv = new TextView(VpMainActivity.this);
            tv.setText("TextView" + i);

             // 如果没有在xml中写过，这个东西就会获取不到，后面调用方法直接出现空指针
//            ViewGroup.LayoutParams layoutParams = tv.getLayoutParams();

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            tv.setLayoutParams(layoutParams);

            tv.setTextColor(Color.parseColor("#55ff0000"));

            tv.setGravity(Gravity.CENTER);

            views.add(tv);
        }

        fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment01());
        fragments.add(new Fragment02());
        fragments.add(new Fragment03());
        fragments.add(new Fragment04());

//        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
//        mViewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager()));
        mViewPager.setAdapter(new MyPagerAdapter()); // 这个是 View，不可以是 Fragment

        mViewPager.setOffscreenPageLimit(fragments.size());
    }

    private void initListener() {
        mRadioButton01.setOnClickListener(new MyOnclickListener());
        mRadioButton02.setOnClickListener(new MyOnclickListener());
        mRadioButton03.setOnClickListener(new MyOnclickListener());
        mRadioButton04.setOnClickListener(new MyOnclickListener());
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mRadioButton01 = (RadioButton) findViewById(R.id.rb1);
        mRadioButton02 = (RadioButton) findViewById(R.id.rb2);
        mRadioButton03 = (RadioButton) findViewById(R.id.rb3);
        mRadioButton04 = (RadioButton) findViewById(R.id.rb4);
    }

    private class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rb1:
                    mViewPager.setCurrentItem(0,false); // 第二个参数是 false，不会出现滑动的效果
                    break;
                case R.id.rb2:
                    mViewPager.setCurrentItem(1,false);
                    break;
                case R.id.rb3:
                    mViewPager.setCurrentItem(2,false);
                    break;
                case R.id.rb4:
                    mViewPager.setCurrentItem(3,false);
                    break;
            }
        }
    }

    /*
     * PagerAdapter
     */
    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (View)object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
//            return super.instantiateItem(container, position);  // 不用 super，好像super直接是抛异常
        }

//        @Override
//        public Object instantiateItem(View container, int position) {  // View container
//            return super.instantiateItem(container, position);
//        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object); // 不用 super，好像super直接是抛异常
            View view = views.get(position);
            container.removeView(view);
        }

//        @Override
//        public void destroyItem(View container, int position, Object object) {  // View container
//            super.destroyItem(container, position, object);
//        }
    }

    /*
     * FragmentPagerAdapter
     */
    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    /*
     * FragmentStatePagerAdapter
     */
    class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter{

        public MyFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
