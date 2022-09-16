package com.sm.android_flutter.lib

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.idlefish.flutterboost.FlutterBoost

fun Postcard.navigationWithRequestCode(requestCode: Int, callback: NavigationCallback?) {
    withInt("requestCode", requestCode)
        .navigation(
            FlutterBoost.instance().currentActivity(),
            requestCode,
            callback
        )
}

fun Postcard.navigationWithRequestCode(requestCode: Int) {
    navigationWithRequestCode(requestCode, null)
}

fun Postcard.withAll(params: Map<String, Any?>): Postcard {
    return with(ARouterFlutterBoostDelegate.map2bundle(params))
}