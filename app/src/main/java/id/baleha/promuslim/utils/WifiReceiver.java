package id.baleha.promuslim.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WifiReceiver extends BroadcastReceiver {

    private static boolean networkActive;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager coMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = coMan.getActiveNetworkInfo();
        if(netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI)
            networkActive = true;
        else networkActive = false;
    }

    public static boolean isNetworkEnable(){
        return networkActive;
    }

}
