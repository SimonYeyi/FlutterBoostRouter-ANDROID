package com.sm.flutter_boost_router;

import com.idlefish.flutterboost.FlutterBoostPlugin;

import java.lang.reflect.Field;

import io.flutter.embedding.engine.FlutterEngine;

public class FlutterBoostPluginFix {

    public static void resetDefaultRequestCode(FlutterEngine flutterEngine) {
        try {
            FlutterBoostPlugin flutterBoostPlugin = (FlutterBoostPlugin) flutterEngine.getPlugins().get(FlutterBoostPlugin.class);
            Field requestCodeField = flutterBoostPlugin.getClass().getDeclaredField("requestCode");
            requestCodeField.setAccessible(true);
            requestCodeField.set(flutterBoostPlugin, 1_000_000_000);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
