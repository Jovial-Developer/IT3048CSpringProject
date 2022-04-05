package com.example.springprojectstationbuild
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.*



class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.fetchComputerComponents()
            R.layout.settings_activity
            Surface {
                mainButtons("Android")
            }
        }
    }

    @Composable
    fun mainButtons(name: String) {

        val context = LocalContext.current
        val text = "You clicked this button"
        val duration = Toast.LENGTH_LONG

        Column {

            Button(
                onClick = {
                    Toast.makeText(context, text, duration).show()
                }
            ) { Text(text = "Select Existing PC") }
            Button(
                onClick = {
                    Toast.makeText(context, text, duration).show()
                }
            ) { Text(text = "Add a new PC") }
            Button(
                onClick = {
                    Toast.makeText(context, text, duration).show()
                }
            ) { Text(text = "Login to My Account") }
            Button(
                onClick = {
                    Toast.makeText(context, text, duration).show()
                }
            ) { Text(text = "Create Account") }
        }
    }
}
