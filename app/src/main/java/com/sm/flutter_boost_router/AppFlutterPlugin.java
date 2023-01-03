package com.sm.flutter_boost_router;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

public class AppFlutterPlugin implements FlutterPlugin {

    private final String appChannelName;

    public AppFlutterPlugin(@NonNull String appChannelName) {
        this.appChannelName = appChannelName;
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        BinaryMessenger binaryMessenger = binding.getBinaryMessenger();
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, appChannelName);
        methodChannel.setMethodCallHandler(new NativeMethodCallDispatcher());
        FlutterMethodChannel.setup(methodChannel);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
