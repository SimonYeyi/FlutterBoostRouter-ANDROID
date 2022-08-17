package com.sm.android_flutter;

import android.content.Context;
import android.net.Uri;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

@Route(path = "/global/path_replace")
public class PathReplaceServiceImpl implements PathReplaceService {
    @Override
    public String forString(String path) {
        if (!path.startsWith("/")) path = String.format("/%s", path);
        String replacedPath = RoutePathReplacer.replace(path);
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
