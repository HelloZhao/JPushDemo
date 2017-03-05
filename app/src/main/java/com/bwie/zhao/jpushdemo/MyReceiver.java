package com.bwie.zhao.jpushdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2017/1/11.
 */

public class MyReceiver extends BroadcastReceiver {

    private static List<OnRefresh> mList = new ArrayList<>();
    @Override
    public void onReceive(Context context, Intent intent) {
        intent.getAction();
        Bundle bundle = intent.getExtras();
        String message = (String) bundle.get(JPushInterface.EXTRA_ALERT);

//        String msg = (String) bundle.get(JPushInterface.EXTRA_MESSAGE);
//        if (!isEmpty(msg)) {
//            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//            Notification notification = new Notification();
//            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            notification.tickerText = message;
//            notification.defaults = Notification.DEFAULT_LIGHTS;
//            notification.defaults = Notification.DEFAULT_LIGHTS;
//            notification.sound = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6");
//            manager.notify(0, notification);
//        }
        for (OnRefresh fresh: mList){
            if(mList!=null){
                fresh.onFlush(message);
            }
        }
    }
    public static void flush(OnRefresh flu){
        mList.add(flu);
    }
    public static void remove(OnRefresh flu){
        mList.remove(flu);
    }

    public interface OnRefresh{
        void onFlush(String content);
    }
}
