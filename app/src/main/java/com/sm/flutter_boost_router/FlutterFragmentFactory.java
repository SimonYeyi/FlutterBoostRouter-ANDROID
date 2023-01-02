package com.sm.flutter_boost_router;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.idlefish.flutterboost.containers.FlutterBoostFragment;
import com.idlefish.flutterboost.containers.FlutterContainerManager;
import com.idlefish.flutterboost.containers.FlutterViewContainer;

import java.util.HashMap;
import java.util.Map;

public class FlutterFragmentFactory {

    private static Fragment create(String path, boolean lazy, Map<String, Object> arguments) {
        if (lazy) {
            return LazyFlutterFragment.newInstance(path, arguments);
        } else {
            return new FlutterBoostFragment.CachedEngineFragmentBuilder(_FlutterBoostFragment.class)
                    .url(path)
                    .urlParams(arguments == null ? new HashMap<>() : arguments)
                    .build();
        }
    }

    public static Fragment create(String path, Map<String, Object> arguments) {
        return create(path, false, arguments);
    }

    /**
     * Use {@link FlutterFragmentFactory#create(String, Map)} instead.
     */
    @Deprecated
    public static Fragment createLazy(String path, Map<String, Object> arguments) {
        return create(path, true, arguments);
    }

    public static Fragment create(String path) {
        return create(path, null);
    }

    /**
     * Use {@link FlutterFragmentFactory#create(String)} instead.
     */
    @Deprecated
    public static Fragment createLazy(String path) {
        return createLazy(path, null);
    }

    public static class _FlutterBoostFragment extends FlutterBoostFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            FlutterViewContainer top = FlutterContainerManager.instance().getTopContainer();
            if (top != null && top != this) top.detachFromEngineIfNeeded();
        }

        @Override
        protected void onUpdateSystemUiOverlays() {
            //super.onUpdateSystemUiOverlays();
        }
    }
}
