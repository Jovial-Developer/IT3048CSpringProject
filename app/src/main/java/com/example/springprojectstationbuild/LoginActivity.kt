package com.example.springprojectstationbuild


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

/**
 * A Login Form Example in Kotlin Android
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // get reference to all views
        var userName = findViewById(R.id.userName) as EditText
        var password = findViewById(R.id.password) as EditText
        var btn_reset = findViewById(R.id.btnReset) as Button
        var btn_submit = findViewById(R.id.btnSubmit) as Button

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            userName.setText("")
            password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            val user_name = userName.text;
            val password = password.text;
            Toast.makeText(this@LoginActivity, user_name, Toast.LENGTH_LONG).show()

            // your code to validate the user_name and password combination
            // and verify the same

        }
    }
}