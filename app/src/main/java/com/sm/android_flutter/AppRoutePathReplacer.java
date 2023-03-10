package com.sm.android_flutter;

import com.google.auto.service.AutoService;
import com.sm.flutter_boost_router.RoutePathReplacer;

@AutoService(RoutePathReplacer.class)
public class AppRoutePathReplacer implements RoutePathReplacer {

    @Override
    public String replace(String path) {
        switch (path) {
            case "/your":
                return "/module/your";
            default:
                return path;
        }
    }
}
