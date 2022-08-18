package com.sm.android_flutter.lib;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public final class NativeMethodCallDispatcher implements MethodChannel.MethodCallHandler {
    private final static List<NativeMethodCallHandler> handlers = new ArrayList<>();

    public static void registerHandler(NativeMethodCallHandler handler) {
        handlers.add(handler);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        for (NativeMethodCallHandler handler : handlers) {
            handler.onMethodCall(call, result);
        }
    }
}
