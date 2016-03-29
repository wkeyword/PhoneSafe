package com.changzheng.phonesafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import dalvik.bytecode.OpcodeInfo;

public class SplashActivity extends AppCompatActivity {
    protected static final int MSG_SHOW_DIALOG=1;//显示升级对话框标记
    protected static final int MSG_ENTER_HOME=2;//进入home界面标记
    protected static final int MSG_SERVER_ERROR=3;//访问服务器端错误标记
    private TextView mVersionTv;//版本号文本控件
    private ProgressBar mProgressBar;
    private Context context;
    private int newVersionCode;
    private String apkUrl;
    private String versionDes;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_ENTER_HOME:
                    enterHome();
                    break;
                case MSG_SHOW_DIALOG:
                    showUpdateDialog();
                    break;
                case MSG_SERVER_ERROR:
                    enterHome();
                    break;
                default:
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        initvVew();
        if (CacheUtils.getBoolean(context,CacheUtils.APK_UPDATE,true)){
            checkUpdate();//检查更新
        }else {
            new Thread(){
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            enterHome();
                        }
                    });
                }
            }.start();
        }

    }

    private void checkUpdate() {
        new Thread(){
            @Override
            public void run() {
                long startTime=System.currentTimeMillis();
                Message message=new Message();
                try {
                    URL url=new URL(Constants.SERVER_VERSION_URL);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    int resultCode=conn.getResponseCode();
                    if (resultCode==HttpURLConnection.HTTP_OK){
                        InputStream is=conn.getInputStream();
                        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                        String jsonResult=reader.readLine();
                        JSONObject jsonObject=new JSONObject(jsonResult);
                        newVersionCode=jsonObject.getInt("code");
                        apkUrl=jsonObject.getString("apkurl");
                        versionDes=jsonObject.getString("des");

                        if (newVersionCode>getVersionCode()){
                            message.what=MSG_SHOW_DIALOG;
                        }else {
                            message.what=MSG_ENTER_HOME;
                        }

                    }else {
                        message.what=MSG_SERVER_ERROR;
                    }
                }catch (Exception e){
                    message.what=MSG_SERVER_ERROR;
                    e.printStackTrace();
                }finally {
                    long durationTime=System.currentTimeMillis()-startTime;
                    if (durationTime<2000){
                        SystemClock.sleep(2000-durationTime);
                    }
                    handler.sendMessage(message);
                }
            }
        }.start();
    }

    private void initvVew() {
        context=this;
        mVersionTv=(TextView)findViewById(R.id.version_tv);
        mProgressBar=(ProgressBar)findViewById(R.id.download_apk_pb);
        mVersionTv.setText("版本号:" + getVersionCode());
    }

    private int getVersionCode() {
        PackageManager packageManager=getPackageManager();
        try {
            PackageInfo packageInfo=packageManager.getPackageInfo(getPackageName(),PackageManager.GET_ACTIVITIES);
            return packageInfo.versionCode;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return 0;
    }

    private void showUpdateDialog(){
        new AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_launcher)
                .setTitle("新版本号:"+newVersionCode)
                .setMessage(versionDes)
                .setPositiveButton("升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        downloadApk();
                        ToastUtils.show(context, "下载APK");
                    }
                })
                .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        enterHome();
                    }
                }).create().show();
    }

    private void enterHome() {
        Intent intent=new Intent(context,HomeActivity.class);
        startActivity(intent);
        finish();

    }

    private void downloadApk(){

    }

}
