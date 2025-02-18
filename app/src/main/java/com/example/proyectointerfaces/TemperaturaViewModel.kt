package com.example.proyectointerfaces

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class TemperaturaViewModel : ViewModel() {
    var temperatura = mutableStateOf(0f)
    var mostrarEnCelsius = mutableStateOf(true)

    // Lista mutable para el historial de temperaturas (máximo 50)
    var historial = mutableStateListOf<Float>()

    // Función para agregar una nueva temperatura al historial
    fun agregarTemperatura() {
        if (historial.size >= 50) {
            historial.removeAt(0) // Eliminamos la más antigua si ya hay 50
        }
        historial.add(temperatura.value)
    }
}


