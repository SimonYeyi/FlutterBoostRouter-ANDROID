package com.sm.android_flutter;

public class TargetPathReplacer implements RoutePathReplacer.Replacer {

    @Override
    public String replace(String path) {
        if (path.equals("/your")) {
            return "/target/your";
        }
        return path;
    }
}
