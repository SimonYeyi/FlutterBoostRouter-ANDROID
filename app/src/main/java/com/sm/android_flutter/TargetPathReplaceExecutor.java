package com.sm.android_flutter;

public class TargetPathReplaceExecutor implements RoutePathReplaceExecutor {

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
