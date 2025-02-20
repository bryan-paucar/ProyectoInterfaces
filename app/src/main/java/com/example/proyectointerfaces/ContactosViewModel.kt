package com.example.proyectointerfaces

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

/**
 * ViewModel que gestiona la información de contactos y servicios de emergencia en distintas ciudades.
 *
 * Proporciona datos sobre ciudades, servicios disponibles, teléfonos de emergencia y contactos de oficina.
 * Permite seleccionar una ciudad y un servicio para obtener la información correspondiente.
 */
class ContactosViewModel : ViewModel() {

    /** Mapa de ciudades con su respectiva imagen representativa. */
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

    /** Lista de servicios disponibles en cada ciudad. */
    val servicios = listOf("Emergencias", "Policía", "Bomberos", "Oficina de Información y Turismo", "Ayuntamiento", "Servicio de Taxi", "Oficina")

    /** Mapa de números de teléfono asociados a cada ciudad y servicio. */
    val telefonos = mapOf(
        "Madrid-Emergencias" to "112",
        "Madrid-Policía" to "091",
        "Madrid-Bomberos" to "080",
        "Madrid-Oficina de Información y Turismo" to "+34 914 201 314",
        "Madrid-Ayuntamiento" to "+34 915 883 300",
        "Madrid-Servicio de Taxi" to "+34 915 474 700",
        "Madrid-Oficina" to "+34 913 423 600",

        "París-Emergencias" to "112",
        "París-Policía" to "17",
        "París-Bomberos" to "18",
        "París-Oficina de Información y Turismo" to "+33 1 49 52 42 63",
        "París-Ayuntamiento" to "+33 1 42 76 60 00",
        "París-Servicio de Taxi" to "+33 1 45 30 30 30",
        "París-Oficina" to "+33 1 45 26 20 30",

        "Londres-Emergencias" to "999",
        "Londres-Policía" to "101",
        "Londres-Bomberos" to "999",
        "Londres-Oficina de Información y Turismo" to "+44 20 7344 1000",
        "Londres-Ayuntamiento" to "+44 20 7983 4000",
        "Londres-Servicio de Taxi" to "+44 20 7272 0272",
        "Londres-Oficina" to "+44 20 2536 0200",

        "Porto Alegre-Emergencias" to "190",
        "Porto Alegre-Policía" to "190",
        "Porto Alegre-Bomberos" to "193",
        "Porto Alegre-Oficina de Información y Turismo" to "+55 51 3289 4285",
        "Porto Alegre-Ayuntamiento" to "+55 51 3289 1027",
        "Porto Alegre-Servicio de Taxi" to "+55 51 3211 1188",
        "Porto Alegre-Oficina" to "+55 51 5644 1000",

        "Acapulco-Emergencias" to "911",
        "Acapulco-Policía" to "911",
        "Acapulco-Bomberos" to "911",
        "Acapulco-Oficina de Información y Turismo" to "+52 744 482 2855",
        "Acapulco-Ayuntamiento" to "+52 744 482 1400",
        "Acapulco-Servicio de Taxi" to "+52 744 485 1073",
        "Acapulco-Oficina" to "+52 744 779 1000",

        "Vancouver-Emergencias" to "911",
        "Vancouver-Policía" to "911",
        "Vancouver-Bomberos" to "911",
        "Vancouver-Oficina de Información y Turismo" to "+1 604 482 2888",
        "Vancouver-Ayuntamiento" to "+1 604 873 7000",
        "Vancouver-Servicio de Taxi" to "+1 604 681 1111",
        "Vancouver-Oficina" to "+34 913 423 600",

        "Houston-Emergencias" to "911",
        "Houston-Policía" to "713 884 3131",
        "Houston-Bomberos" to "911",
        "Houston-Oficina de Información y Turismo" to "+1 713 437 5240",
        "Houston-Ayuntamiento" to "+1 713 247 1000",
        "Houston-Servicio de Taxi" to "+1 713 236 1111",
        "Houston-Oficina" to "+1 713 555 1222",

        "Casablanca-Emergencias" to "19 / 15",
        "Casablanca-Policía" to "19",
        "Casablanca-Bomberos" to "15",
        "Casablanca-Oficina de Información y Turismo" to "+212 522 225 410",
        "Casablanca-Ayuntamiento" to "+212 522 235 157",
        "Casablanca-Servicio de Taxi" to "+212 522 252 014",
        "Casablanca-Oficina" to "+212 522 449 000",

        "Osaka-Emergencias" to "110 / 119",
        "Osaka-Policía" to "110",
        "Osaka-Bomberos" to "119",
        "Osaka-Oficina de Información y Turismo" to "+81 6 6345 3301",
        "Osaka-Ayuntamiento" to "+81 6 6208 7171",
        "Osaka-Servicio de Taxi" to "+81 6 6345 1234",
        "Osaka-Oficina" to "+81 6 4882 6600",

        "Melbourne-Emergencias" to "000",
        "Melbourne-Policía" to "000",
        "Melbourne-Bomberos" to "000",
        "Melbourne-Oficina de Información y Turismo" to "+61 3 9658 9658",
        "Melbourne-Ayuntamiento" to "+61 3 9658 9658",
        "Melbourne-Servicio de Taxi" to "+61 3 8413 7300",
        "Melbourne-Oficina" to "+61 3 9974 9600",

        "Ankara-Emergencias" to "112",
        "Ankara-Policía" to "155",
        "Ankara-Bomberos" to "110",
        "Ankara-Oficina de Información y Turismo" to "+90 312 310 13 55",
        "Ankara-Ayuntamiento" to "+90 312 507 10 00",
        "Ankara-Servicio de Taxi" to "+90 312 444 75 47",
        "Ankara-Oficina" to "+90 312 822 70 00",

        "Dubai-Emergencias" to "999",
        "Dubai-Policía" to "999",
        "Dubai-Bomberos" to "997",
        "Dubai-Oficina de Información y Turismo" to "+971 4 201 5555",
        "Dubai-Ayuntamiento" to "+971 4 406 5555",
        "Dubai-Servicio de Taxi" to "+971 4 208 0808",
        "Dubai-Oficina" to "+971 4 495 7000"
    )

    /** Mapa de contactos de oficina en distintas ciudades. */
    val contactos = mapOf(
        "Madrid-Oficina" to Contacto("Antonio Avellaneda", "+34 913 423 634", "aavellaneda@splatnot.com"),
        "París-Oficina" to Contacto("François Merlin", "+33 1 45 26 22 46", "fmerlin@splatnot.com"),
        "Londres-Oficina" to Contacto("Sarah Louise Taylor", "+44 20 2536 0232", "staylor@splatnot.com"),
        "Porto Alegre-Oficina" to Contacto("Maria Fernanda Oliveira Costa", "+55 51 5644 1688", "mfoliveira@splatnot.com"),
        "Acapulco-Oficina" to Contacto("Antonio Avellaneda", "+52 744 779 1948", "aavellaneda@splatnot.com"),
        "Vancouver-Oficina" to Contacto("David Miller", "+34 913 423 634", "dmiller@splatnot.com"),
        "Houston-Oficina" to Contacto("Robinson Hill", "+1 713 555 1291", "rhill@splatnot.com"),
        "Casablanca-Oficina" to Contacto("Ahmed Ben Youssef El Fassi", "+212 522 449 644", "abenyoussef@splatnot.com"),
        "Osaka-Oficina" to Contacto("Takahashi Hiroshi", "+81 6 4882 6632", "thiroshi@splatnot.com"),
        "Melbourne-Oficina" to Contacto("Emily Johnson", "+61 3 9974 9677", "ejohnson@splatnot.com"),
        "Ankara-Oficina" to Contacto("Elif Demir", "+90 312 822 70 94", "edemir@splatnot.com"),
        "Dubai-Oficina" to Contacto("Khalid Al Maktoum", "+971 4 495 7556", "kalmaktoum@splatnot.com")
    )

    var ciudadSeleccionada = mutableStateOf("Madrid")
    var servicioSeleccionado = mutableStateOf("Emergencias")

    /**
     * Obtiene el recurso de imagen asociado a la ciudad seleccionada.
     *
     * @return Identificador del recurso de imagen de la ciudad seleccionada.
     * @throws IllegalArgumentException Si la ciudad no está en el mapa.
     */
    fun obtenerMapa(): Int {
        return ciudades[ciudadSeleccionada.value] ?: error("Ciudad no encontrada en el mapa")
    }

    /**
     * Obtiene el número de teléfono del servicio seleccionado en la ciudad actual.
     *
     * @return Número de teléfono del servicio seleccionado o "No disponible" si no se encuentra.
     */
    fun obtenerTelefono(): String {
        val clave = "${ciudadSeleccionada.value}-${servicioSeleccionado.value}"
        return telefonos[clave] ?: "No disponible"
    }

    /**
     * Obtiene el contacto de oficina en la ciudad seleccionada.
     *
     * @return Instancia de [Contacto] si existe, o `null` si no hay contacto disponible.
     */
    fun obtenerContacto(): Contacto? {
        val clave = "${ciudadSeleccionada.value}-Oficina"
        return contactos[clave]
    }

    /**
     * Cambia la ciudad seleccionada.
     *
     * @param nuevaCiudad Nueva ciudad seleccionada por el usuario.
     */
    fun seleccionarCiudad(nuevaCiudad: String) {
        ciudadSeleccionada.value = nuevaCiudad
    }

    /**
     * Cambia el servicio seleccionado dentro de la ciudad actual.
     *
     * @param nuevoServicio Nuevo servicio seleccionado por el usuario.
     */
    fun seleccionarServicio(nuevoServicio: String) {
        servicioSeleccionado.value = nuevoServicio
    }


}

/**
 * Representa la información de contacto de una oficina en una ciudad.
 *
 * @param nombre Nombre de la persona de contacto.
 * @param telefono Número de teléfono del contacto.
 * @param email Dirección de correo electrónico del contacto.
 */
data class Contacto(val nombre: String, val telefono: String, val email: String)
