package com.changzheng.phonesafe;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private List<ImageView> mPageList;
    private Context context;
    private Button mEnterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getSupportActionBar().hide();
        context=this;
        //应用视图页
        mEnterBtn=(Button)findViewById(R.id.guide_enter_btn);
        final ViewPager mViewPager=(ViewPager)findViewById(R.id.guide_vp);
        initPage();//初始化数据
//        ListView 展示批量的数据步骤:1.列表项布局    2.初始化数据 List<Map> 3.设置适配器  4.监听列表项
//        初始化数据,装配数据
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mPageList==null?0:mPageList.size();//视图项的数量
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
//              当前的对象是不是一个视图
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//               销毁传递过来的position位置的视图项
                container.removeView(mPageList.get(position));
//                super.destroyItem(container, position, object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                初始化传递过来的position位置的视图项
                container.addView(mPageList.get(position));
                return mPageList.get(position);
            }
        });
//        做监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==mPageList.size()-1){
                    mEnterBtn.setVisibility(View.VISIBLE);
                }
                else {
                    mEnterBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
//初始化视图页的数据
    private void initPage() {
        mPageList=new ArrayList<ImageView>();
        ImageView imageView1=new ImageView(context);
        imageView1.setBackgroundResource(R.drawable.guide_1);
        mPageList.add(imageView1);


        ImageView imageView2=new ImageView(context);
        imageView2.setBackgroundResource(R.drawable.guide_2);
        mPageList.add(imageView2);

        ImageView imageView3=new ImageView(context);
        imageView3.setBackgroundResource(R.drawable.guide_3);
        mPageList.add(imageView3);


    }
//点击开始体验按钮
    public void enterSystem(View view) {
        Intent intent=new Intent(this,SplashActivity.class);
        startActivity(intent);
        CacheUtils.putBoolean(context,"is_first_use",false);
        finish();
    }
}
