package com.sm.android_flutter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.common.collect.Lists;
import com.idlefish.flutterboost.FlutterBoost;
import com.sm.android_flutter.lib.AppFlutterPlugin;
import com.sm.android_flutter.lib.ARouterFlutterBoostDelegate;
import com.sm.android_flutter.lib.FlutterBoostPluginFix;
import com.sm.android_flutter.lib.FlutterBoostSetupOptionsFactory;
import com.sm.android_flutter.lib.NativeMethodCallDispatcher;
import com.sm.android_flutter.lib.NativeMethodCallHandler;
import com.sm.android_flutter.lib.RoutePathReplaceExecutor;
import com.sm.android_flutter.lib.RoutePathReplacer;

import java.util.ServiceLoader;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        RoutePathReplaceExecutor.INSTANCE.addReplacer(Lists.newArrayList(ServiceLoader.load(RoutePathReplacer.class).iterator()));
        NativeMethodCallDispatcher.registerHandlers(Lists.newArrayList(ServiceLoader.load(NativeMethodCallHandler.class).iterator()));
        FlutterBoost.instance().setup(this, new ARouterFlutterBoostDelegate(), engine -> engine.getPlugins().add(new AppFlutterPlugin()), FlutterBoostSetupOptionsFactory.create(this));
        FlutterBoostPluginFix.resetDefaultRequestCode(FlutterBoost.instance().getEngine());
    }
}