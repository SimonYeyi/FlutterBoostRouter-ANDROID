import 'package:flutter/services.dart';

class AppPlugin {
  static final AppPlugin _instance = AppPlugin();

  final MethodChannel _methodChannel =
      const MethodChannel("com.premom.lib_flutter/native_flutter");

  AppPlugin._();

  static AppPlugin get() {
    return _instance;
  }

  AppPlugin() {
    _methodChannel.setMethodCallHandler((call) async {
      switch (call.method) {
        case "flutterMethod":
          print("invoke flutterMethod");
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
