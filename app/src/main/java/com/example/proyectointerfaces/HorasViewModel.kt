package com.example.proyectointerfaces

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class CityTime(val name: String, val utcOffset: Int, val imageRes: Int)

class HorasViewModel : ViewModel() {

    // Lista de ciudades con sus respectivos husos horarios e imagenes
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
        CityTime("Dubai",4,R.drawable.emiratos)
    )
    val cities: List<CityTime> = _cities

    // Estado de la ciudad seleccionada; se inicializa con la primera ciudad de la lista
    var selectedCity by mutableStateOf(_cities.first())
        private set

    // Hora seleccionada (se inicializa con la hora actual)
    var selectedTime by mutableStateOf(LocalTime.now())
        private set

    // Actualiza la ciudad seleccionada
    fun updateSelectedCity(city: CityTime) {
        selectedCity = city
    }

    // Actualiza la hora seleccionada
    fun updateTime(newTime: LocalTime) {
        selectedTime = newTime
    }

    // Calcula y devuelve la hora local para la ciudad pasada como parámetro
    fun getLocalTime(city: CityTime): String {
        val difference = city.utcOffset - selectedCity.utcOffset
        val localTime = selectedTime.plusHours(difference.toLong())
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}
