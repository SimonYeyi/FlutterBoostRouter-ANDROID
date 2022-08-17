package com.sm.android_flutter;

import java.util.ArrayList;
import java.util.List;

public class RoutePathReplacer {
    private final static RoutePathReplacer INSTANCE = new RoutePathReplacer();
    private final List<Replacer> replacerList = new ArrayList<>();

    private RoutePathReplacer() {
    }

    public static RoutePathReplacer getInstance() {
        return INSTANCE;
    }

    public void add(Replacer pathReplacer) {
        replacerList.add(pathReplacer);
    }

    public String replace(String path) {
        String replacePath = path;
        for (Replacer replacer : replacerList) {
            replacePath = replacer.replace(path);
            if (!replacePath.equals(path)) break;
        }
        return replacePath;
    }

    public interface Replacer {
        String replace(String path);
    }
}
