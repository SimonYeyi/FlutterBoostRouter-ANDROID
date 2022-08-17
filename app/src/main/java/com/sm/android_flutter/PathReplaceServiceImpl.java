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
        String replacePath = replaceNativePath(path);
        if (replacePath.equals(path)) {
            if (path.lastIndexOf("/") == 0) path = String.format("/flutter%s", path);
            replacePath = replaceFlutterPath(path);
        }
        return replacePath;
    }


    private String replaceNativePath(String path) {
        if (path.equals("/your")) {
            return "/target/your";
        }
        return path;
    }

    private String replaceFlutterPath(String path) {
        return path;
    }

    @Override
    public Uri forUri(Uri uri) {
        return uri;
    }

    @Override
    public void init(Context context) {

    }
}
