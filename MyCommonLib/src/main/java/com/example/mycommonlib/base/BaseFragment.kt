package com.example.mycommonlib.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected lateinit var TAG: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        TAG = javaClass.simpleName
        Log.d(TAG, "lifecycle: onAttach" )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "lifecycle: onCreate" )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "lifecycle: onCreateView" )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "lifecycle: onViewCreated" )
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

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "lifecycle: onDestroyView" )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "lifecycle: onDestroy" )
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "lifecycle: onDetach" )
    }

}