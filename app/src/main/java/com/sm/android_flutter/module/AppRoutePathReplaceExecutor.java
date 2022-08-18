package com.sm.android_flutter.module;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sm.android_flutter.lib.RoutePathReplaceExecutor;

@Route(path = "/app/route/path_replace")
public class AppRoutePathReplaceExecutor implements RoutePathReplaceExecutor {

    @Override
    public String replace(String path) {
        switch (path) {
            case "/your":
                return "/target/your";
            default:
                return path;
        }
    }
}
