package com.sm.flutter_boost_router;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public enum RoutePathReplaceExecutor implements RoutePathReplacer {
    INSTANCE;

    private final List<RoutePathReplacer> replacerList = new ArrayList<>();

    public void addReplacer(@NonNull List<RoutePathReplacer> replacerList) {
        this.replacerList.addAll(replacerList);
    }

    @Override
    public String replace(String path) {
        String replacedPath = path;
        for (RoutePathReplacer replacer : replacerList) {
            replacedPath = replacer.replace(path);
            if (!replacedPath.equals(path)) break;
        }
        return replacedPath;
    }
}
