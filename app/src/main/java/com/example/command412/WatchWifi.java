package com.example.command412;

/**
 * Created by ASUS on 2016/4/6.
 */

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WatchWifi extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watch);
        Log.v("123", "123");
        TextView SSID = (TextView) findViewById(R.id.SSID);
        TextView BSSID = (TextView) findViewById(R.id.BSSID);
        TextView IpAddress = (TextView) findViewById(R.id.IpAddress);


        WifiManager  wifiManager=(WifiManager)super.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        SSID.setText("SSID :" + wifiInfo.getSSID());
        BSSID.setText("BSSID :" + wifiInfo.getBSSID());
        IpAddress.setText("level :" + wifiInfo.getIpAddress());

    }



}
