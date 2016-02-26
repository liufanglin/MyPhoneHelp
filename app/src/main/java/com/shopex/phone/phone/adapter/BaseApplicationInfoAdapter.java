package com.shopex.phone.phone.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopex.phone.phone.R;
import com.shopex.phone.phone.bean.PackageInfo;

import java.net.ContentHandler;
import java.util.List;

/**
 * Created by samsung on 2016/2/17.
 */
public class BaseApplicationInfoAdapter extends BaseAdapter{
    private List<PackageInfo> list;
    private Context context;
    private LayoutInflater inflater=null;
    private boolean isLocalApp=true;
    public BaseApplicationInfoAdapter(Context context,List<PackageInfo> list,boolean isLocalApp){
        this.list=list;
        this.context=context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.isLocalApp=isLocalApp;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=null;
        ViewHolder holder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.activity_allapp_list,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.appicon.setImageDrawable(list.get(position).getIcon());
        holder.tvPackageName.setText(list.get(position).getPackName());
        holder.tvAppLable.setText(list.get(position).getLabel());
        if (isLocalApp){
            holder.btnStop.setText("卸载");

        }else {
            holder.btnStop.setText("暂停");
        }
        holder.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= list.get(position).getIntent();
                intent.setAction(Intent.ACTION_DELETE);
                Uri packageUri=Uri.parse("package:"+list.get(position).getPackName());
                intent.setData(packageUri);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    class ViewHolder{
        ImageView appicon;
        TextView tvAppLable;
        TextView tvPackageName;
        Button btnStop;
        public ViewHolder(View view){
            this.appicon= (ImageView) view.findViewById(R.id.iv_icon);
            this.tvAppLable= (TextView) view.findViewById(R.id.tv_lable);
            this.tvPackageName= (TextView) view.findViewById(R.id.tv_packag);
            this.btnStop= (Button) view.findViewById(R.id.btn_stop);
        }
    }
}
