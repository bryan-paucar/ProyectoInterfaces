package com.example.proyectointerfaces

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

/**
 * ViewModel para gestionar el estado y la lógica de la conversión de temperaturas.
 *
 * Mantiene la temperatura actual, la preferencia de visualización (Celsius o Fahrenheit)
 * y un historial de las últimas 50 temperaturas registradas.
 */
class TemperaturaViewModel : ViewModel() {

    /** Temperatura actual ingresada por el usuario. */
    var temperatura = mutableStateOf(0f)

    /** Indica si la temperatura se muestra en Celsius (`true`) o en Fahrenheit (`false`). */
    var mostrarEnCelsius = mutableStateOf(true)

    /** Historial de las últimas 50 temperaturas registradas. */
    var historial = mutableStateListOf<Float>()

    /**
     * Agrega la temperatura actual al historial.
     *
     * Si el historial ya contiene 50 temperaturas, elimina la más antigua antes de agregar la nueva.
     */
    fun agregarTemperatura() {
        if (historial.size >= 50) {
            historial.removeAt(0) // Eliminamos la más antigua si ya hay 50
        }
        historial.add(temperatura.value)
    }
}
