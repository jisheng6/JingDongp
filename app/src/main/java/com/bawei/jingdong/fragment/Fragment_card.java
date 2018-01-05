package com.bawei.jingdong.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong.R;
import com.bawei.jingdong.activity.LoginActivity;
import com.bawei.jingdong.adapter.ShopAdapter;
import com.bawei.jingdong.bean.ShopBean;
import com.bawei.jingdong.present.CarePresenter;
import com.bawei.jingdong.view.CareViewCallBack;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class Fragment_card extends Fragment implements CareViewCallBack {
    private CarePresenter presenter;
    private ShopAdapter adapter;
    private CheckBox checkBoxAll;
    private TextView thirdTotalprice;
    private TextView thirdTotalnum;
    private TextView thirdSubmit;
    private LinearLayout thirdPayLinear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences sp = getActivity().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        if (uid == 0) {
            View view1 = inflater.inflate(R.layout.fragment_card2, container, false);
            Button but = view1.findViewById(R.id.but);
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            return view1;
        } else {
            View view = inflater.inflate(R.layout.fragment_card, container, false);
            RecyclerView thirdRecyclerview = view.findViewById(R.id.third_recyclerview);
            checkBoxAll = view.findViewById(R.id.third_allselect);
            thirdTotalprice = view.findViewById(R.id.third_totalprice);
            thirdTotalnum = view.findViewById(R.id.third_totalnum);
            thirdSubmit = view.findViewById(R.id.third_submit);
            thirdPayLinear = view.findViewById(R.id.third_pay_linear);
            presenter = new CarePresenter(this);
            presenter.getData();
            adapter = new ShopAdapter(getActivity());
            LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


            thirdRecyclerview.setLayoutManager(manager);
            thirdRecyclerview.setAdapter(adapter);


            adapter.setListener(new ShopAdapter.UpdateUiListener() {
                @Override
                public void setTotal(String total, String num, boolean allCheck) {


                    checkBoxAll.setChecked(allCheck);
                    thirdTotalnum.setText(num);
                    thirdTotalprice.setText(total);
                }
            });


            return view;
        }
    }

    @Override
    public void success(ShopBean bean) {
        adapter.add(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void failure() {
        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        presenter.getData();
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        presenter.detach();
//    }
    @OnClick(R.id.third_allselect)
    public void onViewClicked() {

        adapter.selectAll(checkBoxAll.isChecked());
    }

}
