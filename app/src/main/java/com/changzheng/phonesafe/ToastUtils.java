package com.changzheng.phonesafe;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by changzheng on 16/3/26.
 */
public class ToastUtils {
    public static void show(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
