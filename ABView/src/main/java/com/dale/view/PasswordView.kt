package com.dale.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.ColorInt

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.MutableLiveData
import com.dale.utils.SizeUtils


class PasswordView : AppCompatEditText {

    private lateinit var eyeShow: Drawable
    private lateinit var eyeHide: Drawable
    private lateinit var delete: Drawable
    private var eye: Drawable? = null
    private var visible = false
    private var drawableClick: Boolean = false
    private var deleteClick: Boolean = false
    private var mHeight: Int = 0
    private var picWidth: Int = 0
    private var picHeight: Int = 0
    private var mLeft: Int = 0
    private var mRight: Int = 0
    private var showPaddingRight: Float = 0.toFloat()
    private var showDelete: Boolean = false
    private lateinit var mPaint: Paint

    private val color: Int by lazy {
        Color.rgb(248, 250, 250)
    }
    private var fouces: Boolean = false


    val isEmptyListener: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        attrs?.let {
            val a = context.theme.obtainStyledAttributes(
                it,
                R.styleable.PasswordView,
                0, 0)
            try {
                showPaddingRight = a.getDimension(R.styleable.PasswordView_eyePaddingRight, SizeUtils.dp2px(12f).toFloat())
            } finally {
                a.recycle()
            }
        }
        val enabledColor = resolveAttr()
        eyeShow = getDrawableWithIntrinsicBounds(context, R.mipmap.x_eye_open, enabledColor)
        eyeHide = getDrawableWithIntrinsicBounds(context, R.mipmap.x_eye_close, enabledColor)
        delete = getDrawableWithIntrinsicBounds(context, R.mipmap.x_icon_edit_delete, enabledColor)
        picWidth = eyeShow.intrinsicWidth
        picHeight = eyeShow.intrinsicHeight
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty() && !showDelete) {
                    showDelete = true
                    setDelete()
                }
                if (s.isEmpty() && showDelete) {
                    showDelete = false
                    setDelete()
                }
                isEmptyListener.value = s.isNotEmpty()
            }
        })
        setEye()
        setDelete()
        this.keyListener = DigitsKeyListener.getInstance("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789")
        this.background = null
        setUnderLine()
        setRawInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        this.setOnFocusChangeListener { v, hasFocus ->
            showDelete = hasFocus && this.text.toString().isNotEmpty()
            setDelete()
        }
    }

    private fun setUnderLine() {
        mPaint = Paint()
        mPaint.strokeWidth = 3.0f
        mPaint.color = color
    }

    @ColorInt
    private fun resolveAttr(): Int {
        return Color.rgb(206, 212, 217)
    }

    private fun getDrawableWithIntrinsicBounds(context: Context, @DrawableRes drawableResId: Int, color: Int): Drawable {

        val drawable = ContextCompat.getDrawable(context, drawableResId)!!.mutate()
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        DrawableCompat.setTint(drawable, color)
        return drawable
    }


    fun setEye() {
        val start = selectionStart
        val end = selectionEnd
        this.transformationMethod = if (visible) HideReturnsTransformationMethod.getInstance() else PasswordTransformationMethod.getInstance()
        setSelection(start, end)
        eye = if (visible) {
            eyeShow
        } else {
            eyeHide
        }
        invalidate()

    }

    fun setEye(visible : Boolean) {
        this.visible = visible
        setEye()
    }

    private fun setDelete() {
        val start = selectionStart
        val end = selectionEnd
        this.setSelection(start, end)
        if (showDelete) {
            val drawables = compoundDrawables
            this.setCompoundDrawables(drawables[0], drawables[1], delete, drawables[3])
        } else {
            val drawables = compoundDrawables
            this.setCompoundDrawables(drawables[0], drawables[1], null, drawables[3])
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.drawLine(0F, (this.height - 3).toFloat(), this.width.toFloat(),
            (this.height - 3).toFloat(), mPaint)
        eye?.setBounds(mLeft + scrollX, (mHeight - picHeight) / 2, mRight + scrollX, (mHeight + picHeight) / 2)
        eye?.draw(canvas)
        canvas.restore()
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val deleteRightX = width - paddingRight
        val deleteLeftX = deleteRightX - delete.intrinsicWidth
        val eyeClicked = event.x >= mLeft && event.x <= mRight
        val deleteClicked = event.x >= deleteLeftX && event.x <= deleteRightX

        if (event.action == MotionEvent.ACTION_DOWN && eyeClicked) {
            drawableClick = true
            return true
        }
        if (event.action == MotionEvent.ACTION_DOWN && deleteClicked) {
            deleteClick = true
            return true
        }

        if (event.action == MotionEvent.ACTION_UP) {
            if (eyeClicked && drawableClick) {
                drawableClick = false
                visible = !visible
                setEye()
                return true
            } else if (deleteClicked && deleteClick && showDelete) {
                setText("")
                return true

            } else {
                drawableClick = false
                deleteClick = false
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mHeight = height
        mLeft = (width.toFloat() - showPaddingRight - picWidth.toFloat()).toInt()
        mRight = mLeft + picWidth

    }

    override fun setError(error: CharSequence) {
        throw RuntimeException("Please use TextInputLayout.setError() instead!")
    }

    override fun setError(error: CharSequence, icon: Drawable) {
        throw RuntimeException("Please use TextInputLayout.setError() instead!")
    }

}
