package com.sm.android_flutter.lib;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

public class AppFlutterPlugin implements FlutterPlugin {

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        BinaryMessenger binaryMessenger = binding.getBinaryMessenger();
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "com.premom.lib_flutter/native_flutter");
        methodChannel.setMethodCallHandler(new NativeMethodCallDispatcher());
        FlutterMethodChannel.setup(methodChannel);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
