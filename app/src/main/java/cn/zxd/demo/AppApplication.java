package cn.zxd.demo;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "1j3OylkPSes1PwYeUoFmk3yu-gzGzoHsz", "7n1od1bAN55d66nDrfeyHjMl");
        AVOSCloud.setDebugLogEnabled(true);
    }
}
