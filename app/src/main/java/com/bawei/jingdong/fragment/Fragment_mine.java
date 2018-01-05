package com.bawei.jingdong.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong.MainActivity;
import com.bawei.jingdong.R;
import com.bawei.jingdong.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class Fragment_mine extends Fragment {
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.back)
    LinearLayout back;
    Unbinder unbinder;
    @BindView(R.id.head_iv)
    ImageView headIv;
    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        SharedPreferences sp=getActivity().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        name=sp.getString("name", null);
        System.out.println("传值"+sp.getString("name", null));
        if (name == null)
        {
            login.setText("未登录");
        }else{
            login.setText(name);

        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.head_iv, R.id.login, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_iv:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                Intent intentt = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentt);
                break;
            case R.id.back:
                SharedPreferences sp= getActivity().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                sp.edit().clear().commit();
                Toast.makeText(getActivity(), "已清空", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
