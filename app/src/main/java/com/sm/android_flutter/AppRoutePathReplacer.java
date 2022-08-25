package com.sm.android_flutter;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.sm.android_flutter.lib.RoutePathReplaceExecutor;
import com.sm.android_flutter.lib.RoutePathReplacer;

@Route(path = "/app/route/path_replace")
public class AppRoutePathReplacer implements RoutePathReplacer, IProvider {

    @Override
    public String replace(String path) {
        switch (path) {
            case "/your":
                return "/module/your";
            default:
                return path;
        }
    }

    @Override
    public void init(Context context) {

    }
}
