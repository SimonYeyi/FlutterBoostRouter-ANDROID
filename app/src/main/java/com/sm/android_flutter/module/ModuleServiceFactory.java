package com.sm.android_flutter.module;

import com.alibaba.android.arouter.launcher.ARouter;

public class ModuleServiceFactory {
    public static ModuleService get() {
        return (ModuleService) ARouter.getInstance().build(ModuleService.PATH).navigation();
    }
}
