package com.shopex.phone.phone;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shopex.phone.phone.activity.AccountSetting;
import com.shopex.phone.phone.activity.AllApp;
import com.shopex.phone.phone.activity.InterceptionActivity;
import com.shopex.phone.phone.common.BaseActivity;
import com.shopex.phone.phone.library.constants.AppConstants;

public class MainActivity extends BaseActivity {
    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLeftBackImageText("手机助手", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setRightImage(R.mipmap.cc, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        gridView= (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new MyBaseAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(MainActivity.this,AccountSetting.class);
                    startActivity(intent);
                } if (position == 2) {
                    Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, InterceptionActivity.class);
                    startActivity(intent);
                } if(position ==4){
                    Intent intent=new Intent(MainActivity.this,AllApp.class);
                    startActivity(intent);

                }
            }
        });

    }
    public class MyBaseAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        public MyBaseAdapter(){
            inflater= (LayoutInflater) MainActivity.this.getSystemService(MainActivity.this.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return AppConstants.MAIN_IMAGE.length;
        }

        @Override
        public Object getItem(int position) {
            return AppConstants.MAIN_TEXT[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView==null){
                view=inflater.inflate(R.layout.activity_gridview_item,null);
            }else {
                view=convertView;
            }
            ImageView imageView= (ImageView) view.findViewById(R.id.iv_show);
            imageView.setImageResource(AppConstants.MAIN_IMAGE[position]);
            TextView textViewTitle= (TextView) view.findViewById(R.id.tv_title);
            textViewTitle.setText(AppConstants.MAIN_TEXTTITLE[position]);
            TextView textView= (TextView) view.findViewById(R.id.tv_sizetitle);
            textView.setText(AppConstants.MAIN_TEXT[position]);
            return view;
        }
    }


}
