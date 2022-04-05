package com.example.springprojectstationbuild.dto

import com.google.gson.annotations.SerializedName

data class ComputerComponent(
    @SerializedName("ID") val id : String,
    @SerializedName("index") var index : Int,
    @SerializedName("Price") var price : Double,
    @SerializedName("Name") var name: String,
    @SerializedName("Brand") var brand: String,
    @SerializedName("Model") var model: String,
    @SerializedName("Hide") var hide: Boolean,
    @SerializedName("AddToCart") var addtocart: Boolean

){
    private var component = "$name, $id, $index, $price, $brand, $model, $hide, $addtocart"
    override fun toString(): String {
        return component
    }
}


