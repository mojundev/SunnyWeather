package com.example.mycommonlib.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class BaseBindingActivity<VB : ViewBinding> : BaseActivity() {
    protected val binding by lazy { getViewBinding() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    protected abstract fun initView()

    protected abstract fun getViewBinding(): VB
}