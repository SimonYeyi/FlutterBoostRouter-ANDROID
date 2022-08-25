package com.sm.android_flutter.lib;

import java.util.ArrayList;
import java.util.List;

public enum RoutePathReplaceExecutor implements RoutePathReplacer {
    INSTANCE;

    private final List<RoutePathReplacer> pathReplacerList = new ArrayList<>();

    public void addReplacer(RoutePathReplacer replacer) {
        pathReplacerList.add(replacer);
    }

    @Override
    public String replace(String path) {
        String replacedPath = path;
        for (RoutePathReplacer replacer : pathReplacerList) {
            replacedPath = replacer.replace(path);
            if (!replacedPath.equals(path)) break;
        }
        return replacedPath;
    }
}
