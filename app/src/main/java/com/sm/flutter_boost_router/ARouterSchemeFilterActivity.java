package com.sm.flutter_boost_router;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

public class ARouterSchemeFilterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri).navigation(this);
        finish();
    }
}
