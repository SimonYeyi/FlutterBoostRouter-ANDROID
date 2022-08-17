package com.sm.android_flutter;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

public class AppFlutterPlugin implements FlutterPlugin {

    private MethodChannel methodChannel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        BinaryMessenger binaryMessenger = binding.getBinaryMessenger();
        methodChannel = new MethodChannel(binaryMessenger, "com.premom.lib_flutter/native_flutter");
        methodChannel.setMethodCallHandler(new NativeMethodCallHandler());
        FlutterMethods.getInstance().setup(methodChannel);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        methodChannel.setMethodCallHandler(null);
        methodChannel = null;
    }
}
