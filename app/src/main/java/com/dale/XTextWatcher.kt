package com.dale

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

private typealias BeforeTextChanged =
            (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit

private typealias OnTextChanged =
            (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit

private typealias AfterTextChanged = (s: Editable?) -> Unit

class XTextWatcher : TextWatcher {

    private var beforeTextChanged: BeforeTextChanged? = null
    private var onTextChanged: OnTextChanged? = null
    private var afterTextChanged: AfterTextChanged? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
        beforeTextChanged?.invoke(s, start, count, after) ?: Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
        onTextChanged?.invoke(s, start, before, count) ?: Unit

    override fun afterTextChanged(s: Editable?) =
        afterTextChanged?.invoke(s) ?: Unit

    fun beforeTextChanged(callback: BeforeTextChanged) {
        beforeTextChanged = callback
    }

    fun onTextChanged(callback: OnTextChanged) {
        onTextChanged = callback
    }

    fun afterTextChanged(callback: AfterTextChanged) {
        afterTextChanged = callback
    }

}

fun EditText.registerTextWatcher(function: XTextWatcher.() -> Unit) {
    this.addTextChangedListener(XTextWatcher().also(function))
}


//tv.addTextChangedListenerDsl {
//    onTextChanged { charSequence, start, after, count ->
//        print("文字修改为：$charSequence")
//    }
//}
