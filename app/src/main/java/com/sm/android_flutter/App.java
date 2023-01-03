package com.sm.android_flutter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.common.collect.Lists;
import com.idlefish.flutterboost.FlutterBoost;
import com.sm.flutter_boost_router.AppFlutterPlugin;
import com.sm.flutter_boost_router.ARouterFlutterBoostDelegate;
import com.sm.flutter_boost_router.FlutterBoostPluginFix;
import com.sm.flutter_boost_router.NativeMethodCallDispatcher;
import com.sm.flutter_boost_router.NativeMethodCallHandler;
import com.sm.flutter_boost_router.RoutePathReplaceExecutor;
import com.sm.flutter_boost_router.RoutePathReplacer;

import java.util.ServiceLoader;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        RoutePathReplaceExecutor.INSTANCE.addReplacer(Lists.newArrayList(ServiceLoader.load(RoutePathReplacer.class).iterator()));
        NativeMethodCallDispatcher.registerHandlers(Lists.newArrayList(ServiceLoader.load(NativeMethodCallHandler.class).iterator()));
        FlutterBoost.instance().setup(this, new ARouterFlutterBoostDelegate(), engine -> engine.getPlugins().add(new AppFlutterPlugin("com.sm.lib_flutter_boost/app_channel")));
        FlutterBoostPluginFix.resetDefaultRequestCode(FlutterBoost.instance().getEngine());
    }
}