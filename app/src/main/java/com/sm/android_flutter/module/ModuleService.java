package com.sm.android_flutter.module;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.sm.android_flutter.lib.FlutterMethodChannel;

@Route(path = ModuleService.PATH)
public class ModuleService implements IProvider {
    public static final String PATH = "/module/service";

    @Override
    public void init(Context context) {

    }

    public void test() {
        FlutterMethodChannel.invokeMethod("test");
    }

    public String getInfo() {
        return "info";
    }
}
