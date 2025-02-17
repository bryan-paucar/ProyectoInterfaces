package com.example.proyectointerfaces

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class ContactosViewModel : ViewModel() {

    // 🔹 Lista de ciudades con sus mapas asociados
    val ciudades = mapOf(
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

    // 🔹 Servicios corregidos
    val servicios = listOf("Emergencias", "Policía", "Empresa de taxi", "Oficina de la empresa")

    // 🔹 Números de teléfono de cada servicio por ciudad
    val telefonos = mapOf(
        // Madrid
        "Madrid-Emergencias" to "+34 112",
        "Madrid-Policía" to "+34 091",
        "Madrid-Empresa de taxi" to "+34 666 123 456",
        "Madrid-Oficina de la empresa" to "+34 900 123 456",

        // París
        "París-Emergencias" to "+33 112",
        "París-Policía" to "+33 17",
        "París-Empresa de taxi" to "+33 612 987 654",
        "París-Oficina de la empresa" to "+33 800 987 654",

        // Londres
        "Londres-Emergencias" to "+44 112",
        "Londres-Policía" to "+44 999",
        "Londres-Empresa de taxi" to "+44 7123 456 789",
        "Londres-Oficina de la empresa" to "+44 800 111 222",

        // Porto Alegre
        "Porto Alegre-Emergencias" to "+55 190",
        "Porto Alegre-Policía" to "+55 193",
        "Porto Alegre-Empresa de taxi" to "+55 519 9999 8888",
        "Porto Alegre-Oficina de la empresa" to "+55 51 1234 5678",

        // Acapulco
        "Acapulco-Emergencias" to "+52 911",
        "Acapulco-Policía" to "+52 089",
        "Acapulco-Empresa de taxi" to "+52 744 567 8901",
        "Acapulco-Oficina de la empresa" to "+52 744 234 5678",

        // Vancouver
        "Vancouver-Emergencias" to "+1 911",
        "Vancouver-Policía" to "+1 604 123 4567",
        "Vancouver-Empresa de taxi" to "+1 604 987 6543",
        "Vancouver-Oficina de la empresa" to "+1 604 222 3333",

        // Houston
        "Houston-Emergencias" to "+1 911",
        "Houston-Policía" to "+1 713 123 4567",
        "Houston-Empresa de taxi" to "+1 713 987 6543",
        "Houston-Oficina de la empresa" to "+1 713 222 3333",

        // Casablanca
        "Casablanca-Emergencias" to "+212 19",
        "Casablanca-Policía" to "+212 177",
        "Casablanca-Empresa de taxi" to "+212 661 234 567",
        "Casablanca-Oficina de la empresa" to "+212 522 345 678",

        // Osaka
        "Osaka-Emergencias" to "+81 119",
        "Osaka-Policía" to "+81 110",
        "Osaka-Empresa de taxi" to "+81 6 1234 5678",
        "Osaka-Oficina de la empresa" to "+81 6 2345 6789",

        // Melbourne
        "Melbourne-Emergencias" to "+61 000",
        "Melbourne-Policía" to "+61 131 444",
        "Melbourne-Empresa de taxi" to "+61 3 9876 5432",
        "Melbourne-Oficina de la empresa" to "+61 3 2345 6789",

        // Ankara
        "Ankara-Emergencias" to "+90 112",
        "Ankara-Policía" to "+90 155",
        "Ankara-Empresa de taxi" to "+90 312 345 6789",
        "Ankara-Oficina de la empresa" to "+90 312 456 7890",

        // Dubai
        "Dubai-Emergencias" to "+971 999",
        "Dubai-Policía" to "+971 901",
        "Dubai-Empresa de taxi" to "+971 4 567 8901",
        "Dubai-Oficina de la empresa" to "+971 4 678 9012"
    )

    // 🔹 Contactos de la empresa (solo para "Oficina de la empresa")
    val contactos = mapOf(
        "Madrid-Oficina de la empresa" to Contacto("Carlos López", "+34 900 123 456", "+34 666 555 444", "carlos.lopez@madrid.com"),
        "París-Oficina de la empresa" to Contacto("Marie Dupont", "+33 800 987 654", "+33 612 345 678", "marie.dupont@paris.com"),
        "Londres-Oficina de la empresa" to Contacto("John Smith", "+44 800 111 222", "+44 7123 456 789", "john.smith@london.com"),
        "Porto Alegre-Oficina de la empresa" to Contacto("Fernando Oliveira", "+55 51 1234 5678", "+55 519 8765 4321", "fernando.oliveira@portoalegre.com"),
        "Acapulco-Oficina de la empresa" to Contacto("Sofía Ramírez", "+52 744 234 5678", "+52 744 876 5432", "sofia.ramirez@acapulco.com"),
        "Vancouver-Oficina de la empresa" to Contacto("Michael Johnson", "+1 604 222 3333", "+1 604 987 6543", "michael.johnson@vancouver.com"),
        "Houston-Oficina de la empresa" to Contacto("Robert Martinez", "+1 713 222 3333", "+1 713 999 8888", "robert.martinez@houston.com"),
        "Casablanca-Oficina de la empresa" to Contacto("Amina El-Fassi", "+212 522 345 678", "+212 661 987 654", "amina.elfassi@casablanca.com"),
        "Osaka-Oficina de la empresa" to Contacto("Takeshi Nakamura", "+81 6 2345 6789", "+81 90 1234 5678", "takeshi.nakamura@osaka.com"),
        "Melbourne-Oficina de la empresa" to Contacto("Emily White", "+61 3 2345 6789", "+61 400 123 456", "emily.white@melbourne.com"),
        "Ankara-Oficina de la empresa" to Contacto("Mehmet Yilmaz", "+90 312 456 7890", "+90 532 987 6543", "mehmet.yilmaz@ankara.com"),
        "Dubai-Oficina de la empresa" to Contacto("Ahmed Al-Farsi", "+971 4 678 9012", "+971 555 666 777", "ahmed.alfarsi@dubai.com")
    )


    // 🔹 Estados de la selección
    var ciudadSeleccionada = mutableStateOf("Madrid")
    var servicioSeleccionado = mutableStateOf("Emergencias")

    // 🔹 Obtener el mapa de la ciudad seleccionada
    fun obtenerMapa(): Int {
        return ciudades[ciudadSeleccionada.value] ?: error("Ciudad no encontrada en el mapa")
    }

    // 🔹 Obtener el teléfono del servicio seleccionado
    fun obtenerTelefono(): String {
        val clave = "${ciudadSeleccionada.value}-${servicioSeleccionado.value}"
        return telefonos[clave] ?: "No disponible"
    }

    // 🔹 Obtener el contacto si existe (solo para "Oficina de la empresa")
    fun obtenerContacto(): Contacto? {
        val clave = "${ciudadSeleccionada.value}-${servicioSeleccionado.value}"
        return contactos[clave]
    }

    // 🔹 Métodos para actualizar la selección
    fun seleccionarCiudad(nuevaCiudad: String) {
        ciudadSeleccionada.value = nuevaCiudad
    }

    fun seleccionarServicio(nuevoServicio: String) {
        servicioSeleccionado.value = nuevoServicio
    }
}



