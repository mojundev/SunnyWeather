package com.example.mycommonlib.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mycommonlib.other.ActivityCollector

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var TAG: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
        TAG = javaClass.simpleName

        Log.d(TAG, "lifecycle: onCreate" )
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "lifecycle: onRestart" )
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "lifecycle: onStart" )
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "lifecycle: onResume" )
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "lifecycle: onPause" )
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "lifecycle: onStop" )
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
        Log.d(TAG, "lifecycle: onDestroy" )
    }

}