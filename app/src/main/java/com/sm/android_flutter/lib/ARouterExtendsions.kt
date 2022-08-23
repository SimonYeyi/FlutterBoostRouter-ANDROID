package com.sm.android_flutter.lib

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.idlefish.flutterboost.FlutterBoost

fun Postcard.withRequestCodeNavigation(requestCode: Int, callback: NavigationCallback?) {
    withInt("requestCode", requestCode)
        .navigation(
            FlutterBoost.instance().currentActivity(),
            requestCode,
            callback
        )
}

fun Postcard.withRequestCodeNavigation(requestCode: Int) {
    withRequestCodeNavigation(requestCode, null)
}