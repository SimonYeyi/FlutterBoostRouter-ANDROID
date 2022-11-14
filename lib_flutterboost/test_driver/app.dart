import 'package:flutter/material.dart';
import 'package:flutter_driver/driver_extension.dart';
import 'package:lib_flutterboost/main.dart';

void main() {
  enableFlutterDriverExtension();
  runApp(TestMaterialApp(
    home: MainPage(title: "test"),
  ));
}

class TestMaterialApp extends StatelessWidget {
  final Widget? home;

  TestMaterialApp({Key? key, required this.home}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: home, onGenerateRoute: (settings) {
      if (settings.name == "home") {
        return MaterialPageRoute(
            builder: (context) =>
                MainPage(
                    title: "home",
                    data: (settings.arguments as Map<String,
                        String>)["data"]!));
      } else if (settings.name == "simple") {
        return MaterialPageRoute(
            builder: (context) =>
                SimplePage(
                    data: (settings.arguments as Map<String,
                        String>)["data"]!));
      }
    },);
  }
}