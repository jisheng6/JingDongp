package com.bawei.jingdong.bao;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Adminjs on 2018/1/10.
 */

public class IsNetworkable {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    Toast.makeText(context,"当前所连接的网络可用",Toast.LENGTH_SHORT).show();
                    // EventBus.getDefault().postSticky(new MessageEvent("当前所连接的网络可用"));
                    return true;
                }
            }
        }
        Toast.makeText(context,"网络不可用",Toast.LENGTH_SHORT).show();
        // EventBus.getDefault().postSticky(new MessageEvent("网络不可用"));
        return false;
    }
}
