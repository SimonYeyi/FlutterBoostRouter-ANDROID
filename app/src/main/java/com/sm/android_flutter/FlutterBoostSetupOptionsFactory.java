package com.sm.android_flutter;

import com.idlefish.flutterboost.FlutterBoostSetupOptions;

public class FlutterBoostSetupOptionsFactory {

    public static FlutterBoostSetupOptions create() {
        return new FlutterBoostSetupOptions.Builder().dartEntrypoint("main").build();
    }
}
