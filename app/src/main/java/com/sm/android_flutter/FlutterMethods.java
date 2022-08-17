package com.sm.android_flutter;

import io.flutter.plugin.common.MethodChannel;

public class FlutterMethods {
    private static final FlutterMethods INSTANCE = new FlutterMethods();
    private MethodChannel methodChannel;

    private FlutterMethods() {
    }

    public static FlutterMethods getInstance() {
        return INSTANCE;
    }

    void setup(MethodChannel methodChannel) {
        this.methodChannel = methodChannel;
    }
}
