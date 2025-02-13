package com.example.proyectointerfaces

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class TemperaturaViewModel : ViewModel() {
    var temperatura = mutableStateOf(0f)
    var mostrarEnCelsius = mutableStateOf(true) // true = Celsius, false = Fahrenheit
}

