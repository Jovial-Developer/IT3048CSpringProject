package com.example.springprojectstationbuild.service

import com.example.springprojectstationbuild.dto.ComputerComponent

class ComponentServiceStub {
    fun fetchComputerComponent(): Set<ComputerComponent>? {
        var fakeComputerComponent = mutableSetOf<ComputerComponent>()

        fakeComputerComponent.add(
            ComputerComponent(
            "3049HJN89",
            1,
            300.00,
            "CPU",
            "Intel",
            "CORE-i7-4953",
            false,
            false
        ))
        fakeComputerComponent.add(ComputerComponent(
            "3048HJN89",
            2,
            200.00,
            "CPU",
            "Intel",
            "CORE-i5-4954",
            false,
            false
        ))
        return fakeComputerComponent
    }

}