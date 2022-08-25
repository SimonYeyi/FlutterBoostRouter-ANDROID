package com.sm.android_flutter.lib;

import androidx.fragment.app.Fragment;

import com.idlefish.flutterboost.containers.FlutterBoostFragment;

import java.util.HashMap;
import java.util.Map;

public class FlutterFragmentFactory {

    private static Fragment create(String path, boolean lazy, Map<String, Object> arguments) {
        if (lazy) {
            return LazyFlutterFragment.newInstance(path, arguments);
        } else {
            return new FlutterBoostFragment.CachedEngineFragmentBuilder(FlutterBoostFragment.class)
                    .url(path)
                    .urlParams(arguments == null ? new HashMap<>() : arguments)
                    .build();
        }
    }

    public static Fragment create(String path, Map<String, Object> arguments) {
        return create(path, false, arguments);
    }

    public static Fragment createLazy(String path, Map<String, Object> arguments) {
        return create(path, true, arguments);
    }

    public static Fragment create(String path) {
        return create(path, null);
    }

    public static Fragment createLazy(String path) {
        return createLazy(path, null);
    }
}
