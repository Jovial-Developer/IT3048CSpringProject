package com.example.springprojectstationbuild

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.springprojectstationbuild.dto.ComputerComponent
import com.example.springprojectstationbuild.service.ComponentService
import kotlinx.coroutines.launch


    class MainViewModel : ViewModel() {


        var components : MutableLiveData<List<ComputerComponent>> = MutableLiveData<List<ComputerComponent>>()
        var componentService : ComponentService = ComponentService()


        fun fetchComputerComponents() {
            viewModelScope.launch{
                var innerComponents = componentService.fetchComputerComponents()
                components.postValue(innerComponents)
            }
        }
    }
