package com.example.springprojectstationbuild

//import androidx.compose.runtime.livedata.observeAsState
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    lateinit var CPU: CheckBox
    lateinit var wifi: CheckBox
    lateinit var RAM: CheckBox
    lateinit var Power: CheckBox
    lateinit var Search: Button

    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.fetchComputerComponents()
            //val components by viewModel.components.observeAsState(initial = emptyList())
            R.layout.activity_main


            Search = findViewById(R.id.btnSearch)
            CPU = findViewById(R.id.cbCPU)
            Power = findViewById(R.id.cbpowersource)
            wifi = findViewById(R.id.cbwificard)
            RAM = findViewById(R.id.cbRAM)

            Search.setOnClickListener {
                val result = StringBuilder()
                result.append("selected Items")

                if (CPU.isChecked) {
                    result.append("testing checked works:CPU\n")
                }
                if (Power.isChecked) {
                    result.append("testing checked works:Power\n")
                }
                if (wifi.isChecked) {
                    result.append("testing checked works:wifi\n")
                }
                if (RAM.isChecked) {
                    result.append("testing checked works:RAM\n")
                }
                result.append("testing checked works:nothing\n")
                Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_LONG).show()}

        }
    }
}

