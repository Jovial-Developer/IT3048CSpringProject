package com.example.springprojectstationbuild.dto

import com.google.gson.annotations.SerializedName

//ID is generated when new object is created in firebase
//index is order in firebase object list
//Boolean hide is info on whether the user has hidden the object in the list of component objects
//Boolean addtocart is whether the user wants to add the object in their list of computer components to buy.
data class ComputerComponent(
    @SerializedName("ID") val id : Int,
    @SerializedName("index") var index : Int,
    @SerializedName("Price") var price : Int,
    @SerializedName("Name") var name: String,
    @SerializedName("Brand") var brand: String,
    @SerializedName("Model") var model: String,
    @SerializedName("Hide") var hide: Boolean,
    @SerializedName("AddToCart") var addtocart: Boolean
    ){
    private var component = name + " " + id + " " + index + " " + price + " " + brand + " " + model
            override fun toString(): String {
        return component
    }
}
