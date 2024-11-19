package com.sergio.carlist.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.sergio.carlist.data.model.Car

class CarViewModel: ViewModel() {
    private val _cars = mutableStateListOf<Car>()
    val cars: List<Car> = _cars

    fun addCar(car: Car) {
        _cars.add(car)
    }
}