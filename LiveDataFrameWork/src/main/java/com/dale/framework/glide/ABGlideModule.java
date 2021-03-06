package com.dale.framework.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.AppGlideModule;
import com.dale.utils.FileUtils;
import com.dale.utils.StringUtils;

import java.io.File;
import java.io.InputStream;

import okhttp3.OkHttpClient;

@GlideModule
public class ABGlideModule extends AppGlideModule {

    public static final int IMAGE_DISK_CACHE_MAX_SIZE = 100 * 1024 * 1024;//图片缓存文件最大值为100Mb

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        /**
         * 配置全局默认占位图等
         */
//        RequestOptions requestOptions = new RequestOptions()
//                .error(R.mipmap.x_state_failed)
//                .placeholder(R.mipmap.x_state_empty);
//        builder.setDefaultRequestOptions(requestOptions);

        builder.setDefaultTransitionOptions(Drawable.class, DrawableTransitionOptions.withCrossFade());
        String path = FileUtils.getDir(FileUtils.PIC);
        if(!StringUtils.isEmpty(path)){
            builder.setDiskCache(() -> DiskLruCacheWrapper.create(new File(path), IMAGE_DISK_CACHE_MAX_SIZE));
        }else {
            super.applyOptions(context,builder);
        }

//        Glide.with(context)
//                .load("")
//                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher))
//                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
//                .into(imageView);

    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ProgressInterceptor())
                .build();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(okHttpClient));
    }
}
