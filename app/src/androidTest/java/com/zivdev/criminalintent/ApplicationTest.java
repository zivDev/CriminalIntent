package com.zivdev.criminalintent;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.test.ApplicationTestCase;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}




//public class AppInfo {
//    //名称
//    private String name;
//    //图标
////    private Drawable icon;icon
//    //包名
//    private String packagName;
//    //版本号
//    private String versionName;
//    //是否安装到SD卡
//    private boolean isSD;
//    //是否是用户程序
//    private boolean isUser;
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
////    public Drawable getIcon() {
////        return icon;
////    }
////    public void setIcon(Drawable icon) {
////        this.icon = icon;
////    }
//    public String getPackagName() {
//        return packagName;
//    }
//    public void setPackagName(String packagName) {
//        this.packagName = packagName;
//    }
//    public String getVersionName() {
//        return versionName;
//    }
//    public void setVersionName(String versionName) {
//        this.versionName = versionName;
//    }
//    public boolean isSD() {
//        return isSD;
//    }
//    public void setSD(boolean isSD) {
//        this.isSD = isSD;
//    }
//    public boolean isUser() {
//        return isUser;
//    }
//    public void setUser(boolean isUser) {
//        this.isUser = isUser;
//    }
//    public AppInfo() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//    public AppInfo(String name, String packagName,
//                   String versionName, boolean isSD, boolean isUser) {
//        super();
//        this.name = name;
////        this.icon = icon;
//        this.packagName = packagName;
//        this.versionName = versionName;
//        this.isSD = isSD;
//        this.isUser = isUser;
//    }
//    @Override
//    public String toString() {
//        return "AppInfo [name=" + name + ", packagName="
//                + packagName + ", versionName=" + versionName + ", isSD="
//                + isSD + ", isUser=" + isUser + "]";
//    }
//
//}