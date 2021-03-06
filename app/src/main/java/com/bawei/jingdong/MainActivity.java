package com.bawei.jingdong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawei.jingdong.adapter.MyAdapter;
import com.bawei.jingdong.bao.IsNetworkable;
import com.bawei.jingdong.bao.MyViewPager;
import com.bawei.jingdong.fragment.Fragment_faxian;
import com.bawei.jingdong.fragment.Fragment_fclass;
import com.bawei.jingdong.fragment.Fragment_mine;
import com.bawei.jingdong.fragment.Fragment_card;
import com.bawei.jingdong.fragment.Fragment_shouye;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.pager)
    MyViewPager pager;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private ArrayList<Fragment> list;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        IsNetworkable.isNetworkAvailable(this);
        list = new ArrayList<>();
        list.add(new Fragment_shouye());
        list.add(new Fragment_fclass());
        list.add(new Fragment_faxian());
        list.add(new Fragment_card());
        list.add(new Fragment_mine());
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                radioGroup.check(radioGroup.getChildAt(position).getId());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        pager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return true;
//            }
//        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.shouye:
                        pager.setCurrentItem(0, false);
                        break;
                    case R.id.fenlei:
                        pager.setCurrentItem(1, false);
                        break;
                    case R.id.faxian:
                        pager.setCurrentItem(2, false);
                        break;
                    case R.id.card:
                        pager.setCurrentItem(3, false);
                        break;
                    case R.id.my:
                        pager.setCurrentItem(4, false);
                        break;
                }
            }
        });
        pager.setOffscreenPageLimit(5);
    }
    @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                exit();
                return false;
            }
            return super.onKeyDown(keyCode, event);
        }

        public void exit() {
            if ((System.currentTimeMillis() - exitTime) > 5000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }
}
