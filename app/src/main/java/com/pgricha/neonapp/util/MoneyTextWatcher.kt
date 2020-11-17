package com.pgricha.neonapp.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.util.*

class MoneyTextWatcher : TextWatcher {
    private val editTextWeakReference: WeakReference<EditText>
    private val locale: Locale

    constructor(editText: EditText?, locale: Locale?) {
        editTextWeakReference = WeakReference<EditText>(editText)
        this.locale = if (locale != null) locale else Locale.getDefault()
    }

    constructor(editText: EditText?) {
        editTextWeakReference = WeakReference<EditText>(editText)
        locale = Locale.getDefault()
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        val editText: EditText = editTextWeakReference.get() ?: return
        editText.removeTextChangedListener(this)
        val parsed: BigDecimal = parseToBigDecimal(editable.toString(), locale)
        val formatted: String = formatCurrancyValueWithoutSimbol(parsed)
        editText.setText(formatted)
        editText.setSelection(formatted.length)
        editText.addTextChangedListener(this)
    }
}