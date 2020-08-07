2019.7.23第一版依赖方式：

- https://github.com/AndyJennifer/NestedScrollingDemo 一个嵌套滚动的demo

- 引入方式

​  implementation 'com.dale.x:AB_FrameWork:1.0.0-beta01'

- 此外必须依赖：

    compileOnly 'com.google.code.gson:gson:2.8.6'
    compileOnly 'com.tencent.sonic:sdk:3.1.0'


  - android:usesCleartextTraffic="true" 适配8.0添加


2019.11.20
    - 1.1.6版本
      - 更新基类，修Presenter生命周期Lifecycle方式监听，已经demo实现LivaData方式回调
      
2019.11.29 单activity https://github.com/YoKeyword/Fragmentation
    - 新建单activity xframework lib  库 
    
- 一个动画库地址:  https://lottiefiles.com/  


https://juejin.im/post/5e78473df265da57201811e4(面试)
https://github.com/Blankj/Android_QA(面试)
https://developer.android.google.cn/training/constraint-layout/motion-layout-examples 动画布局

typora 看文档的编辑器







- 图片下载进度监听

        ProgressInterceptor.addListener(data.getImageUrl(), progress -> mProgressBar.setProgress(progress));



        Glide.with(mContext).load(data.getImageUrl()).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                mProgressBar.setVisibility(View.GONE);
                ProgressInterceptor.removeListener(data.getImageUrl());
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) wechat_receiver_imgage.getLayoutParams();
                int width;
                int height;
                if(resource.getIntrinsicWidth() * 4 <  resource.getIntrinsicHeight() * 3){
                    width = SizeUtils.dp2px(120);
                    height = width * resource.getIntrinsicHeight() / resource.getIntrinsicWidth();
                }else {
                    height =  SizeUtils.dp2px(160);
                    width = height * resource.getIntrinsicWidth() / resource.getIntrinsicHeight();
                }

                lp.height = height;

                if(width > ScreenUtils.getScreenWidth() * 0.65){
                    width = (int) (ScreenUtils.getScreenWidth() * 0.65);
                    lp.width = width;
                    wechat_receiver_imgage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }else {
                    wechat_receiver_imgage.setScaleType(ImageView.ScaleType.CENTER);
                    lp.width = width;
                }

                wechat_receiver_imgage.setLayoutParams(lp);
                wechat_receiver_imgage.setImageDrawable(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }

            @Override
            public void onDestroy() {
                super.onDestroy();
                ProgressInterceptor.removeListener(data.getImageUrl());
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                ProgressInterceptor.removeListener(data.getImageUrl());
            }

        });
        
        
1、北方华创：世界级半导体设备后备军
2、中微公司：世界级半导体设备后备军
3、大族激光：世界激光设备全场景龙头
5、兆易创新：世界存储芯片平台后备军
7、长电科技：世界前三的先进芯片封装
9、中环股份：世界级大硅片潜力后备军
10、紫光国微：世界级卡芯片平台龙头