package com.example.springprojectstationbuild.dto

import com.google.gson.annotations.SerializedName

data class ComputerComponent(
    @SerializedName("Price") var price : Int,
    @SerializedName("Name") var name: String
    ){
        var component = name + " " + price
        override fun toString(): String {
        return component
    }
}
