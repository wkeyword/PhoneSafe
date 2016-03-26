package com.changzheng.phonesafe;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by changzheng on 16/3/25.
 */
public class CacheUtils {
    public static final String CONFIG_SP="config_sp";// config_sp.xml 文件  存放位置 ：/data/data/《包名》/shared_prefes
    public static final String IS_FIRST_USE="is_first_use";// 是否第一次使用应用
    public static final String APK_UPDATE = "apk_update";//是否要更新版本
    private static SharedPreferences mSp;
    private static SharedPreferences getPreferencs(Context context){
        if(mSp==null){
            mSp=context.getSharedPreferences(CONFIG_SP, Context.MODE_PRIVATE);
        }
        return mSp;
    }
    // 保存布尔数据
    public static void putBoolean(Context context,String key, boolean value) {
        SharedPreferences sp=getPreferencs(context);
        sp.edit().putBoolean(key, value).commit();
    }
    // 取布尔数据 ,返回的是false 默认值
    public static boolean getBoolean(Context context,String key) {
        SharedPreferences sp=getPreferencs(context);
        return sp.getBoolean(key, false);
    }
    // 取布尔数据 ,默认返回的是false
    public static boolean getBoolean(Context context,String key,boolean defvalue) {
        SharedPreferences sp=getPreferencs(context);
        return sp.getBoolean(key, defvalue);
    }
    // 保存字符串
    public static void putString(Context context,String key, String value) {
        SharedPreferences sp=getPreferencs(context);
        sp.edit().putString(key, value).commit();
    }
    // 取字符串数据 ,默认返回的是 null
    public static String getString(Context context,String key) {
        SharedPreferences sp=getPreferencs(context);
        return sp.getString(key, null);
    }
    // 取字符串数据 ,返回的是传递过来的值
    public static String getString(Context context,String key,String defvalue) {
        SharedPreferences sp=getPreferencs(context);
        return sp.getString(key, defvalue);
    }
}
