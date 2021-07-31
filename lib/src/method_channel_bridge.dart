import 'package:flutter/services.dart';

class MethodChannelBridge {
  static const _platform =
      const MethodChannel("com.example.methodchannel/method");

  static Future<String> getMessageFromKotlin() async {
    try {
      final String result = await _platform.invokeMethod('Method1');
      return result;
    } on PlatformException catch (e) {
      return '${e.message}';
    }
  }

  static Future<void> callNotificationFromKotlin(String flutterTitle) async {
    try {
      final Map args = <String, dynamic>{
        'flutterTitle': flutterTitle,
      };

      await _platform.invokeMethod('Method2', args).toString();
    } on PlatformException catch (e) {
      print('${e.message}');
    }
  }
}
