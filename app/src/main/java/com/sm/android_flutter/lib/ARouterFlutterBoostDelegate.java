package com.sm.android_flutter.lib;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostDelegate;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;
import com.idlefish.flutterboost.containers.FlutterBoostActivity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivityLaunchConfigs;

public class ARouterFlutterBoostDelegate implements FlutterBoostDelegate {
    private final List<String> specialPageNames;
    private final NativeRouteHandler specialNativeRouteHandler;

    public ARouterFlutterBoostDelegate() {
        this(Collections.emptyList(), null);
    }

    public ARouterFlutterBoostDelegate(List<String> specialPageNames, NativeRouteHandler nativeRouteHandler) {
        this.specialPageNames = specialPageNames;
        this.specialNativeRouteHandler = nativeRouteHandler;
    }

    @Override
    public void pushNativeRoute(FlutterBoostRouteOptions options) {
        if (specialPageNames.contains(options.pageName())) {
            specialNativeRouteHandler.pushNativeRoute(options);
            return;
        }
        ARouter.getInstance().build(options.pageName())
                .with(map2bundle(options.arguments()))
                .navigation(FlutterBoost.instance().currentActivity(), options.requestCode());
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

    static Bundle map2bundle(Map<String, Object> arguments) {
        if (arguments == null) return null;
        final Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : arguments.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Boolean) {
                bundle.putBoolean(key, (Boolean) value);
            } else if (value instanceof Integer) {
                bundle.putInt(key, (Integer) value);
            } else if (value instanceof Long) {
                bundle.putLong(key, (Long) value);
            } else if (value instanceof Double) {
                bundle.putDouble(key, (Double) value);
            } else if (value instanceof String) {
                bundle.putString(key, (String) value);
            } else if (value instanceof Serializable) {
                bundle.putSerializable(key, (Serializable) value);
            } else {
                throw new IllegalArgumentException("argument type unknown:" + value);
            }
        }
        return bundle;
    }

    public interface NativeRouteHandler {
        void pushNativeRoute(FlutterBoostRouteOptions options);
    }
}
