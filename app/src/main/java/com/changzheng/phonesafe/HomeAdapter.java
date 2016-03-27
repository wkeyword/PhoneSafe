package com.changzheng.phonesafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by changzheng on 16/3/27.
 */
public class HomeAdapter extends BaseAdapter {

    private Context context;
    private int[] iconId={R.drawable.safe,R.drawable.callmsgsafe,R.drawable.app,R.drawable.taskmanager,R.drawable.netmanager,R.drawable.trojan,R.drawable.sysoptimize,R.drawable.atools,R.drawable.settings};
    private String[] names={"手机防盗","通信卫士","软件管理","进程管理","流量统计","手机杀毒","缓存清理","高级工具","设置中心"};

    public HomeAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.home_grid_item,parent,false);
        TextView nameTv=(TextView)itemView.findViewById(R.id.name_tv);
        ImageView iconTv=(ImageView)itemView.findViewById(R.id.icon_iv);
        nameTv.setText(names[position]);
        iconTv.setImageResource(iconId[position]);
        return itemView;
    }
}
