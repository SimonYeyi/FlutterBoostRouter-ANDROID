package com.sm.android_flutter.lib;

import java.util.ArrayList;
import java.util.List;

public class RoutePathReplacer {
    private static final List<RoutePathReplaceExecutor> pathReplaceExecutors = new ArrayList<>();

    public static void addExecutor(RoutePathReplaceExecutor pathReplaceExecutor) {
        pathReplaceExecutors.add(pathReplaceExecutor);
    }

    public static String replace(String path) {
        String replacedPath = path;
        for (RoutePathReplaceExecutor executor : pathReplaceExecutors) {
            replacedPath = executor.replace(path);
            if (!replacedPath.equals(path)) break;
        }
        return replacedPath;
    }
}
