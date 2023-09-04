package com.example.mycommonlib.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit

object SharedPrefs {

    fun save(context: Context, rootKey: String, commit: Boolean, action: SharedPreferences.Editor.() -> Unit) {
        context.getSharedPreferences(rootKey, Context.MODE_PRIVATE).edit(commit, action)
    }

    /**
     * 泛型实化
     */
    inline fun <reified T>read(context: Context, rootKey: String, dataKey: String) : T {
        val  prefs = context.getSharedPreferences(rootKey, Context.MODE_PRIVATE)
        return when (T::class.java) {
            java.lang.Integer::class.java -> prefs.getInt(dataKey,0) as T
            java.lang.Boolean::class.java -> prefs.getBoolean(dataKey, false) as T
            java.lang.Float::class.java -> prefs.getFloat(dataKey, 0f) as T
            java.lang.Long::class.java -> prefs.getLong(dataKey, 0L) as T
            java.lang.String::class.java -> prefs.getString(dataKey, "") as T
            java.util.Set::class.java -> prefs.getStringSet(dataKey, emptySet()) as T
            else -> throw Exception("不支持的类型")
        }

    }

}