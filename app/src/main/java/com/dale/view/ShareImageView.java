package com.dale.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import androidx.appcompat.widget.AppCompatImageView;

import com.dale.libdemo.R;
import com.dale.utils.ResUtils;
import com.dale.utils.SizeUtils;
import com.zxing.qrcode.view.QRCodeEncoder;

public class ShareImageView extends AppCompatImageView {

    int urlColor;//链接颜色
    float urlSize;//链接字体
    int textColor;//文本颜色
    float textSize;//文本字体
    int codeColor;//二维码颜色
    float codeMinSize;//二维码最小尺寸
    float codeMaxSize;//二维码最大尺寸
    float sharePadding;//内容四周边距
    TextPaint mTextPaint;//文本和url画笔
    Paint mCodePaint;//二维码画笔
    int textGravity;//文本上下左右居中
    int codeGravity;//二维码上下左右居中
    String url;
    String text;
    Bitmap bitmap;
    int codeSize;
    int currCodeSize;

    private int mScrollX;
    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;
    private int mDw;
    private float mX;



    public ShareImageView(Context context) {
        this(context, null);
    }

    public ShareImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShareImageView);
            urlColor = typedArray.getColor(R.styleable.ShareImageView_urlColor, ResUtils.getColor(R.color.white));
            urlSize = typedArray.getDimension(R.styleable.ShareImageView_urlSize, SizeUtils.dp2px(14));
            textColor = typedArray.getColor(R.styleable.ShareImageView_textColor, ResUtils.getColor(R.color.white));
            textSize = typedArray.getDimension(R.styleable.ShareImageView_textSize, SizeUtils.dp2px(14));
            codeColor = typedArray.getColor(R.styleable.ShareImageView_codeColor, ResUtils.getColor(R.color.heise));
            codeMinSize = typedArray.getDimension(R.styleable.ShareImageView_codeMinSize, SizeUtils.dp2px(60));
            codeMaxSize = typedArray.getDimension(R.styleable.ShareImageView_codeMaxSize, SizeUtils.dp2px(120));
            sharePadding = typedArray.getDimension(R.styleable.ShareImageView_sharePadding, SizeUtils.dp2px(20));
            textGravity = typedArray.getInt(R.styleable.ShareImageView_textGravity, 0);
            codeGravity = typedArray.getInt(R.styleable.ShareImageView_codeGravity, 2);
            typedArray.recycle();
            mTextPaint = new TextPaint();
            mTextPaint.setAntiAlias(true);
            mCodePaint = new Paint();
            mCodePaint.setAntiAlias(true);
            mCodePaint.setColor(codeColor);
        }
        currCodeSize = codeSize = SizeUtils.dp2px(80);

    }

    public void setUrl(String url){
        this.url = url;
        bitmap = QRCodeEncoder.syncEncodeQRCode(url, codeSize, codeColor);
        invalidate();
    }

    public void setText(String text){
        this.text = text;
//        invalidate();
    }

    public void setScaled(float scaled){
        currCodeSize = (int) (codeSize + (((codeMaxSize - codeMinSize) / 100) * scaled));
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCodeBitmap(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas){
        float x = 0,y = 0;
        Rect rect = new Rect();
        mTextPaint.setTextSize(urlSize);
        mTextPaint.getTextBounds(url, 0, url.length(), rect);
        int w = rect.width();
        int h = rect.height();
        if(!TextUtils.isEmpty(url)){
            switch (textGravity) {
                case 0:
                    x = sharePadding;
                    y = sharePadding + SizeUtils.dp2px(20);
                    break;
                case 1:
                    x = getWidth() - sharePadding - w;
                    y = sharePadding + SizeUtils.dp2px(20);
                    break;
                case 2:
                    x = sharePadding;
                    y = getHeight() - sharePadding + h / 2;
                    break;
                case 3:
                    x = getWidth() - sharePadding - w;
                    y = getHeight() - sharePadding + h / 2;
                    break;
                case 4:
                    x = getWidth() / 2 - w / 2;
                    y = getHeight() / 2 + h / 2;
                    break;
            }

            if(!TextUtils.isEmpty(text)){
                mTextPaint.setTextSize(urlSize);
                mTextPaint.setColor(urlColor);
//                int subIndex = mTextPaint.breakText(str, 0, str.length(), true, lineWidth, null);
                StaticLayout myStaticLayout = new StaticLayout(url, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                canvas.save();
                canvas.translate(x, y - SizeUtils.dp2px(20));
                myStaticLayout.draw(canvas);
                canvas.restore();

                mTextPaint.setTextSize(textSize);
                mTextPaint.setColor(textColor);
                canvas.drawText(text, x, y, mTextPaint);

            }else {
                mTextPaint.setTextSize(urlSize);
                mTextPaint.setColor(urlColor);
                StaticLayout myStaticLayout = new StaticLayout(url, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                canvas.save();
                canvas.translate(x,y);
                myStaticLayout.draw(canvas);
                canvas.restore();
            }

        }
    }

    private void drawCodeBitmap(Canvas canvas){
        if(bitmap == null){
            return;
        }
        float left = 0f,top = 0f;
        bitmap = Bitmap.createScaledBitmap(bitmap, currCodeSize, currCodeSize, true);
        switch (codeGravity) {
            case 0:
                left = sharePadding;
                top = sharePadding;
                break;
            case 1:
                left = getWidth() - sharePadding - bitmap.getWidth();
                top = sharePadding;
                break;
            case 2:
                left = sharePadding;
                top = getHeight() - sharePadding - bitmap.getHeight();
                break;
            case 3:
                left = getWidth() - sharePadding - bitmap.getWidth();
                top = getHeight() - sharePadding - bitmap.getHeight();
                break;
            case 4:
                left = getWidth()/2 - bitmap.getWidth()/2;
                top = getHeight()/2 - bitmap.getHeight()/2;
                break;
        }
        canvas.drawBitmap(bitmap, left, top, null);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int w = 2000;
        setMeasuredDimension(w, h);
    }


    int mLastX;
    int mLastY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                ViewGroup view = (ViewGroup) getParent();
                view.requestDisallowInterceptTouchEvent(false);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    ViewGroup view = (ViewGroup) getParent();
                    view.requestDisallowInterceptTouchEvent(true);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //手指位置地点
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View parent = (View) getParent();
                mDw = getWidth() - parent.getWidth();
                if (mDw > 0) {
                    mScroller.forceFinished(true);
                    if (mVelocityTracker == null) {
                        mVelocityTracker = VelocityTracker.obtain();
                        mVelocityTracker.addMovement(event);
                    }
                    mScrollX = getScrollX();
                    mX = event.getX();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mDw > 0) {
                    mVelocityTracker.addMovement(event);

                    float x = event.getX();
                    int dx = (int) (mX - x + mScrollX);
                    if (dx < 0) {
                        dx = 0;
                    }
                    if (dx > mDw) {
                        dx = mDw;
                    }
                    scrollTo(dx, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mDw > 0) {
                    mVelocityTracker.addMovement(event);
                    mVelocityTracker.computeCurrentVelocity(1000);
                    int velocityX = (int) mVelocityTracker.getXVelocity();
                    mScroller.fling(getScrollX(), 0, -velocityX, 0, 0, mDw, 0, 0);
                    postInvalidate();
                    if (mVelocityTracker != null) {
                        mVelocityTracker.recycle();
                        mVelocityTracker = null;
                    }
                }
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        // 如果返回true，表示动画还没有结束
        // 因为前面startScroll，所以只有在startScroll完成时 才会为false
        if (mScroller.computeScrollOffset()) {
            // 产生了动画效果 每次滚动一点
            scrollTo(mScroller.getCurrX(), 0);
            //刷新View 否则效果可能有误差
            postInvalidate();
        }
    }


}
