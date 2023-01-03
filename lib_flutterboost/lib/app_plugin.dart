import 'package:flutter/services.dart';

class AppPlugin {
  static final AppPlugin _instance = AppPlugin();

  final MethodChannel _methodChannel =
      const MethodChannel("com.sm.lib_flutter_boost/app_channel");

  AppPlugin._();

  static AppPlugin get() {
    return _instance;
  }

  AppPlugin() {
    _methodChannel.setMethodCallHandler((call) async {
      switch (call.method) {
        case "test":
          print("invoke test method");
          break;
        default:
          break;
      }
    });
  }

  Future<String?> getInfo() async {
    return _methodChannel.invokeMethod<String?>("getInfo");
  }
}
