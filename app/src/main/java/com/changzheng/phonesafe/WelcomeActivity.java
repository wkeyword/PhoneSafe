package com.changzheng.phonesafe;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
//        引用图片
        ImageView welcomeImg= (ImageView) findViewById(R.id.welcome_iv);

//        设置动画
        AnimationSet set=new AnimationSet(false);

        RotateAnimation rotateAnimation=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(3000);//设置时长
        rotateAnimation.setFillAfter(true);//设置为填充效果
        set.addAnimation(rotateAnimation);//添加旋转动画到集合中

        //设置缩放动作
//        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1,welcomeImg.getWidth()/2,welcomeImg.getHeight()/2);

        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        set.addAnimation(scaleAnimation);

        Animation animation=AnimationUtils.loadAnimation(this, R.anim.welcome_alpha);
        set.addAnimation(animation);
        //添加透明度动画
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ToastUtils.show(getApplicationContext(),"动画巡展结束!");
                if (CacheUtils.getBoolean((WelcomeActivity.this),CacheUtils.IS_FIRST_USE,true)){
                    Intent intent=new Intent();
                    intent.setClass(WelcomeActivity.this,GuideActivity.class);
                    startActivity(intent);
                    finish();//欢迎界面可以设置standard模式
                }else {
                    Intent intent=new Intent();
                    intent.setClass(WelcomeActivity.this,SplashActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
//                Toast.makeText(getApplicationContext(),"进入界面",Toast.LENGTH_SHORT).show();

            }
        });

        welcomeImg.startAnimation(set);
    }
}
