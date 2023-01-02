package com.sm.flutter_boost_router;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.plugin.common.MethodChannel;

public final class FlutterMethodChannel {
    private static MethodChannel methodChannel;

    static void setup(MethodChannel methodChannel) {
        FlutterMethodChannel.methodChannel = methodChannel;
    }

    public static void invokeMethod(@NonNull String method) {
        invokeMethod(method, null, null);
    }

    public static void invokeMethod(@NonNull String method, @NonNull Object arguments) {
        invokeMethod(method, arguments, null);
    }

    public static void invokeMethod(@NonNull String method, @NonNull MethodChannel.Result callback) {
        invokeMethod(method, null, callback);
    }

    public static void invokeMethod(@NonNull String method, @Nullable Object arguments, @Nullable MethodChannel.Result callback) {
        methodChannel.invokeMethod(method, arguments, callback);
    }
}
