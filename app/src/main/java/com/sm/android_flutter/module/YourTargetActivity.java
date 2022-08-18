package com.sm.android_flutter.module;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.idlefish.flutterboost.FlutterBoost;

@Route(path = "/target/your")
public class YourTargetActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent data = new Intent();
                data.putExtra("result", 100);
                setResult(RESULT_OK, data);
                finish();
            }
        }, 5000);
    }
}
