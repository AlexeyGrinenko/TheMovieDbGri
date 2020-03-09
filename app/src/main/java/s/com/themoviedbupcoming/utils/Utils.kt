package s.com.themoviedbupcoming.utils

import android.util.Log
import android.view.View
import android.widget.EditText
import s.com.themoviedbupcoming.BuildConfig
import java.util.*


fun log(message: String, key: String = "Log_Key_Debug") {
    if (BuildConfig.DEBUG) Log.d(key, message)
}

//fun TextInputEditText?.string(): String = this?.text?.toString() ?: ""

fun EditText?.string(): String = this?.text?.toString()?.trim() ?: ""

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

data class Optional<V>(
    var value: V? = null,
    var error: Throwable? = null
) {

    fun empty() = value == null

    fun get() = if (value == null) throw NoSuchElementException() else value!!

    fun isError() = error != null

    fun sameValue(compared: Optional<V>) = compared.value == value
}