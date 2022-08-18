package com.sm.android_flutter.lib;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.template.IProvider;

import io.flutter.plugin.common.MethodChannel;

public class FlutterMethods implements IProvider {
    private static MethodChannel methodChannel;

    static void setup(MethodChannel methodChannel) {
        FlutterMethods.methodChannel = methodChannel;
    }

    @Override
    public void init(Context context) {

    }

    protected void invokeMethod(@NonNull String method) {
        this.invokeMethod(method, null,null);
    }

    protected void invokeMethod(@NonNull String method,  @NonNull Object arguments) {
        this.invokeMethod(method, arguments,null);
    }

    protected void invokeMethod(@NonNull String method, @NonNull MethodChannel.Result callback) {
        this.invokeMethod(method, null,callback);
    }

    protected void invokeMethod(@NonNull String method, @Nullable Object arguments, @Nullable MethodChannel.Result callback) {
        methodChannel.invokeMethod(method, arguments, callback);
    }
}
