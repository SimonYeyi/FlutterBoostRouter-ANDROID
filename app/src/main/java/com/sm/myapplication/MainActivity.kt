package com.sm.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs
import com.sm.myapplication.databinding.ActivityFullscreenBinding

@Route(path = "/target/main")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            FlutterBoost.instance()
                .open(
                    FlutterBoostRouteOptions.Builder().pageName("/")
                        .requestCode(REQUEST_CODE_FLUTTER_ROOT)
                        .arguments(mapOf("data" to "From MainActivity")).build()
                )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println(
            "requestCode=$requestCode, result=${
                data?.getSerializableExtra(
                    FlutterActivityLaunchConfigs.ACTIVITY_RESULT_KEY
                )
            }"
        )
    }

    companion object {
        const val REQUEST_CODE_FLUTTER_ROOT = 10
    }
}