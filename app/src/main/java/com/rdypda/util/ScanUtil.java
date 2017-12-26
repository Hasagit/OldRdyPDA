package com.rdypda.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DengJf on 2017/12/15.
 */

public class ScanUtil {
    private BroadcastReceiver receiver;
    private Context context;

    public ScanUtil(Context context) {
        this.context = context;
    }

    public void open(){
        Intent intent = new Intent ("ACTION_BAR_SCANCFG");
        //开启
        intent.putExtra("EXTRA_SCAN_POWER", 1);
        //输出模式为广播
        intent.putExtra("EXTRA_SCAN_MODE", 3);
        //关闭自动换行
        intent.putExtra("EXTRA_SCAN_AUTOENT", 0);
        //声音提示
        intent.putExtra("EXTRA_SCAN_NOTY_SND",1);
        context.sendBroadcast(intent);
    }

    public void close(){
        Intent intent = new Intent ("ACTION_BAR_SCANCFG");
        intent.putExtra("EXTRA_SCAN_POWER", 0);
        context.sendBroadcast(intent);
        if (receiver!=null){
            context.unregisterReceiver(receiver);
        }
    }


    public void setOnScanListener(final OnScanListener scanListener){
        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String scanResult=intent.getStringExtra("SCAN_BARCODE1");
                final String scanStatus=intent.getStringExtra("SCAN_STATE");
                if("ok".equals(scanStatus)){
                    scanListener.onSuccess(scanResult);
                }else{
                    scanListener.onFail(scanStatus);
                }
            }
        };
        IntentFilter receiverIntent=new IntentFilter("nlscan.action.SCANNER_RESULT");
        context.registerReceiver(receiver,receiverIntent);
    }


    public interface OnScanListener{
        void onSuccess(String result);

        void onFail(String error);
    }


}
