package com.example.proyectointerfaces

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Representa una ciudad con su nombre, zona horaria UTC y recurso de imagen asociado.
 *
 * @param name Nombre de la ciudad.
 * @param utcOffset Diferencia horaria en horas con respecto a UTC.
 * @param imageRes Identificador del recurso de imagen asociado a la ciudad.
 */
data class CityTime(val name: String, val utcOffset: Int, val imageRes: Int)

/**
 * ViewModel que gestiona la lógica de la pantalla de horas en diferentes ciudades del mundo.
 *
 * Permite seleccionar una ciudad y visualizar su hora local, basada en la diferencia de huso horario.
 */
class HorasViewModel : ViewModel() {

    /** Lista de ciudades disponibles con su huso horario y su imagen representativa. */
    private val _cities = listOf(
        CityTime("Madrid", 1, R.drawable.espana),
        CityTime("París", 1, R.drawable.francia),
        CityTime("Londres", 0, R.drawable.uk),
        CityTime("Porto Alegre", -3, R.drawable.brasil),
        CityTime("Acapulco", -6, R.drawable.mexico),
        CityTime("Vancouver", -8, R.drawable.canada),
        CityTime("Houston", -6, R.drawable.usa),
        CityTime("Casablanca", 0, R.drawable.marruecos),
        CityTime("Osaka", 9, R.drawable.japon),
        CityTime("Melbourne", 10, R.drawable.australia),
        CityTime("Ankara", 3, R.drawable.turquia),
        CityTime("Dubai", 4, R.drawable.emiratos)
    )

    /** Lista pública de ciudades disponibles. */
    val cities: List<CityTime> = _cities

    /** Ciudad actualmente seleccionada. Se inicializa con la primera ciudad de la lista. */
    var selectedCity by mutableStateOf(_cities.first())
        private set

    /** Hora seleccionada por el usuario. Se inicializa con la hora actual. */
    var selectedTime by mutableStateOf(LocalTime.now())
        private set

    /**
     * Actualiza la ciudad seleccionada.
     *
     * @param city Nueva ciudad seleccionada por el usuario.
     */
    fun updateSelectedCity(city: CityTime) {
        selectedCity = city
    }

    /**
     * Actualiza la hora seleccionada manualmente por el usuario.
     *
     * @param newTime Nueva hora establecida.
     */
    fun updateTime(newTime: LocalTime) {
        selectedTime = newTime
    }

    /**
     * Calcula la hora local de una ciudad con base en la diferencia horaria respecto a la ciudad seleccionada.
     *
     * @param city Ciudad cuya hora local se quiere calcular.
     * @return Hora local en formato "HH:mm".
     */
    fun getLocalTime(city: CityTime): String {
        val difference = city.utcOffset - selectedCity.utcOffset
        val localTime = selectedTime.plusHours(difference.toLong())
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}
