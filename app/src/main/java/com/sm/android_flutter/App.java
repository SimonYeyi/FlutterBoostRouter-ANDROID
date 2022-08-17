package com.sm.android_flutter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.idlefish.flutterboost.FlutterBoost;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        RoutePathReplacer.addExecutor(new TargetPathReplaceExecutor());
        FlutterBoost.instance().setup(this, new FlutterBoostDelegateImpl(), engine -> engine.getPlugins().add(new AppFlutterPlugin()), FlutterBoostSetupOptionsFactory.create());
    }
}