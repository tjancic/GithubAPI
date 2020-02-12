package rs.droidtech.githubapi.repository.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import com.squareup.picasso.Picasso
import rs.droidtech.githubapi.R
import rs.droidtech.githubapi.repository.GithubAPIApplication.Companion.applicationContext
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.KClass

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isOnline(): Boolean {
    val connectivityManager = this
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.apply {
        val netInfo = activeNetworkInfo
        netInfo?.let {
            if (it.isConnected) return true
        }
    }
    return false
}

fun ImageView.loadImage(path: String?) {
    path?.let {
        Picasso.get()
            .load(path)
            .error(R.drawable.ic_avatar)
            .into(this)
    } ?: Picasso.get()
        .load(R.drawable.ic_avatar)
        .into(this)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun ViewGroup.inflate(resource: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(resource, this, attachToRoot)

fun Activity.gotoActivity(
    cls: KClass<out Activity>, finish: Boolean = false,
    extras: Map<String, Any?>? = null
) {
    val intent = Intent(this, cls.java)
    extras?.forEach { intent.addExtra(it.key, it.value) }
    startActivity(intent)
    if (finish) finish()
}

fun Intent.addExtra(key: String, value: Any?) {
    when (value) {
        is Long -> putExtra(key, value)
        is String -> putExtra(key, value)
        is Boolean -> putExtra(key, value)
        is Float -> putExtra(key, value)
        is Double -> putExtra(key, value)
        is Int -> putExtra(key, value)
        is Parcelable -> putExtra(key, value)
        //Add other types when needed
    }
}

inline fun <reified T> Activity.getExtra(extra: String): T? {
    return intent.extras?.get(extra) as? T?
}

fun String.toDate(
    inputDateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    outputDateFormat: String = "dd MMM yyyy"
): String {
    return try {
        val inputSDF = SimpleDateFormat(inputDateFormat, Locale.getDefault())
        val outputSDF = SimpleDateFormat(outputDateFormat, Locale.getDefault())

        val parsedInputDate = inputSDF.parse(this)
        outputSDF.format(parsedInputDate)

    } catch (e: ParseException) {
        this
    }
}

fun showToast(message: String) =
    Toast.makeText(applicationContext(), message, Toast.LENGTH_SHORT).show()
