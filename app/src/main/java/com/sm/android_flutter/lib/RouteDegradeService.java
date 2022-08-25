package com.sm.android_flutter.lib;

import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Route(path = "/route/degrade")
public class RouteDegradeService implements DegradeService {
    @Override
    public void onLost(Context context, Postcard postcard) {
        FlutterBoost.instance().open(createFlutterBoostRouteOptions(postcard));
    }

    @Override
    public void init(Context context) {

    }

    private FlutterBoostRouteOptions createFlutterBoostRouteOptions(Postcard postcard) {
        FlutterBoostRouteOptions.Builder optionsBuilder = new FlutterBoostRouteOptions.Builder();
        optionsBuilder.pageName(postcard.getPath());
        Bundle bundle = postcard.getExtras();
        if (bundle == null) return optionsBuilder.build();
        Set<String> keySet = bundle.keySet();
        if (keySet == null) return optionsBuilder.build();
        Map<String, Object> arguments = new HashMap<>(keySet.size());
        optionsBuilder.arguments(arguments);
        for (String key : keySet) {
            if (key.equals("requestCode")) {
                optionsBuilder.requestCode(bundle.getInt(key));
            } else {
                arguments.put(key, bundle.get(key));
            }
        }
        return optionsBuilder.build();
    }
}
