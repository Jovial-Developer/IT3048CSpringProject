package com.example.springprojectstationbuild.dao

import com.example.springprojectstationbuild.dto.ComputerComponent
import retrofit2.Call
import retrofit2.http.GET
interface IComponentDAO {

    @GET("docyx/pc-part-dataset/main/data/raw")
    fun getAllComponents() : Call<ArrayList<ComputerComponent>>

}