package com.example.qhsj.myapplication;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class FmMainActivity extends FragmentActivity {

    private FrameLayout mFlContainer;
    private RadioButton mRadioButton01;
    private RadioButton mRadioButton02;
    private RadioButton mRadioButton03;
    private RadioButton mRadioButton04;
    private Fragment01 fragment01;
    private Fragment02 fragment02;
    private Fragment03 fragment03;
    private Fragment04 fragment04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initListener();

        init();
    }

    private void initListener() {
        mRadioButton01.setOnClickListener(new MyOnclickListener());
        mRadioButton02.setOnClickListener(new MyOnclickListener());
        mRadioButton03.setOnClickListener(new MyOnclickListener());
        mRadioButton04.setOnClickListener(new MyOnclickListener());
    }

    private void init() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (fragment01 == null){
            fragment01 = new Fragment01();
        }
        transaction.add(R.id.fl_contain,fragment01); // , "Fragment01"

        if (fragment02 == null){
            fragment02 = new Fragment02();
        }
        transaction.add(R.id.fl_contain,fragment02);

        if (fragment03 == null){
            fragment03 = new Fragment03();
        }
        transaction.add(R.id.fl_contain,fragment03);

        if (fragment04 == null){
            fragment04 = new Fragment04();
        }
        transaction.add(R.id.fl_contain,fragment04);

        hideFragment(transaction);

        transaction.show(fragment01);

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(fragment01 != null){
            transaction.hide(fragment01);
        }
        if(fragment02 != null){
            transaction.hide(fragment02);
        }
        if(fragment03 != null){
            transaction.hide(fragment03);
        }
        if(fragment04 != null){
            transaction.hide(fragment04);
        }
    }

    private void initView() {
        mFlContainer = (FrameLayout) findViewById(R.id.fl_contain);
        mRadioButton01 = (RadioButton) findViewById(R.id.rb1);
        mRadioButton02 = (RadioButton) findViewById(R.id.rb2);
        mRadioButton03 = (RadioButton) findViewById(R.id.rb3);
        mRadioButton04 = (RadioButton) findViewById(R.id.rb4);
    }

    private class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (v.getId()) {
                case R.id.rb1:
                    if (fragment01 == null){
                        fragment01 = new Fragment01();
                    }
//                    transaction.replace(R.id.fl_contain,fragment01);

                    hideFragment(transaction);
                    transaction.show(fragment01);
                    break;
                case R.id.rb2:
                    if (fragment02 == null){
                        fragment02 = new Fragment02();
                    }
//                    transaction.replace(R.id.fl_contain,fragment02);

                    hideFragment(transaction);
                    transaction.show(fragment02);
                    break;
                case R.id.rb3:
                    if (fragment03 == null){
                        fragment03 = new Fragment03();
                    }
//                    transaction.replace(R.id.fl_contain,fragment03);

                    hideFragment(transaction);
                    transaction.show(fragment03);
                    break;
                case R.id.rb4:
                    if (fragment04 == null){
                        fragment04 = new Fragment04();
                    }
//                    transaction.replace(R.id.fl_contain,fragment04);

                    hideFragment(transaction);
                    transaction.show(fragment04);
                    break;
            }
            transaction.commit();
        }
    }
}
