import 'package:flutter_driver/flutter_driver.dart';
import 'package:test/test.dart';

void main() {
  group('driver test', () {
    // 通过key属性定位元素
    final text = find.text('null');

    FlutterDriver? driver;

    // 测试开始前链接FlutterDriver
    setUpAll(() async {
      driver = await FlutterDriver.connect();
    });

    // 测试结束后关闭FlutterDriver
    tearDownAll(() async {
      driver?.close();
    });

    // TestCase
    test('main page', () async {
      expect(await driver?.getText(text), 'null');
      //点击文本，页面跳转
      await driver?.tap(text);

      //新页面校验文本
      final simpleText = find.text("push from test page");
      expect(await driver?.getText(simpleText), 'push from test page');

      //新页面继续点击文本
      await driver?.tap(find.byType("Obx"));

      expect(await driver?.getText(simpleText), 'push from test page');
    });
  });
}
