package com.sm.android_flutter.lib;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

import io.flutter.plugin.common.MethodChannel;

public interface NativeMethodCallHandler extends MethodChannel.MethodCallHandler, IProvider {
    @Override
    default void init(Context context) {
    }
}
