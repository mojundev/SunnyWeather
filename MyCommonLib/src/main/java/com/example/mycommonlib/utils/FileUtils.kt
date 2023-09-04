package com.example.mycommonlib.utils

import android.content.Context
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 文件读写数据工具类
 * /data/data/<package name>/files/目录
 */
object FileUtils {

    fun save(context: Context, inputText: String, fileName: String) {
        try {
            val output = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun read(context: Context, fileName: String): String {
        val stringBuilder = StringBuilder()
        try {
            val input = context.openFileInput(fileName)
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                it.forEachLine { line->
                    stringBuilder.append(line)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

}