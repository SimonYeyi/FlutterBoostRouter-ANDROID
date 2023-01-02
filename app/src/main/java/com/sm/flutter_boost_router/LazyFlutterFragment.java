package com.sm.flutter_boost_router;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.idlefish.flutterboost.containers.FlutterBoostFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link FlutterBoostFragment} instead.
 */
@Deprecated
public class LazyFlutterFragment extends Fragment {
    private static final String EXTRAS_PATH = "EXTRAS_PATH";
    private static final String EXTRAS_ARGUMENTS = "EXTRAS_ARGUMENTS";

    public static Fragment newInstance(String path, Map<String, Object> arguments) {
        LazyFlutterFragment instance = new LazyFlutterFragment();
        Bundle args = new Bundle();
        args.putString(EXTRAS_PATH, path);
        args.putSerializable(EXTRAS_ARGUMENTS, ((HashMap<String, Object>) arguments));
        instance.setArguments(args);
        return instance;
    }

    private LazyFlutterFragment() {
    }

    private boolean firstVisible = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout frameLayout = new FrameLayout(inflater.getContext());
        frameLayout.setId(android.R.id.widget_frame);
        return frameLayout;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && firstVisible) {
            firstVisible = false;
            onFirstVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && firstVisible) {
            firstVisible = false;
            onFirstVisible();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden() && firstVisible) {
            firstVisible = false;
            onFirstVisible();
        }
    }

    private void onFirstVisible() {
        String path = getArguments().getString(EXTRAS_PATH);
        Map<String, Object> arguments = (Map<String, Object>) getArguments().getSerializable(EXTRAS_ARGUMENTS);
        Fragment flutterFragment = FlutterFragmentFactory.create(path, arguments);
        getChildFragmentManager()
                .beginTransaction()
                .replace(android.R.id.widget_frame, flutterFragment)
                .commitAllowingStateLoss();
    }
}
