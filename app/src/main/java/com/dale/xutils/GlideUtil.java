package com.dale.xutils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dale.framework.util.ABApplication;
import com.dale.utils.SizeUtils;

import java.io.File;


/**
 * des: glide 图片加载库使用
 * 1.所有方法中 Context 传入建议使用 Activity/Fragment 对象 ，图片的加载会和 Activity/Fragment 的生命周期保持一致
 * <p>
 * 2.调用skipMemoryCache()方法并传入true，就表示禁用掉Glide的内存缓存功能。
 * <p>
 * 3.这个diskCacheStrategy()方法基本上就是Glide硬盘缓存功能的一切，它可以接收五种参数：
 * DiskCacheStrategy.NONE： 表示不缓存任何内容。
 * DiskCacheStrategy.DATA： 表示只缓存原始图片。
 * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
 * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
 * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
 */
public class GlideUtil {


    /**
     * @param context
     * @param imageView
     * @param url       图片地址
     */
    public static void loadImage(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param file      图片
     */
    public static void loadLoaclAvatar(Context context, ImageView imageView, File file) {
        Glide.with(context)
                .load(file)
                .dontAnimate()
                .into(imageView);
    }


    /**
     * @param context
     * @param imageView
     * @param url       图片地址
     * @param error     加载失败显示图
     */
    public static void loadImage(Context context, ImageView imageView, String url, int error) {

        RequestOptions options = new RequestOptions()
                .error(error);

        Glide.with(context)
                .load(url)
                .apply(options)
                .dontAnimate()
                .into(imageView);

    }

    /**
     * @param context
     * @param imageView
     * @param url             头像地址
     * @param mRequestOptions 加载失败显示图
     */
    public static void loadAvatarImage(Context context, ImageView imageView, String url, RequestOptions mRequestOptions) {

        Glide.with(context)
                .load(url)
                .apply(mRequestOptions)
                .dontAnimate()
                .skipMemoryCache(false)
                .dontTransform()
                .into(imageView);

    }

    private static final ImagePlaceHolder imagePlaceHolder = new ImagePlaceHolder(ABApplication.getInstance());
    private static final RequestOptions defaultOptions   = new RequestOptions()
            .placeholder(imagePlaceHolder)
            .dontAnimate()
            .error(imagePlaceHolder);

    /**
     * @param context
     * @param imageView
     * @param url       图片地址
     */
    public static void loadImageForDefault(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .apply(defaultOptions)
                .into(imageView);
    }


    private static RequestOptions egameOptions = null;

    public static void loadImageWithOption(Context context, ImageView imageView, String url) {
        if (egameOptions == null) {
            RoundedCornersTransform transform = new RoundedCornersTransform(context, SizeUtils.dp2px(5));
            transform.setNeedCorner(true, true, false, false);
            ImagePlaceHolder disscountImagePlaceHolder = new ImagePlaceHolder(ABApplication.getInstance());
            //disscountImagePlaceHolder.setMarginTop(ResUtils.dp2px(10));
            egameOptions = new RequestOptions()
                    .transform(transform)
                    .placeholder(disscountImagePlaceHolder)
                    .dontAnimate()
                    .error(disscountImagePlaceHolder);

        }
        Glide.with(context)
                .load(url)
                .apply(egameOptions)
                .into(imageView);
    }

    /**
     * @param url    图片地址
     * @param holder 加载中占位图
     * @param error  加载失败显示图
     */
    public static void loadImage(Context context, ImageView imageView, String url, int holder, int error) {
        RequestOptions options = new RequestOptions().placeholder(holder).error(error);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    private static RequestOptions discountOptions = null;

    /**
     * 优惠列表图片加载
     */
    public static void loadImageDiscount(Context context, ImageView imageView, String url) {

        if (discountOptions == null) {
            RoundedCornersTransform transform = new RoundedCornersTransform(context, SizeUtils.dp2px(8));
            ImagePlaceHolder discountImagePlaceHolder = new ImagePlaceHolder(ABApplication.getInstance());
            transform.setNeedCorner(true, true, false, false);
            //imagePlaceHolder.setMarginTop(ResUtils.dp2px(10));
            discountOptions = new RequestOptions()
                    .transform(transform)
                    .placeholder(discountImagePlaceHolder)
                    .dontAnimate()
                    .centerCrop()
                    .error(discountImagePlaceHolder);

        }
        Glide.with(context)
                .load(url)
                .apply(discountOptions)
                .into(imageView);
    }


    private static RequestOptions homeOptions = null;

    public static void loadHomeRound(Context context, ImageView imageView, int resId) {
        if (homeOptions == null) {
            RoundedCornersTransform transform = new RoundedCornersTransform(context, SizeUtils.dp2px(6));
            transform.setNeedCorner(true, true, false, false);
            ImagePlaceHolder homePlaceHolder = new ImagePlaceHolder(ABApplication.getInstance());
            homeOptions = new RequestOptions()
                    .transform(transform)
                    .dontAnimate()
                    .placeholder(homePlaceHolder)
                    .error(homePlaceHolder);

        }
        Glide.with(context)
                .load(resId)
                .apply(homeOptions)
                .into(imageView);
    }

    /**
     * 加载网络图片带有缩略图
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void loadImageThumbnail(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .thumbnail(0.2f)
                .into(imageView);
    }


}
