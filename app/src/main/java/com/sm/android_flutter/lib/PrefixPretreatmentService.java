package com.sm.android_flutter.lib;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PretreatmentService;

@Route(path = "/route/pretreatment")
public class PrefixPretreatmentService implements PretreatmentService {

    @Override
    public void init(Context context) {

    }

    @Override
    public boolean onPretreatment(Context context, Postcard postcard) {
        String path = postcard.getPath();
        String[] pathSplit = path.split("/auto_prefix_");
        if (pathSplit.length > 1) {
            postcard.setPath(pathSplit[0].replaceFirst(pathSplit[1], ""));
        }
        return true;
    }
}
