package com.sm.android_flutter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.idlefish.flutterboost.FlutterBoost;
import com.sm.android_flutter.lib.AppFlutterPlugin;
import com.sm.android_flutter.lib.ARouterFlutterBoostDelegate;
import com.sm.android_flutter.lib.FlutterBoostSetupOptionsFactory;
import com.sm.android_flutter.lib.NativeMethodCallDispatcher;
import com.sm.android_flutter.lib.NativeMethodCallHandler;
import com.sm.android_flutter.lib.RoutePathReplaceExecutor;
import com.sm.android_flutter.lib.RoutePathReplacer;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        RoutePathReplacer.addExecutor(ARouter.getInstance().navigation(RoutePathReplaceExecutor.class));
        NativeMethodCallDispatcher.registerHandler(((NativeMethodCallHandler) ARouter.getInstance().build("/module/method_handler").navigation()));
        FlutterBoost.instance().setup(this, new ARouterFlutterBoostDelegate(), engine -> engine.getPlugins().add(new AppFlutterPlugin()), FlutterBoostSetupOptionsFactory.create());
    }
}