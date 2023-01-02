package com.sm.android_flutter.module;

import androidx.annotation.NonNull;

import com.google.auto.service.AutoService;
import com.sm.flutter_boost_router.NativeMethodCallHandler;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

@AutoService(NativeMethodCallHandler.class)
public class ModuleMethodHandler implements NativeMethodCallHandler {
    private final ModuleService moduleService = ModuleServiceFactory.get();

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        switch (call.method) {
            case "getInfo":
                String info = moduleService.getInfo();
                result.success(info);
                break;
            default:
                break;
        }
    }
}
