package com.bawei.jingdong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bawei.jingdong.MainActivity;
import com.bawei.jingdong.R;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class CainActivity extends Activity{
    private int time = 3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (time>0){
                time--;
                handler.sendEmptyMessageDelayed(0,1000);
            }else{
                Intent intent = new Intent(CainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cain);
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
