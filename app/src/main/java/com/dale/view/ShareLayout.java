package com.dale.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dale.libdemo.R;
import com.dale.utils.ResUtils;
import com.dale.utils.ScreenUtils;
import com.dale.utils.SizeUtils;
import com.zxing.qrcode.view.QRCodeEncoder;

/**
 * @Description:
 */
public class ShareLayout extends HorizontalScrollView {

    ImageView codeImageView;
    ImageView contentView;
    View codeTextView;
    TextView tipTextView;
    RelativeLayout shareView;
    Context mContext;
    int imageGravity = IGravity.TopLeft;//二维码图片默认位置
    int urlGravity = IGravity.TopLeft;//链接默认位置
    int tipsGravity = IGravity.TopLeft;//文案默认位置
    int imageMinSize = SizeUtils.dp2px(60);//二维码图片最小尺寸
    int imageMaxSize = SizeUtils.dp2px(120);//二维码图片最大尺寸
    int imageSize = SizeUtils.dp2px(80);//二维码图片默认尺寸
    int urlMinSize = SizeUtils.dp2px(8);//链接最小字体
    int urlMaxSize = SizeUtils.dp2px(20);//链接最大字体
    int urlSize = SizeUtils.dp2px(12);//链接默认字体
    int tipsMinSize = SizeUtils.dp2px(8);//tips最小字体
    int tipsMaxSize = SizeUtils.dp2px(20);//tips最大字体
    int tipsSize = SizeUtils.dp2px(12);//tips默认字体
    int codeColor = ResUtils.getColor(R.color.black);//二维码默认颜色
    String url;

    public ShareLayout(Context context) {
        this(context, null);
    }

    public ShareLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(mContext).inflate(R.layout.share_layout, this, true);
        shareView = findViewById(R.id.shareView);
        contentView = findViewById(R.id.contentView);
        codeImageView = findViewById(R.id.codeImageView);
        codeTextView = findViewById(R.id.codeTextView);
        tipTextView = findViewById(R.id.tipTextView);
    }


    @Override

    public boolean onInterceptTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        return !(isTouchPointInView(codeImageView, x, y) || isTouchPointInView(codeTextView, x, y) || isTouchPointInView(tipTextView, x, y));
    }

    private boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }

    /**
     * 二维码图片当前进度百分比
     * @return
     */
    private int getImageScale(){
        return (imageSize - imageMinSize) / (imageMaxSize - imageMinSize) * 100;
    }
    /**
     * Url当前进度百分比
     * @return
     */
    private int getUrlScale(){
        return (urlSize - urlMinSize) / (urlMaxSize - urlMinSize) * 100;
    }
    /**
     * tips当前进度百分比
     * @return
     */
    private int getTipsScale(){
        return (tipsSize - tipsMinSize) / (tipsMaxSize - tipsMinSize) * 100;
    }

    public void setUrl(String url) {
        this.url = url;
        Glide.with(getContext()).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                int h = SizeUtils.dp2px(300);
                int w = resource.getWidth() * h / resource.getHeight();

                RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams) contentView.getLayoutParams();
                rl.width = w;
                rl.height = h;
                contentView.setLayoutParams(rl);
                contentView.setImageBitmap(resource);
                if (w < ScreenUtils.getScreenWidth()) {
                    w = ScreenUtils.getScreenWidth();
                }
                LayoutParams layoutParams = new LayoutParams(w, h);
                shareView.setLayoutParams(layoutParams);

            }
        });
        refreshCodeBg();
    }


    private void refreshCodeBg() {
        Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(url, SizeUtils.dp2px(80), codeColor);
        codeImageView.setImageBitmap(bitmap);
    }

    public interface IGravity {
        int TopLeft = 0;
        int TopRight = 1;
        int BottomLeft = 2;
        int BottomRight = 3;
        int Center = 4;

        @IntDef({TopLeft, TopRight, BottomLeft, BottomRight, Center})
        @interface BaseMode {
        }

    }


}
