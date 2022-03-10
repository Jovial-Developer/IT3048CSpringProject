package com.example.springprojectstationbuild
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState



class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
  //          viewModel.fetchComputerComponents()
            val components by viewModel.components.observeAsState(initial = emptyList())
            R.layout.settings_activity

        }
    }
}
