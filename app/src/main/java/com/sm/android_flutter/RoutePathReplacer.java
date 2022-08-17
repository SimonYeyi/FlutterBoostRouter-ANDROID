package com.sm.android_flutter;

import java.util.ArrayList;
import java.util.List;

public class RoutePathReplacer {
    private static final List<RoutePathReplaceExecutor> executors = new ArrayList<>();

    public static void addExecutor(RoutePathReplaceExecutor pathExecutor) {
        executors.add(pathExecutor);
    }

    public static String replace(String path) {
        String replacedPath = path;
        for (RoutePathReplaceExecutor executor : executors) {
            replacedPath = executor.replace(path);
            if (!replacedPath.equals(path)) break;
        }
        return replacedPath;
    }
}
