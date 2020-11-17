package com.pgricha.neonapp.util

import android.util.Log
import com.pgricha.neonapp.R
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

fun getDecimalFormat(): DecimalFormat? {
    val decimalFormat = DecimalFormat(
        "##,###,###,##0.00",
        DecimalFormatSymbols(Locale("pt", "BR"))
    )
    decimalFormat.setMinimumFractionDigits(2)
    decimalFormat.setParseBigDecimal(true)
    return decimalFormat
}

fun moneyStringToDouble(value: String): Double {
    return value.replace(".", "").replace(",", ".").toDouble()
}

fun parseToBigDecimal(value: String, locale: Locale): BigDecimal {
    val replaceable = java.lang.String.format(
        "[%s,.\\s]",
        NumberFormat.getInstance(locale).currency.getSymbol()
    )
    val cleanString = value.replace(replaceable.toRegex(), "")
    return BigDecimal(cleanString).setScale(
        2, BigDecimal.ROUND_FLOOR
    ).divide(
        BigDecimal(100), BigDecimal.ROUND_FLOOR
    )
}

fun formatCurrancyValueWithoutSimbol(value: BigDecimal?): String {
    try {
        if (value != null) {
            val locale = Locale("pt", "BR")
            return NumberFormat.getCurrencyInstance(locale).format(value)
                .replace("[^\\d,.]".toRegex(), "")
        }
    } catch (e: Exception) {
        Log.i("Error", e.message.toString())
    }
    return ""
}

fun getMockImage(nome: String?): Int {
    val imgName = nome?.toLowerCase(Locale.ROOT)?.replace(" ", "")

    when (imgName) {
        "chandlerbing" -> return R.drawable.chandlerbing
        "harrypotter" -> return R.drawable.harrypotter
        else -> {
            return R.drawable.jonsnow
        }
    }
}