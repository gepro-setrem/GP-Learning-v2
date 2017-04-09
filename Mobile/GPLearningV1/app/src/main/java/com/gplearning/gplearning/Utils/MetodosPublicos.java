package com.gplearning.gplearning.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Mateus on 09/04/2017.
 */

public class MetodosPublicos {

    public static final boolean IsConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            Boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            Boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected || mobileConnected) {
                return true;
            }
        }
        return false;
    }

}
