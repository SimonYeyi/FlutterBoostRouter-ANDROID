package com.sm.android_flutter.module;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sm.android_flutter.lib.FlutterMethods;

@Route(path = "/module/flutter_methods")
public class ModuleFlutterMethods extends FlutterMethods {

    public void flutterMethod() {
        invokeMethod("flutterMethod");
    }
}
