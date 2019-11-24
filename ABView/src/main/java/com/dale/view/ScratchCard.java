package com.dale.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.dale.utils.SizeUtils;

/**
 * description: 挂挂卡效果
 */
public class ScratchCard extends View {

    private Paint mOutterPaint;  //定义画笔
    private Path mPath;          //定义路径
    private Canvas mCanvas;      //定义画布
    private Bitmap mBitmap;      //定义图片

    private int mLastX;           // 获取横坐标
    private int mLastY;          // 获取纵坐标

    private Bitmap mOutterBitmap;
    //- - - - - - - - - - - - - -
    // private Bitmap bitmap;

    private String mText;     //定义文本（中奖信息）
    private Paint mBackPaint;  //定义画笔

    private Rect mTextBound;     //记录刮奖信息的宽和高
    private int mTextSize;
    private int mTextColor;

    private volatile boolean mComplete = false;    //判断涂层区域是否消除达到阈值 (volatile保证子线程的更新和主线程的可见性)

    /**
     * 刮完涂层满足阈值回调接口
     */
    public interface OnGuaGuaKaCompleteListener {
        void complete();
    }

    private OnGuaGuaKaCompleteListener mListener;

    public void setOnGuaGuaKaCompleteListener(OnGuaGuaKaCompleteListener mListener) {
        this.mListener = mListener;
    }

    public ScratchCard(Context context) {
        this(context, null);
    }

    public ScratchCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScratchCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        //设置一下自定义属性的值
        TypedArray a = null;
        try {
            a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScratchCard, defStyleAttr, 0);

            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.ScratchCard_CardText) {
                    mText = a.getString(attr);
                } else if (attr == R.styleable.ScratchCard_CardTextColor) {
                    mTextColor = a.getColor(attr, 0x000000);
                } else if (attr == R.styleable.ScratchCard_CardTextSize) {
                    mTextSize = (int) a.getDimension(attr, SizeUtils.dp2px(22));   //把22sp转化为像素
                }
            }

        } finally {
            if (a != null)
                a.recycle();
        }

    }

    /**
     * 回调mText公布中奖信息
     *
     * @param mText
     */
    public void setText(String mText) {
        this.mText = mText;
        //获取当前画笔绘制文本的宽和高
        mBackPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        //  初始化Bitmap , Canvas（画板）
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        setupOutPaint();    //设置绘制Path画笔属性
        setUpBackPaint();
        //mCanvas.drawColor(Color.parseColor("#c0c0c0"));       //设置一个灰色的涂层，来遮盖底部图片
        mCanvas.drawRoundRect(new RectF(0, 0, width, height), 30, 30, mOutterPaint);   //设置涂层遮盖和圆角等
        mCanvas.drawBitmap(mOutterBitmap, null, new Rect(0, 0, width, height), null);       //调用那个图片
    }

    /**
     * 设置我们获奖信息的属性
     */
    private void setUpBackPaint() {
        mBackPaint.setColor(mTextColor);
        mBackPaint.setStyle(Paint.Style.FILL);
        mBackPaint.setTextSize(mTextSize);
        //获取当前画笔绘制文本的宽和高
        mBackPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

    }

    /**
     * 设置绘制Path画笔的属性
     */
    private void setupOutPaint() {
        mOutterPaint.setColor(Color.parseColor("#c0c0c0"));  //设置画笔颜色
        mOutterPaint.setAntiAlias(true);   //设置锯齿类
        mOutterPaint.setDither(true);      //设置抖动
        mOutterPaint.setStrokeJoin(Paint.Join.ROUND);    // 设置圆角
        mOutterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOutterPaint.setStyle(Paint.Style.FILL);
        mOutterPaint.setStrokeWidth(80);          //设置画笔宽度
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;              //记录按下的横纵坐标值
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;

            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                if (dx > 3 || dy > 3) {            //记录划的路径大小，超过三像素才有显示
                    mPath.lineTo(x, y);
                }
                mLastX = x;                      //获取更新横纵坐标值
                mLastY = y;
                break;

            case MotionEvent.ACTION_UP:
                new Thread(mRunable).start();
                break;

        }
        invalidate();
        return true;
    }

    private Runnable mRunable = new Runnable() {                //开启一个线程来计算擦除的面积
        @Override
        public void run() {
            int w = getWidth();   //获取控件宽度
            int h = getHeight();  //获取空间高度

            float wipeArea = 0;    //定义擦除区域大小
            float totalArea = w * h;  //总的控件面积大小

            Bitmap bitmap = mBitmap;
            int[] mPixels = new int[w * h];
            bitmap.getPixels(mPixels, 0, w, 0, 0, w, h);   //获得Bitmap上的所有的像素信息

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int index = i + j * w;
                    if (mPixels[index] == 0) {
                        wipeArea++;
                    }
                }
            }

            if (wipeArea > 0 && totalArea > 0) {
                int percent = (int) (wipeArea * 100 / totalArea);    //刮开所占获取百分比值
                if (percent > 60) {
                    //强制清除掉所有图层区域
                    mComplete = true;
                    postInvalidate();


                }
            }

        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(mText, getWidth() / 2 - mTextBound.width() / 2, getHeight() / 2 + mTextBound.height() / 2, mBackPaint);
        if (mComplete) {
            if (mListener != null) {
                mListener.complete();
            }
        }

        if (!mComplete) {
            drawPath();
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    private void drawPath() {
        mOutterPaint.setStyle(Paint.Style.STROKE);
        mOutterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));           //设置擦除模式
        mCanvas.drawPath(mPath, mOutterPaint);
    }

    /**
     * 进行初始化操作
     */
    private void init() {
        mOutterPaint = new Paint();
        mPath = new Path();

        // bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.p1211);            //设置底部图片
//        mOutterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p1);         //设置遮盖层图片
        mText = "谢谢惠顾";
        mTextBound = new Rect();
        mBackPaint = new Paint();
        mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 22, getResources().getDisplayMetrics());
    }

}

