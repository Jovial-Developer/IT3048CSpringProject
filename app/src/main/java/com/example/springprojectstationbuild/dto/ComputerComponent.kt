package com.example.springprojectstationbuild.dto

import com.google.gson.annotations.SerializedName

data class ComputerComponent(var price : String, var name : String){
    override fun toString(): String {
        return "$price $name"
    }

}
