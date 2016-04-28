package com.example.command412;

/**
 * Created by ASUS on 2016/4/6.
 */

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddWifiActivity extends Activity{

    private WifiManager wifiManager;
    private WifiInfo wifiInfo;
    List<ScanResult> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_list);

        TextView txtWifi = (TextView)findViewById(R.id.txtWifi);
        wifiManager=(WifiManager)super.getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        txtWifi.setText(wifiInfo.getSSID()+"  已连接");

        ListView list = (ListView)findViewById(R.id.listWifi);

        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub

            }
        });

        init();




    }


    private void init() {

        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        list = wifiManager.getScanResults();
        ListView listView = (ListView) findViewById(R.id.listWifi);

        if (list == null) {
            Toast.makeText(this, "wifi未打开！", Toast.LENGTH_LONG).show();
        }else {
            listView.setAdapter(new MyAdapter(this,list));
        }

    }

    public class MyAdapter extends BaseAdapter {

        LayoutInflater inflater;
        List<ScanResult> list;
        public MyAdapter(Context context, List<ScanResult> list) {
            // TODO Auto-generated constructor stub
            this.inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            View view = null;
            view = inflater.inflate(R.layout.wifi_list_item, null);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            TextView signalStrenth = (TextView) view.findViewById(R.id.signal_strenth);

            ScanResult scanResult = list.get(position);
            wifiInfo = wifiManager.getConnectionInfo();
            if(!scanResult.SSID.equals(wifiInfo.getSSID())){
                textView.setText(scanResult.SSID);
                signalStrenth.setText(String.valueOf(Math.abs(scanResult.level)));
            }else{
                textView.setText("");
                signalStrenth.setText("");
            }

//            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
//            //判断信号强度，显示对应的指示图标
//            if (Math.abs(scanResult.level) > 100) {
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_0));
//            } else if (Math.abs(scanResult.level) > 80) {
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_1));
//            } else if (Math.abs(scanResult.level) > 70) {
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_1));
//            } else if (Math.abs(scanResult.level) > 60) {
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_2));
//            } else if (Math.abs(scanResult.level) > 50) {
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_3));
//            } else {
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_4));
//            }
            return view;
        }
    }

}

