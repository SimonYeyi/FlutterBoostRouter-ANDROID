package com.sm.myapplication;

import android.content.Context;
import android.net.Uri;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

@Route(path = "/global/path_replace")
public class PathReplaceServiceImpl implements PathReplaceService {
    @Override
    public String forString(String path) {
        if ("your".equals(path) || "your".equals(path.replaceFirst("/", ""))) {
            return "/target/your";
        }
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
