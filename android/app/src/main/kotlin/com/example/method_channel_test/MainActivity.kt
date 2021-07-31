package com.example.method_channel_test

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import androidx.annotation.NonNull

class MainActivity: FlutterActivity() {
  companion object {
    private val CHANNEL = "com.example.methodchannel/method"
    private const val METHOD1 = "Method1"
    private const val METHOD2 = "Method2"
}

  override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
      super.configureFlutterEngine(flutterEngine)
      MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->

          if (call.method == METHOD1) {
              result.success(method1())
          } else if (call.method == METHOD2) {
              val value1 = call.argument<Int>("value1")
              val value2 = call.argument<Int>("value2")
              result.success(method2(value1!!.toInt(), value2!!.toInt()))
          } else {
              result.notImplemented()
          }
      }
  }

  private fun method1(): String {
      return "Android"
  }

  private fun method2(value1: Int, value2: Int): String {
      return "${value1 * value2}"
  }
}
