package com.spaceapps.tasks.core.extensions

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> AppCompatActivity.viewBinding(inflater:(LayoutInflater)->T) = lazy(LazyThreadSafetyMode.NONE){
    inflater.invoke(layoutInflater)
}