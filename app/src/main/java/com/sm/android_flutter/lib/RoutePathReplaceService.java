package com.sm.android_flutter.lib;

import android.content.Context;
import android.net.Uri;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

@Route(path = "/route/path_replace")
public class RoutePathReplaceService implements PathReplaceService {

    @Override
    public String forString(String path) {
        final String replacedPath = RoutePathReplaceExecutor.INSTANCE.replace(path);
        final String prefix = "/";
        String prefixPath = replacedPath;
        if (!replacedPath.startsWith(prefix)) {
            prefixPath = prefix + replacedPath;
        }
        if (prefixPath.lastIndexOf("/") == 0) {
            prefixPath = prefix + "flutter" + prefixPath;
        }
        if (!replacedPath.equals(prefixPath)) {
            String auto_prefix = replaceLast(prefixPath, replacedPath, "");
            prefixPath += "/auto_prefix_" + auto_prefix;
        }
        return prefixPath;
    }

    private static String replaceLast(String string, String regex, String replacement) {
        return string.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    @Override
    public Uri forUri(Uri uri) {
        return uri;
    }

    @Override
    public void init(Context context) {

    }
}
