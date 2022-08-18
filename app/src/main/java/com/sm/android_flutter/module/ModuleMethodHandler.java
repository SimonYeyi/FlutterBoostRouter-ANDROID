package com.sm.android_flutter.module;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sm.android_flutter.lib.NativeMethodCallHandler;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

@Route(path = "/module/method_handler")
public class ModuleMethodHandler implements NativeMethodCallHandler {

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        switch (call.method) {
            case "getInfo":
                result.success("info");
                break;
            default:
                result.notImplemented();
                break;
        }
    }
}
