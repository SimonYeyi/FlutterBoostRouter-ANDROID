package com.sm.android_flutter.lib;

import java.util.ArrayList;
import java.util.List;

public enum RoutePathReplacer implements RoutePathReplaceExecutor {
    INSTANCE;

    private final List<RoutePathReplaceExecutor> pathReplaceExecutors = new ArrayList<>();

    public void addExecutor(RoutePathReplaceExecutor pathReplaceExecutor) {
        pathReplaceExecutors.add(pathReplaceExecutor);
    }

    @Override
    public String replace(String path) {
        String replacedPath = path;
        for (RoutePathReplaceExecutor executor : pathReplaceExecutors) {
            replacedPath = executor.replace(path);
            if (!replacedPath.equals(path)) break;
        }
        return replacedPath;
    }
}
