import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import 'package:lib_flutterboost/main.dart';

import '../test_driver/app.dart';

void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  testWidgets("integration test", (WidgetTester tester) async {
    // Build our app and trigger a frame.
    await tester.pumpWidget(TestMaterialApp(
      home: MainPage(title: "test"),
    ));

    final text = find.text('null');
    // Verify
    expect(text, findsOneWidget);

    // Tap
    await tester.tap(text);
    await tester.pumpAndSettle(const Duration(seconds: 1));

    // Verify
    final simpleText = find.text("push from test page");
    expect(simpleText, findsOneWidget);

    // Tap
    await tester.tap(simpleText);
    await tester.pumpAndSettle(const Duration(seconds: 10));
  });
}
