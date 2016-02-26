package com.shopex.phone.phone.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.shopex.phone.phone.R;
import com.shopex.phone.phone.adapter.BaseApplicationInfoAdapter;
import com.shopex.phone.phone.bean.PackageInfo;
import com.shopex.phone.phone.common.BaseActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by samsung on 2016/2/17.
 */
public class AllApp extends BaseActivity {
    private Button localApp;
    private Button systemApp;
    private ListView allApp;
    private List<PackageInfo> localList=new ArrayList<>();
    private List<PackageInfo> systemList=new ArrayList<>();
    private PackageManager pm=null;
    private BaseApplicationInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allapp);
        init();
        setLeftBackImageText("应用程序", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loadAppInfomation();
        adapter=new BaseApplicationInfoAdapter(AllApp.this, localList,true);
        allApp.setAdapter(adapter);

    }

    @Override
    public void init() {
        localApp= (Button) findViewById(R.id.btn_local);
        systemApp= (Button) findViewById(R.id.btn_system);
        allApp= (ListView) findViewById(R.id.lv_all);
        systemApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAppInfomation();
                adapter=new BaseApplicationInfoAdapter(AllApp.this, systemList,false);
                allApp.setAdapter(adapter);

            }
        });
        localApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAppInfomation();
                adapter = new BaseApplicationInfoAdapter(AllApp.this, localList, true);
                allApp.setAdapter(adapter);
            }
        });
    }

    //查询系统应用
    public void addSystemApp(android.content.pm.PackageInfo info,ResolveInfo riInfo){
        //拿到包名
        String packageName=info.packageName;
        //拿到应用程序信息
        ApplicationInfo appInfo=info.applicationInfo;
        //拿到应用程序启动名
        String activityName=appInfo.name;
        //拿到应用程序图标
        Drawable icon=appInfo.loadIcon(pm);
        //拿到应用程序名
        String appName=appInfo.loadLabel(pm).toString();
        Intent intent=new Intent();
        intent.setComponent(new ComponentName(riInfo.activityInfo.packageName, riInfo.activityInfo.name));
        PackageInfo myAppInfo=new PackageInfo();
        myAppInfo.setPackName(packageName);
        myAppInfo.setLabel(appName);
        myAppInfo.setIcon(icon);
        myAppInfo.setIntent(intent);
        systemList.add(myAppInfo);
    }
    //查询系统应用
    public void addLocalApp(android.content.pm.PackageInfo info,ResolveInfo riInfo){
        //拿到包名
        String packageName=info.packageName;
        //拿到应用程序信息
        ApplicationInfo appInfo=info.applicationInfo;
        //拿到应用程序启动名
        String activityName=appInfo.name;
        //拿到应用程序图标
        Drawable icon=appInfo.loadIcon(pm);
        //拿到应用程序名
        String appName=appInfo.loadLabel(pm).toString();
        Intent intent=new Intent();
        intent.setComponent(new ComponentName(riInfo.activityInfo.packageName, riInfo.activityInfo.name));
        PackageInfo myAppInfo=new PackageInfo();
        myAppInfo.setPackName(packageName);
        myAppInfo.setLabel(appName);
        myAppInfo.setIcon(icon);
        myAppInfo.setIntent(intent);
        localList.add(myAppInfo);
    }

    public void loadAppInfomation() {
      /*  List<PackageInfo> apps = new ArrayList<PackageInfo>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        Collections.sort(infos, new ResolveInfo.DisplayNameComparator(pm));
        if(infos != null) {
            apps.clear();
            for (int i = 0; i < infos.size(); i++) {
                PackageInfo app = new PackageInfo();
                ResolveInfo info = infos.get(i);
                app.setPackName(info.loadLabel(pm).toString());
                app.setLabel(info.resolvePackageName);
                app.setIcon(info.loadIcon(pm));
                Intent in=new Intent();
                in.setComponent(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
                app.setIntent(in);
                apps.add(app);
            }
        }
      return apps;*/
        //   PackageManager packageManager = getPackageManager();

        Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        pm=getPackageManager();
        List<ResolveInfo> listAllApps = pm.queryIntentActivities(mIntent, 0);
        // 判断是否系统应用：
        for (int i = 0; i < listAllApps.size(); i++) {
            ResolveInfo appInfo = listAllApps.get(i);
            String pkgName = appInfo.activityInfo.packageName;//获取包名
            //根据包名获取PackageInfo mPackageInfo;（需要处理异常）
            android.content.pm.PackageInfo mPackageInfo = null;
            try {
                mPackageInfo = getPackageManager().getPackageInfo(pkgName, 0);
                if ((mPackageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                    //第三方应用
                    addLocalApp(mPackageInfo, appInfo);

                } else {
                    //系统应用
                    addSystemApp(mPackageInfo, appInfo);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }




}
