// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility that Flutter provides. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.
import 'package:flutter_test/flutter_test.dart';

import 'package:lib_flutterboost/main.dart';

import '../test_driver/app.dart';

void main() {
  testWidgets('widget test', (WidgetTester tester) async {
    // Build our app and trigger a frame.
    await tester.pumpWidget(TestMaterialApp(
      home: MainPage(title: "test", data: "0"),
    ));

    // Verify
    expect(find.text('0'), findsOneWidget);
    expect(find.text('1'), findsNothing);

    // Tap
    await tester.tap(find.text('0'));
    await tester.pump();

    final simpleText = find.text("push from test page");
    expect(simpleText, findsOneWidget);
  });
}
