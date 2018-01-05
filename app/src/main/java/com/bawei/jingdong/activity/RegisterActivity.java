package com.bawei.jingdong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong.R;
import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bean.RegistBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adminjs on 2017/12/28.
 */

public class RegisterActivity extends Activity {
    @BindView(R.id.zhuce_cha)
    TextView zhuceCha;
    @BindView(R.id.zhuce_word)
    EditText zhuceWord;
    @BindView(R.id.zhuce_pwd)
    EditText zhucePwd;
    @BindView(R.id.zhuce_btn)
    Button zhuceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.zhuce_cha, R.id.zhuce_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce_cha:
                finish();
                break;
            case R.id.zhuce_btn:
                String p = zhuceWord.getText().toString().trim();
                String pa = zhucePwd.getText().toString().trim();
                IGeation.api.getRegist(p,pa)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RegistBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RegistBean registBean) {
                                String code = registBean.getCode();
                                if (code.equals("0")){
                                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
        }
    }
}
