package com.example.springprojectstationbuild.service

import com.example.springprojectstationbuild.RetrofitClientInstance
import com.example.springprojectstationbuild.dao.IComponentDAO
import com.example.springprojectstationbuild.dto.ComputerComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

interface IComponentService {
    suspend fun fetchComputerComponents() : List<ComputerComponent>?
}

class ComponentService : IComponentService {
    override suspend fun fetchComputerComponents(): List<ComputerComponent>? {
        return withContext(Dispatchers.IO){
            val service = RetrofitClientInstance.retrofitInstance?.create(IComponentDAO::class.java)
            val response = service!!.getAllComponents().awaitResponse()
            if (response.isSuccessful)
            {
                return@withContext response.body()
            }else{
                throw Exception("Failed to get components. Server Response: ${response.code()}")
            }
        }
    }
}