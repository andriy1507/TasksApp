package com.spaceapps.tasks

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.spaceapps.tasks.core.extensions.viewBinding
import com.spaceapps.tasks.databinding.ActivityMainWearBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainWearableActivity : AppCompatActivity(R.layout.activity_main_wear) {

    private val binding by viewBinding(ActivityMainWearBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
    }
}