package com.sm.android_flutter.lib;

import android.content.Context;
import android.net.Uri;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

@Route(path = "/route/path_replace")
public class RoutePathReplaceService implements PathReplaceService {
    @Override
    public String forString(String path) {
        if (!path.startsWith("/")) path = String.format("/%s", path);
        String replacedPath = RoutePathReplacer.INSTANCE.replace(path);
        if (replacedPath.lastIndexOf("/") == 0) replacedPath = String.format("/flutter%s", path);
        return replacedPath;
    }

    @Override
    public Uri forUri(Uri uri) {
        return uri;
    }

    @Override
    public void init(Context context) {

    }
}
