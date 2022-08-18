package com.sm.android_flutter.lib;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface RoutePathReplaceExecutor extends IProvider {
    String replace(String path);

    @Override
    default void init(Context context) {
    }
}
