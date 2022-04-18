package com.example.springprojectstationbuild

//import androidx.compose.runtime.livedata.observeAsState
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    //var ComputerComponent = newComputerComponent()

    private var strUri by mutableStateOf("")
    private var uri: Uri?= null
    lateinit var CPU: CheckBox
    lateinit var wifi: CheckBox
    lateinit var RAM: CheckBox
    lateinit var Power: CheckBox
    lateinit var Search: Button

    private val viewModel: MainViewModel by viewModel<MainViewModel>()
    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.fetchComputerComponents()
            /*firebaseUser?.let {
                val user = User(it.uid, "")
                viewModel.user = user
                viewModel.listenToParts()
            }
*/
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
                Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_LONG).show()
            }

        }
    }

  /*  @Composable
    private fun Events () {
        val photos by viewModel.eventPhotos.observeAsState(initial = emptyList())
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp), modifier = Modifier.fillMaxHeight()) {
            items (
                items = photos,
                itemContent = {
                    EventListItem(photo = it)
                }
            )
        }
    }*/

    private fun signIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val signinIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        signInLauncher.launch(signinIntent)
    }

    private val signInLauncher = registerForActivityResult (
        FirebaseAuthUIActivityResultContract()
    ) {
            res -> this.signInResult(res)
    }


    private fun signInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            firebaseUser = FirebaseAuth.getInstance().currentUser
            firebaseUser?.let {
                val user = User(it.uid, it.displayName)
                viewModel.user = user
                viewModel.saveUser()
                viewModel.listenToParts()
            }
        } else {
            Log.e("MainActivity.kt", "Error logging in " + response?.error?.errorCode)

        }
    }
}


