import 'package:flutter/services.dart';

class MethodChannelBridge {
  static const _platform =
      const MethodChannel("com.example.methodchannel/method");

  static Future<String> getPlatformOS() async {
    try {
      final String result = await _platform.invokeMethod('Method1');
      return result;
    } on PlatformException catch (e) {
      return '${e.message}';
    }
  }

  static Future<String> getDataFromPlatform(int value1, int value2) async {
    try {
      final Map args = <String, dynamic>{
        'value1': value1,
        'value2': value2,
      };

      final String result = await _platform.invokeMethod('Method2', args);
      return result;
    } on PlatformException catch (e) {
      return '${e.message}';
    }
  }
}
