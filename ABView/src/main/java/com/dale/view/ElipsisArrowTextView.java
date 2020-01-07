package com.dale.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 多行文字，显示不全，在最后一行显示更多箭头
 */
public class ElipsisArrowTextView extends AppCompatTextView {


    private static final String ELLIPSIS_HINT = "...";
    private static final String GAP_TO_EXPAND_HINT = " ";
    private static final int MAX_LINES_ON_SHRINK = 3;

    private String mEllipsisHint;
    private String mGapToExpandHint = GAP_TO_EXPAND_HINT;
    private int mMaxLinesOnShrink = MAX_LINES_ON_SHRINK;

    private TextView.BufferType mBufferType = TextView.BufferType.NORMAL;
    private TextPaint mTextPaint;
    private Layout mLayout;
    private int mTextLineCount = -1;
    private int mLayoutWidth = 0;
    private int mFutureTextViewWidth = 0;

    private CharSequence mOrigText;

    private Bitmap bitmap1;
    private CenteredImageSpan imgSpan1;

    public ElipsisArrowTextView(Context context) {
        super(context);
        init();
    }

    public ElipsisArrowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    public ElipsisArrowTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ElipsisArrowTextView);
        if (a == null) {
            return;
        }
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.ElipsisArrowTextView_arr_MaxLinesOnShrink) {
                mMaxLinesOnShrink = a.getInteger(attr, MAX_LINES_ON_SHRINK);
            } else if (attr == R.styleable.ElipsisArrowTextView_arr_EllipsisHint) {
                mEllipsisHint = a.getString(attr);
            } else if (attr == R.styleable.ElipsisArrowTextView_arr_GapToExpandHint) {
                mGapToExpandHint = a.getString(attr);
            }
        }
        a.recycle();
    }

    private void init() {
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.x_arrow_more);
        imgSpan1 = new CenteredImageSpan(getContext(), R.mipmap.x_arrow_more);

        if (TextUtils.isEmpty(mEllipsisHint)) {
            mEllipsisHint = ELLIPSIS_HINT;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
                setTextInternal(getNewTextByConfig(), mBufferType);
            }
        });
    }


    private CharSequence getNewTextByConfig() {
        if (TextUtils.isEmpty(mOrigText)) {
            return mOrigText;
        }

        mLayout = getLayout();
        if (mLayout != null) {
            mLayoutWidth = mLayout.getWidth();
        }

        if (mLayoutWidth <= 0) {
            if (getWidth() == 0) {
                if (mFutureTextViewWidth == 0) {
                    return mOrigText;
                } else {
                    mLayoutWidth = mFutureTextViewWidth - getPaddingLeft() - getPaddingRight();
                }
            } else {
                mLayoutWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            }
        }

        mTextPaint = getPaint();

        mTextLineCount = -1;
        mLayout = null;
        mLayout = new DynamicLayout(mOrigText, mTextPaint, mLayoutWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        mTextLineCount = mLayout.getLineCount();

        if (mTextLineCount <= mMaxLinesOnShrink) {
            return mOrigText;
        }

        int indexEnd = getValidLayout().getLineEnd(mMaxLinesOnShrink - 1);
        int indexStart = getValidLayout().getLineStart(mMaxLinesOnShrink - 1);
        int indexEndTrimmed = indexEnd
                - getLengthOfString(mEllipsisHint)
                - getLengthOfString(mGapToExpandHint);

        if (indexEndTrimmed <= indexStart) {
            indexEndTrimmed = indexEnd;
        }

        int remainWidth = getValidLayout().getWidth() -
                (int) (mTextPaint.measureText(mOrigText.subSequence(indexStart, indexEndTrimmed).toString()) + 0.5) - bitmap1.getWidth();
        float widthTailReplaced = mTextPaint.measureText(getContentOfString(mEllipsisHint)
                + getContentOfString(mGapToExpandHint));

        int indexEndTrimmedRevised = indexEndTrimmed;
        if (remainWidth > widthTailReplaced) {
            int extraOffset = 0;
            int extraWidth = 0;
            while (remainWidth > widthTailReplaced + extraWidth) {
                extraOffset++;
                if (indexEndTrimmed + extraOffset <= mOrigText.length()) {
                    extraWidth = (int) (mTextPaint.measureText(
                            mOrigText.subSequence(indexEndTrimmed, indexEndTrimmed + extraOffset).toString()) + 0.5);
                } else {
                    break;
                }
            }
            indexEndTrimmedRevised += extraOffset - 1;
        } else {
            int extraOffset = 0;
            int extraWidth = 0;
            while (remainWidth + extraWidth < widthTailReplaced) {
                extraOffset--;
                if (indexEndTrimmed + extraOffset > indexStart) {
                    extraWidth = (int) (mTextPaint.measureText(mOrigText.subSequence(indexEndTrimmed + extraOffset, indexEndTrimmed).toString()) + 0.5);
                } else {
                    break;
                }
            }
            indexEndTrimmedRevised += extraOffset;
        }

        String fixText = removeEndLineBreak(mOrigText.subSequence(0, indexEndTrimmedRevised));
        SpannableStringBuilder ssbShrink = new SpannableStringBuilder(fixText);

        ssbShrink.append(mEllipsisHint);

        if (issetSpecialColor) {
            int lenth = ssbShrink.length();
            if (specialColorLenth <= lenth) {
                lenth = specialColorLenth;
            }
            ssbShrink.setSpan(colorSpan, specialColorStart, lenth, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        ssbShrink.append(getContentOfString(mGapToExpandHint));

        ssbShrink.append("+");
        ssbShrink.setSpan(imgSpan1, ssbShrink.length() - 1, ssbShrink.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ssbShrink;
    }

    private String removeEndLineBreak(CharSequence text) {
        String str = text.toString();
        while (str.endsWith("\n")) {
            str = str.substring(0, str.length() - 1);
        }


        Layout mLayout = new DynamicLayout(str, mTextPaint, mLayoutWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        if (mLayout.getLineCount() > mMaxLinesOnShrink) {
            if (str.contains("\n")) {
                str = str.substring(0, str.lastIndexOf("\n"));
            }
        }

        return str;
    }

    private Layout getValidLayout() {
        return mLayout != null ? mLayout : getLayout();
    }

    @Override
    public void setText(CharSequence text, TextView.BufferType type) {
        mOrigText = text;
        mBufferType = type;
        setTextInternal(getNewTextByConfig(), type);
    }

    private void setTextInternal(CharSequence text, TextView.BufferType type) {
        super.setText(text, type);
    }

    private int getLengthOfString(String string) {
        if (string == null) {
            return 0;
        }
        return string.length();
    }

    private String getContentOfString(String string) {
        if (string == null) {
            return "";
        }
        return string;
    }


    boolean issetSpecialColor = false;//是否有不同字体需要变颜色
    int specialColorStart;//开始变色的位置
    int specialColorLenth;//需要变色的长度
    ForegroundColorSpan colorSpan;

    public void setSpecialColor(int start, int lenth, ForegroundColorSpan colorSpan) {
        specialColorStart = start;
        specialColorLenth = lenth;
        this.colorSpan = colorSpan;
        issetSpecialColor = true;
    }

    class CenteredImageSpan extends ImageSpan {

        public CenteredImageSpan(Context context, final int drawableRes) {
            super(context, drawableRes);
        }

        @Override
        public void draw(@NonNull Canvas canvas, CharSequence text,
                         int start, int end, float x,
                         int top, int y, int bottom, @NonNull Paint paint) {
            // image to draw
            Drawable b = getDrawable();
            // font metrics of text to be replaced
            Paint.FontMetricsInt fm = paint.getFontMetricsInt();
            int transY = (y + fm.descent + y + fm.ascent) / 2
                    - b.getBounds().bottom / 2;

            canvas.save();
            canvas.translate(x, transY);
            b.draw(canvas);
            canvas.restore();
        }
    }

}
