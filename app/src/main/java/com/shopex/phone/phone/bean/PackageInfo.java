package com.shopex.phone.phone.bean;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by samsung on 2016/2/17.
 */
public class PackageInfo {
    private Drawable icon;
    private String packName;
    private String label;
    private Intent intent;
    public void setIcon(Drawable icon){
        this.icon=icon;
    }
    public Drawable getIcon(){
        return icon;
    }
    public void setPackName(String packName){
        this.packName=packName;
    }
    public String getPackName(){
        return packName;
    }
    public void setLabel(String label){
        this.label=label;
    }
    public String getLabel(){
        return label;
    }
    public void setIntent(Intent intent){
        this.intent=intent;
    }
    public Intent getIntent(){
        return intent;
    }

}
