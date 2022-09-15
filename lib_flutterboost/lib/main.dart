import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:get/get.dart';
import 'package:lib_flutterboost/app_plugin.dart';

@pragma('vm:entry-point')
void mainDev() {
  print('------------------------------mainDev------------------------------');
}

void main() {
  print('------------------------------main------------------------------');

  ///这里的CustomFlutterBinding调用务必不可缺少，用于控制Boost状态的resume和pause
  CustomFlutterBinding();
  runApp(MyApp());

  AppPlugin.get().getInfo().then((value) => print(value));
}

///创建一个自定义的Binding，继承和with的关系如下，里面什么都不用写
class CustomFlutterBinding extends WidgetsFlutterBinding
    with BoostFlutterBinding {}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  /// 由于很多同学说没有跳转动画，这里是因为之前exmaple里面用的是 [PageRouteBuilder]，
  /// 其实这里是可以自定义的，和Boost没太多关系，比如我想用类似iOS平台的动画，
  /// 那么只需要像下面这样写成 [CupertinoPageRoute] 即可
  /// (这里全写成[MaterialPageRoute]也行，这里只不过用[CupertinoPageRoute]举例子)
  ///
  /// 注意，如果需要push的时候，两个页面都需要动的话，
  /// （就是像iOS native那样，在push的时候，前面一个页面也会向左推一段距离）
  /// 那么前后两个页面都必须是遵循CupertinoRouteTransitionMixin的路由
  /// 简单来说，就两个页面都是CupertinoPageRoute就好
  /// 如果用MaterialPageRoute的话同理

  Map<String, FlutterBoostRouteFactory> routerMap = {
    '/': (RouteSettings settings, String? uniqueId) {
      return CupertinoPageRoute(
          settings: settings,
          builder: (_) {
            Map<String, dynamic>? map =
                settings.arguments as Map<String, dynamic>?;
            String? data = map?['data'] as String?;
            return MainPage(
              title: "main",
              data: data,
            );
          });
    },
    'home': (RouteSettings settings, String? uniqueId) {
      return CupertinoPageRoute(
          settings: settings,
          builder: (_) {
            Map<String, dynamic>? map =
                settings.arguments as Map<String, dynamic>?;
            String? data = map?['data'] as String?;
            return MainPage(
              title: "home",
              data: data,
            );
          });
    },
    'simple': (settings, uniqueId) {
      return CupertinoPageRoute(
          settings: settings,
          builder: (_) {
            Map<String, dynamic> map =
                settings.arguments as Map<String, dynamic>;
            String data = map['data'] as String;
            return SimplePage(
              data: data,
            );
          });
    },
  };

  Route<dynamic>? routeFactory(RouteSettings settings, String? uniqueId) {
    FlutterBoostRouteFactory? func = routerMap[settings.name];
    return func?.call(settings, uniqueId);
  }

  Widget appBuilder(Widget home) {
    return AnnotatedRegion<SystemUiOverlayStyle>(
      child: MaterialApp(
        home: home,
        debugShowCheckedModeBanner: true,

        ///必须加上builder参数，否则showDialog等会出问题
        builder: (_, __) {
          return home;
        },
      ),
      value: SystemUiOverlayStyle.light,
    );
  }

  @override
  Widget build(BuildContext context) {
    return FlutterBoostApp(
      routeFactory,
      appBuilder: appBuilder,
    );
  }
}

class MainPage extends StatelessWidget {
  final String title;
  final String? data;

  MainPage({required this.title, this.data});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
        leading: BackButton(
          onPressed: () {
            Navigator.of(context).pop({"result": "pop with back button"});
          },
        ),
      ),
      body: Center(
        child: InkWell(
          onTap: () {
            Navigator.of(context).pushNamed("simple",
                arguments: {"data": "push from $title page"});
          },
          child: Text(data ?? "null"),
        ),
      ),
    );
  }
}

class SimplePage extends StatelessWidget {
  final Rx<Object> data;

  SimplePage({Key? key, required Object data})
      : data = data.obs,
        super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: BackButton(
          onPressed: () {
            while(Navigator.of(context).canPop()){
              Navigator.of(context).pop();
            }
            BoostNavigator.instance.pushReplacement("home",
                arguments: {"data": "push from simple page"});
          },
        ),
        systemOverlayStyle: SystemUiOverlayStyle.light,
        backgroundColor: Colors.purple,
      ),
      body: Center(
        child: GestureDetector(
          onTap: () async {
            final result = await Navigator.of(context).pushNamed("home",
                arguments: {"data": "push from simple page"});

            data.value = result ?? "null";
          },
          child: Obx(() {
            return Text(data.toString());
          }),
        ),
      ),
    );
  }
}
