package com.sm.android_flutter;

import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostDelegate;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;
import com.idlefish.flutterboost.containers.FlutterBoostActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivityLaunchConfigs;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.init(this);

        FlutterBoost.instance().setup(this, new FlutterBoostDelegate() {
            @Override
            public void pushNativeRoute(FlutterBoostRouteOptions options) {
                Map<String, Object> arguments = options.arguments();
                Postcard postcard = ARouter.getInstance().build(options.pageName());
                if (arguments != null) {
                    for (Map.Entry<String, Object> entry : arguments.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value instanceof Boolean) {
                            postcard.withBoolean(key, (Boolean) value);
                        } else if (value instanceof Integer) {
                            postcard.withInt(key, (Integer) value);
                        } else if (value instanceof Long) {
                            postcard.withLong(key, (Long) value);
                        } else if (value instanceof Double) {
                            postcard.withDouble(key, (Double) value);
                        } else if (value instanceof String) {
                            postcard.withString(key, (String) value);
                        } else if (value instanceof Serializable) {
                            postcard.withSerializable(key, (Serializable) value);
                        } else {
                            throw new IllegalArgumentException("pushNativeRoute arguments type unknown:" + value + ", pageName:" + options.pageName());
                        }
                    }
                }
                postcard.navigation(FlutterBoost.instance().currentActivity(), options.requestCode());
            }

            @Override
            public void pushFlutterRoute(FlutterBoostRouteOptions options) {
                Intent intent = new FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity.class)
                        .backgroundMode(options.opaque() ? FlutterActivityLaunchConfigs.BackgroundMode.opaque : FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                        .destroyEngineWithActivity(false)
                        .uniqueId(options.uniqueId())
                        .url(options.pageName())
                        .urlParams(options.arguments() == null ? new HashMap<>() : options.arguments())
                        .build(FlutterBoost.instance().currentActivity());
                FlutterBoost.instance().currentActivity().startActivityForResult(intent, options.requestCode());
            }
        }, engine -> {
        });
    }
}