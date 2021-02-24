- https://www.iconfont.cn/ 图标库
- https://blog.csdn.net/fafaws3000/article/details/79422833
- https://blog.csdn.net/lxd_android/article/details/79076312
- http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0623/3097.html

- git代码地址：https://github.com/

- keytool在jdk的bin文件里有，所以找到keytool.exe,定位到该目录，然后运行下面命令
keytool -list -v -keystore C:\Users\Dell\Desktop\release.keystore.jks
android签名 ->SHA256就是


- 注册地址https://bintray.com/signup/oss
- 登陆地址https://bintray.com/login?forwardedFrom=%2Fsignup%2Foss  name:daleyangbo  pass:woaiqin2004
- git 代码管理邮箱：441623442@qq.com （密码：441623442@qq.com）
- 邮箱登录：abxingxing@gmail.com（上传代码用这个->谷歌登录）
- key:dd9580acfcc6ff298afedc55df7c06123e3eaadc

- gradlew clean build bintrayUpload -PbintrayUser=daleyangbo  -PbintrayKey=1b5de92c9156d894149a71d7fde0522bad17995c  -PdryRun=false


- 1.根目录添加（查看最新对应版本号：https://bintray.com/novoda/maven/bintray-release）
- classpath 'com.novoda:bintray-release:0.8.0'

        android {
         compileSdkVersion 26
             lintOptions {
                 abortOnError false
                 }
                 ......

          }



        publish {
            userOrg = 'daleyangbo'   //组织名称 organization 建议写用户名（bintray.com用户名）
            groupId = 'com.chad.adapter'  //jcenter上的路径
            artifactId = 'ABBaseAdapter'  //项目名称
            publishVersion = '1.0.0'    //版本号
            desc = '适配器'//d项目描述
            website = 'https://github.com'// github 托管地址
            repoName="maven" //你的仓库名称，没有填写默认仓库是maven//这也是很多人上传仓库不对名问题最多情况，
        }

- artifactId要和之前创建maven里面项目的名字相同(创建的package)，否则上传时会报错误

- 在审核通过前可以通过配置自己私有仓地址使用： maven { url 'https://dl.bintray.com/abxingxing/maven'}

        allprojects {
            repositories {
                google()
                jcenter()
                maven { url 'https://dl.bintray.com/abxingxing/maven'}
            }
        }

- addjcenter的时候，vcs应该改为自己的仓库地址，如：https://dl.bintray.com/abxingxing/maven


-
    //SmartRefreshLayout
      //下面示例中的值等于默认值
            RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
            refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
            refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）

            refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
            refreshLayout.setHeaderHeightPx(100);//同上-像素为单位 （V1.1.0删除）
            refreshLayout.setFooterHeight(100);//Footer标准高度（显示上拉高度>=标准高度 触发加载）
            refreshLayout.setFooterHeightPx(100);//同上-像素为单位 （V1.1.0删除）

            refreshLayout.setFooterHeaderInsetStart(0);//设置 Header 起始位置偏移量 1.0.5
            refreshLayout.setFooterHeaderInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）
            refreshLayout.setFooterFooterInsetStart(0);//设置 Footer 起始位置偏移量 1.0.5
            refreshLayout.setFooterFooterInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）

            refreshLayout.setHeaderMaxDragRate(2);//最大显示下拉高度/Header标准高度
            refreshLayout.setFooterMaxDragRate(2);//最大显示下拉高度/Footer标准高度
            refreshLayout.setHeaderTriggerRate(1);//触发刷新距离 与 HeaderHeight 的比率1.0.4
            refreshLayout.setFooterTriggerRate(1);//触发加载距离 与 FooterHeight 的比率1.0.4

            refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
            refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
            refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
            refreshLayout.setEnablePureScrollMode(false);//是否启用纯滚动模式
            refreshLayout.setEnableNestedScroll(false);//是否启用嵌套滚动
            refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
            refreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
            refreshLayout.setEnableHeaderTranslationContent(true);//是否下拉Header的时候向下平移列表或者内容
            refreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
            refreshLayout.setEnableLoadMoreWhenContentNotFull(true);//是否在列表不满一页时候开启上拉加载功能
            refreshLayout.setEnableFooterFollowWhenLoadFinished(false);//是否在全部加载结束之后Footer跟随内容1.0.4
            refreshLayout.setEnableOverScrollDrag(false);//是否启用越界拖动（仿苹果效果）1.0.4

            refreshLayout.setEnableScrollContentWhenRefreshed(true);//是否在刷新完成时滚动列表显示新的内容 1.0.5
            refreshLayout.srlEnableClipHeaderWhenFixedBehind(true);//是否剪裁Header当时样式为FixedBehind时1.0.5
            refreshLayout.srlEnableClipFooterWhenFixedBehind(true);//是否剪裁Footer当时样式为FixedBehind时1.0.5

            refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
            refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作

            refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener());//设置多功能监听器
            refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDecider());//设置滚动边界判断
            refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDeciderAdapter());//自定义滚动边界

            refreshLayout.setRefreshHeader(new ClassicsHeader(context));//设置Header
            refreshLayout.setRefreshFooter(new ClassicsFooter(context));//设置Footer
            refreshLayout.setRefreshContent(new View(context));//设置刷新Content（用于非xml布局代替addView）1.0.4

            refreshLayout.autoRefresh();//自动刷新
            refreshLayout.autoLoadMore();//自动加载
            refreshLayout.autoRefresh(400);//延迟400毫秒后自动刷新
            refreshLayout.autoLoadMore(400);//延迟400毫秒后自动加载
            refreshLayout.finishRefresh();//结束刷新
            refreshLayout.finishLoadMore();//结束加载
            refreshLayout.finishRefresh(3000);//延迟3000毫秒后结束刷新
            refreshLayout.finishLoadMore(3000);//延迟3000毫秒后结束加载
            refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
            refreshLayout.finishLoadMore(false);//结束加载（加载失败）
            refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
            refreshLayout.closeHeaderOrFooter();//关闭正在打开状态的 Header 或者 Footer（1.1.0）
            refreshLayout.resetNoMoreData();//恢复没有更多数据的原始状态 1.0.4（1.1.0删除）
            refreshLayout.setNoMoreData(false);//恢复没有更多数据的原始状态 1.0.5