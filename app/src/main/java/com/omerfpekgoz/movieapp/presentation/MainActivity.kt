package com.omerfpekgoz.movieapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omerfpekgoz.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
}