package com.dale.xutils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.dale.libdemo.R;
import com.dale.utils.SizeUtils;

public class ImagePlaceHolder extends Drawable {
    private static final int MIN_WIDHT = SizeUtils.dp2px(20);
    private static final int MIN_HEIGHT = SizeUtils.dp2px(50);
    private Bitmap iconBitmap;
    private int marginTop = SizeUtils.dp2px(26);
    private int iconWidth;
    private int iconHeight;
    private Paint paint;
    private Rect src;
    private Rect imageBounds;
    private int marginLeft;
    private Rect dst;
    private Context context;

    public ImagePlaceHolder(Context context) {
        this.context = context;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setDither(true);
        this.paint.setFilterBitmap(true);
        setIcon(R.mipmap.ic_launcher);
        setBackgroundColor(R.color.color_adbccf);
    }

    /**
     * 设置icon距离顶部的距离
     */
    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
        dst = new Rect(marginLeft, marginTop, marginLeft + iconWidth, marginTop + iconHeight);
    }

    public void setBackgroundColor(@ColorRes int id) {
        int drawColor = ContextCompat.getColor(context, id);
        this.paint.setColor(drawColor);
    }

    public void setIcon(@DrawableRes int id) {
        Drawable icon = ContextCompat.getDrawable(context, id);
        if (icon != null) {
            //icon.setColorFilter(ContextCompat.getColor(context,R.color.generic_color_AEB5C2), PorterDuff.Mode.SRC_ATOP);
            iconBitmap = ((BitmapDrawable) icon).getBitmap();
            iconWidth = iconBitmap.getWidth();
            iconHeight = iconBitmap.getHeight();
            src = new Rect(0, 0, iconWidth, iconHeight);

        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getBounds().width();
        int height = getBounds().height();
        if (width < MIN_WIDHT) {
            width = MIN_WIDHT;
        }
        if (height < MIN_HEIGHT) {
            height = MIN_HEIGHT;
        }

        if (imageBounds == null || imageBounds.width() != width || imageBounds.height() != height) {
            imageBounds = new Rect(0, 0, width, height);
            marginLeft = (width - iconWidth) / 2;

            int  top = (height - iconHeight)/2;

            //注销掉如下代码，改为Rect(0, 0, width, height)，解决BW-1736 banner默认图片不能铺满的问题
            //dst = new Rect(marginLeft, top, marginLeft + iconWidth, top+iconHeight);
            dst = new Rect(0, 0, width, height);
        }
        canvas.drawRect(imageBounds, this.paint);
        canvas.drawBitmap(iconBitmap, src, dst, paint);
        canvas.save();
    }


    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
