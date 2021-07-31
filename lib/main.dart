import 'package:flutter/material.dart';

import 'src/method_channel_bridge.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Method Channel'),
        ),
        body: Container(
          width: double.infinity,
          height: double.infinity,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              TextButton(
                onPressed: () {
                  _getMethod1Value();
                },
                child: Text('Get OS'),
              )
            ],
          ),
        ),
      ),
    );
  }

  void _getMethod1Value() {
    MethodChannelBridge.getPlatformOS().then((value) {
      print(value);
    });
  }
}
