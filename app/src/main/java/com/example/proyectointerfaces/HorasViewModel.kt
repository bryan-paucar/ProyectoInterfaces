package com.example.proyectointerfaces

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class HorasViewModel : ViewModel() {
    // Lista de ciudades y su diferencia horaria con UTC
    private val ciudades = mapOf(
        "Madrid" to 1,
        "París" to 1,
        "Londres" to 0,
        "Porto Alegre" to -3,
        "Acapulco" to -6,
        "Vancouver" to -8,
        "Houston" to -6,
        "Casablanca" to 0,
        "Osaka" to 9,
        "Melbourne" to 10,
        "Ankara" to 3,
        "Dubai" to 4
    )

    // Mapeo de cada ciudad con su respectivo mapa de país
    val mapaDePaises = mapOf(
        "Madrid" to R.drawable.espana,
        "París" to R.drawable.francia,
        "Londres" to R.drawable.uk,
        "Porto Alegre" to R.drawable.brasil,
        "Acapulco" to R.drawable.mexico,
        "Vancouver" to R.drawable.canada,
        "Houston" to R.drawable.usa,
        "Casablanca" to R.drawable.marruecos,
        "Osaka" to R.drawable.japon,
        "Melbourne" to R.drawable.australia,
        "Ankara" to R.drawable.turquia,
        "Dubai" to R.drawable.emiratos
    )

    // Ciudad seleccionada
    var ciudadSeleccionada = mutableStateOf("Madrid")

    // Lista de horas en intervalos de 30 minutos (06:00 AM - 11:30 PM)
    val listaHoras = List(36) { i ->
        val hora = LocalTime.of(6 + (i / 2), if (i % 2 == 0) 0 else 30)
        hora.format(DateTimeFormatter.ofPattern("hh:mm a"))
    }

    // Hora seleccionada (por defecto: 12:00 PM)
    var horaSeleccionada = mutableStateOf(listaHoras[12])

    // Función para actualizar la ciudad seleccionada
    fun seleccionarCiudad(nuevaCiudad: String) {
        ciudadSeleccionada.value = nuevaCiudad
    }

    // Función para actualizar la hora seleccionada
    fun actualizarHora(nuevaHora: String) {
        horaSeleccionada.value = nuevaHora
    }

    // Función para calcular la hora en cada ciudad
    fun obtenerHorasEnCiudades(): Map<String, String> {
        val diferenciaSeleccionada = ciudades[ciudadSeleccionada.value] ?: 0
        val horaBase = LocalTime.parse(horaSeleccionada.value, DateTimeFormatter.ofPattern("hh:mm a"))

        return ciudades.mapValues { (ciudad, diferencia) ->
            val nuevaHora = horaBase.plusHours((diferencia - diferenciaSeleccionada).toLong())
            nuevaHora.format(DateTimeFormatter.ofPattern("hh:mm a"))
        }
    }
}
