2019.7.23第一版依赖方式：

- https://github.com/AndyJennifer/NestedScrollingDemo 一个嵌套滚动的demo

- 引入方式

​  implementation 'com.dale.framework:ABFrameWork:1.0.3'

- 此外必须依赖：

    compileOnly 'androidx.appcompat:appcompat:1.1.0'
    compileOnly 'com.chad.adapter:ABBaseAdapterX:1.0.0'
    compileOnly 'androidx.recyclerview:recyclerview:1.1.0-rc01'
    compileOnly 'com.scwang.smartrefresh:ABRefreshViewX:1.0.0'
    compileOnly 'com.google.android.material:material:1.2.0-alpha01'
    compileOnly 'com.mty.utils:ABUtilsX:1.0.3'
//    compileOnly 'com.dale.dialog:ABDialog:1.0.1'
    compileOnly 'androidx.swiperefreshlayout:swiperefreshlayout:1.0.0'
    compileOnly 'com.dale.view:ABView:1.0.1'
    implementation 'com.dale.webview:ABWebView:1.0.2'
    compileOnly 'com.dale.xpopup:ABXpopup:1.0.0'
    compileOnly 'com.google.code.gson:gson:2.8.6'
    compileOnly 'com.tencent.sonic:sdk:3.1.0'


  - android:usesCleartextTraffic="true" 适配8.0添加

2019.7.27

- 1.0.1版本
  - 更新demo,更新下来刷新基类，添加head，empty，间隔线设置等

2019.7.28
- 1.0.2版本
  - 更新demo,修改基类，状态栏颜色更改   https://www.jianshu.com/p/73a117136105

2019.8.19
- 1.0.9版本
  - 更新demo,添加下拉刷新状态栏透明帮助类

2019.8.22
- 1.1.1版本
  - 更新基类Fragment中跳转Activity方法,修改headview 显示bug

2019.8.25
- 1.1.2版本
  - 更新基类WebViewActivity进度菊花修改

2019.11.18
 - 1.1.3版本
   - 更新基类WebView框架优化，对话框依赖替换

2019.11.19
    - 1.1.5版本
      - 更新基类，添加多Presenter复用，添加Refreshd代理实现

2019.11.20
    - 1.1.6版本
      - 更新基类，修Presenter生命周期Lifecycle方式监听，已经demo实现LivaData方式回调