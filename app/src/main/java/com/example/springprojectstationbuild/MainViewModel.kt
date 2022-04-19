package com.example.springprojectstationbuild

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.springprojectstationbuild.dto.AddtoCart
import com.example.springprojectstationbuild.dto.ComputerComponent
import com.example.springprojectstationbuild.dto.User
import com.example.springprojectstationbuild.service.ComponentService
import com.google.android.gms.common.config.GservicesValue.value
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
//import com.google.firebase.firestore.auth.User
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch


    class MainViewModel(get: Any) : ViewModel() {


        var computerComponent by mutableStateOf(ComputerComponent())
        val NEW_Computer = "New Component"
        var user: User? = null
        var components: MutableLiveData<List<ComputerComponent>> =
            MutableLiveData<List<ComputerComponent>>()
        var componentService: ComponentService = ComponentService()
        private val storageReference = FirebaseStorage.getInstance().getReference()
        private lateinit var firestore: FirebaseFirestore

        init {
            firestore = FirebaseFirestore.getInstance()
            firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        }

        fun fetchComputerComponents() {
            viewModelScope.launch {
                var innerComponents = componentService.fetchComputerComponents()
                components.postValue(innerComponents)
            }
        }

        fun saveParts() {
            user?.let { user ->
                val document =
                    if (computerComponent.PartsID == null || computerComponent.PartsID.isEmpty()) {
                        // insert
                        firestore.collection("users").document(user.uid).collection("Parts")
                            .document()
                    } else {
                        // update
                        firestore.collection("users").document(user.uid).collection("Parts")
                            .document(computerComponent.PartsID)
                    }
            }
        }

        fun listenToParts() {
            user?.let { user ->
                firestore.collection("users").document(user.uid).collection("Parts")
                    .addSnapshotListener { snapshot, error ->
                        // see of we received an error
                        if (error != null) {
                            Log.w("listen failed.", error)
                            return@addSnapshotListener
                        }
                        // if we reached this point, there was not an error, and we have data.
                        snapshot?.let {
                            val allParts = ArrayList<ComputerComponent>()
                            allParts.add(ComputerComponent(NEW_Computer))
                            val documents = snapshot.documents
                            documents.forEach {
                                val computer = it.toObject(ComputerComponent::class.java)
                                computer?.let {
                                    allParts.add(computer)
                                }
                            }
                            ComputerComponent.value = allParts
                        }
                    }
            }
        }

        fun saveUser() {user?.let { user ->
            val handle = firestore.collection("users").document(user.uid).set(user)
            handle.addOnSuccessListener { Log.d("Firebase", "User Saved") }
            handle.addOnFailureListener { Log.e("Firebase", "User save failed $it") }

        }
        }

        internal fun updateCart(photo: AddtoCart) {
            user?.let { user ->
                val photoDocument =
                    if (photo.id.isEmpty()) {
                        // we need to create a new document.
                        firestore.collection("users").document(user.uid).collection("Parts")
                            .document(computerComponent.PartsID).collection("Component").document()
                    } else {
                        // update existing document
                        firestore.collection("users").document(user.uid).collection("Parts")
                            .document(computerComponent.PartsID).collection("Component").document(photo.id)
                    }
                photo.id = photoDocument.id
                val handle = photoDocument.set(photo)
                handle.addOnSuccessListener {
                    Log.i(ContentValues.TAG, "Successfully updated photo metadata")
                }
                handle.addOnFailureListener {
                    Log.e(ContentValues.TAG, "Failed to update updated photo metadata  ${it.message}")
                }
            }
        }
    }