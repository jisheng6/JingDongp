package com.bawei.jingdong.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong.MainActivity;
import com.bawei.jingdong.R;
import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bao.MD5Util;
import com.bawei.jingdong.bean.LoginBean;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

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

public class LoginActivity extends Activity {
    @BindView(R.id.denglu_cha)
    TextView dengluCha;
    @BindView(R.id.denglu_word)
    EditText dengluWord;
    @BindView(R.id.denglu_pwd)
    EditText dengluPwd;
    @BindView(R.id.denglu_btn)
    Button dengluBtn;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.xmm)
    ImageView xmm;
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
//    @BindView(R.id.zhuan)
//    Button zhuan;
//    @BindView(R.id.denglu_md)
//    TextView dengluMd;
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private String pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this.getApplicationContext());
        final String trim = dengluPwd.getText().toString().trim();
//        zhuan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String secretSign = MD5Util.getStringMD5(trim);
//               // String secretSign = MD5Util.getStringMD5_16(trim);
//                dengluMd.setText(secretSign);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.denglu_cha, R.id.denglu_btn, R.id.zhuce, R.id.xmm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu_cha:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.denglu_btn:
                String p = dengluWord.getText().toString().trim();
                pa = dengluPwd.getText().toString().trim();
                IGeation.api.getLogin(p, pa)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LoginBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginBean loginBean) {
                                String code = loginBean.getCode();
                                if (code.equals("0")) {
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    int uid = loginBean.getData().getUid();
                                    String mobile = loginBean.getData().getMobile();
                                    SharedPreferences sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putString("name", mobile);
                                    edit.putInt("uid", uid);
                                    edit.commit();
                                    System.out.println("传值1" + edit.putString("name", mobile));
                                    System.out.println("传值2" + edit.putString("uid", uid + ""));
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
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
            case R.id.zhuce:
                Intent intenta = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intenta);
                break;
            case R.id.xmm:
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this, "all", mIUiListener);
                break;
        }


    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }
    }
}
