2019.11.22第一版依赖方式：

- 引入方式

​  implementation 'com.dale.push:ABPush:xxxxx'

- 此外必须依赖：

  - implementation 'androidx.appcompat:appcompat:xxx'

- 在您项目主module的build.gradle中添加如下配置：
  -         manifestPlaceholders = [
                    //以下三个极光推送
                    JPUSH_PKGNAME : "您项目包名",
                    JPUSH_APPKEY : "8340435031b17ab302c541c3", //JPush 上注册的包名对应的 Appkey.
                    JPUSH_CHANNEL : "developer-default", //暂时填写默认值即可.
            ]

- manifest中如下配置
                <provider
                    android:name="cn.jpush.android.service.DataProvider"
                    android:authorities="${applicationId}.DataProvider"
                    android:exported="false"
                    android:process=":pushcore" />

                <provider
                    android:name="cn.jpush.android.service.DownloadProvider"
                    android:authorities="${applicationId}.DownloadProvider"
                    android:exported="true" />

                <receiver
                    android:name="com.dale.push_demo.MyPushReceiver"
                    android:enabled="true"
                    android:exported="false">
                    <intent-filter>
                        <action android:name="cn.jpush.android.intent.RECEIVE_MESSAGE" />
                        <category android:name="${applicationId}" />
                    </intent-filter>
                </receiver>

- Application 初始化
        PushSdk.ins().initSDK(this);

- 接收推送
        public class MyPushReceiver extends JPushMessageReceiver {

        }

