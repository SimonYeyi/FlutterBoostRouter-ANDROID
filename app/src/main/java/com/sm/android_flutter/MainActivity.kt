package com.sm.android_flutter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs
import com.sm.android_flutter.databinding.ActivityFullscreenBinding
import com.sm.flutter_boost_router.navigationWithRequestCode
import com.sm.android_flutter.module.ModuleServiceFactory

@Route(path = "/target/main")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            ARouter.getInstance().build("/")
                .withString("data", "from main activity")
                .navigationWithRequestCode(REQUEST_CODE_FLUTTER_ROOT)
/*            FlutterBoost.instance()
                .open(
                    FlutterBoostRouteOptions.Builder().pageName("/")
                        .requestCode(REQUEST_CODE_FLUTTER_ROOT)
                        .arguments(mapOf("data" to "From MainActivity")).build()
                )*/

            ModuleServiceFactory.get().test()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_FLUTTER_ROOT) {
            binding.fullscreenContent.text = data?.getSerializableExtra(
                FlutterActivityLaunchConfigs.ACTIVITY_RESULT_KEY
            ).toString()
        } else {
            binding.fullscreenContent.text = "other request code:$requestCode";
        }
    }

    companion object {
        const val REQUEST_CODE_FLUTTER_ROOT = 10
    }
}