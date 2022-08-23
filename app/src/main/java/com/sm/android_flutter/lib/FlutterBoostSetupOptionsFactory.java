package com.sm.android_flutter.lib;

import android.content.Context;

import com.idlefish.flutterboost.FlutterBoostSetupOptions;

public class FlutterBoostSetupOptionsFactory {

    public static FlutterBoostSetupOptions create(Context context) {
        return new FlutterBoostSetupOptions.Builder().dartEntrypoint(getEntrypoint(context.getPackageName())).build();
    }

    private static String getEntrypoint(String packageName) {
        if (packageName.endsWith("dev1") || packageName.endsWith("dev2")) {
            return "mainDev";
        } else if (packageName.endsWith("test") || packageName.endsWith("test2")) {
            return "mainTest";
        } else if (packageName.endsWith("mtest")) {
            return "mtest";
        } else if (packageName.endsWith("hotfix")) {
            return "mainFix";
        } else {
            return "main";
        }
    }
}
