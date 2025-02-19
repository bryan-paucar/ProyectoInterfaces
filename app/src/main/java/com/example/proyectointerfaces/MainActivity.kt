package com.example.proyectointerfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectointerfaces.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {}
        }
    }
}

/**
 * AppScaffold es el componente principal de la aplicación que proporciona la estructura base de la UI.
 *
 * - Implementa una barra superior y una barra inferior a través de `AppTopBar` y `AppBottomBar`.
 * - Maneja la navegación entre diferentes pantallas usando un `NavController` y un `NavHost`.
 * - Mantiene el estado de la aplicación mediante ViewModels compartidos: `TemperaturaViewModel`, `HorasViewModel` y `ContactosViewModel`.
 *
 * Pantallas incluidas:
 * 1. `TemperaturaScreen` para mostrar conversiones de temperatura.
 * 2. `HorasScreen` para mostrar la hora en diferentes ciudades.
 * 3. `ContactosScreen` para mostrar una lista de contactos.
 */
@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    // Crear el ViewModel aquí para que se mantenga en toda la app
    val temperaturaViewModel: TemperaturaViewModel = viewModel()
    val horasViewModel: HorasViewModel = viewModel() // ViewModel compartido
    val contactosViewModel: ContactosViewModel = viewModel()

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(navController) },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Temperatura.route
            ) {
                composable(Screen.Temperatura.route) {
                    TemperaturaScreen(padding = paddingValues, viewModel = temperaturaViewModel)
                }
                composable(Screen.Horas.route) {
                    HorasScreen(padding = paddingValues, viewModel = horasViewModel)
                }
                composable(Screen.Contactos.route) {
                    ContactosScreen(padding = paddingValues, viewModel = contactosViewModel)
                }
            }
        }
    )
}

/**
 * Clase sellada (`sealed class`) que define las rutas de navegación dentro de la aplicación.
 *
 * Cada objeto dentro de `Screen` representa una pantalla específica con su correspondiente `route`:
 * - `Temperatura` → Ruta: `"temperatura"`
 * - `Horas` → Ruta: `"horas"`
 * - `Contactos` → Ruta: `"contactos"`
 *
 * Esta clase se utiliza en conjunto con `NavController` para gestionar la navegación entre pantallas.
 */
sealed class Screen(val route: String) {
    object Temperatura : Screen("temperatura")
    object Horas : Screen("horas")
    object Contactos : Screen("contactos")
}

/**
 * Composable que representa la barra superior de la aplicación.
 *
 * @OptIn(ExperimentalMaterial3Api::class) Se utiliza la API experimental de Material 3 para `TopAppBar`.
 *
 * La barra superior incluye:
 * - Un logotipo (`Image`) alineado a la izquierda.
 * - Dos botones de acción (`IconButton`):
 *   - **Configuración** (`Settings`): Actualmente sin funcionalidad.
 *   - **Inicio de sesión** (`AccountBox`): Actualmente sin funcionalidad.
 *
 * @Composable
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo2),
                    contentDescription = "Logo",
                    modifier = Modifier.size(170.dp) // Tamaño ajustado para no desbalancear la barra
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Sin funcionalidad, es estático */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer // Asegura contraste
                )
            }
            IconButton(onClick = { /* Sin funcionalidad, es estático */ }) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Login",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

/*
@Composable
fun AppBottomBar(navController: NavController) {
    val items = listOf(
        Screen.Temperatura,
        Screen.Horas,
        Screen.Contactos
    )

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == screen.route,
                onClick = { navController.navigate(screen.route) },
                label = { Text(text = screen.route.replaceFirstChar { it.uppercase() }) },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = when (screen) {
                                is Screen.Temperatura -> R.drawable.temp
                                is Screen.Horas -> R.drawable.clock
                                is Screen.Contactos -> R.drawable.contacts
                            }
                        ),
                        contentDescription = screen.route
                    )
                }
            )
        }
    }
}
*/

/**
 * Composable que representa la barra de navegación inferior de la aplicación.
 *
 * @param navController Controlador de navegación (`NavController`) utilizado para gestionar la navegación entre pantallas.
 *
 * La barra inferior contiene tres elementos de navegación (`NavigationBarItem`):
 * - **Temperatura**: Navega a la pantalla de conversión de temperatura.
 * - **Horas**: Navega a la pantalla de horarios en diferentes ciudades.
 * - **Contactos**: Navega a la pantalla de contactos.
 *
 * Cada elemento incluye:
 * - Un **icono** representativo.
 * - Un **texto descriptivo** con la tipografía del tema.
 * - Un indicador visual de selección basado en la ruta actual.
 *
 * @Composable
 */
@Composable
fun AppBottomBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier.height(56.dp),
        containerColor = MaterialTheme.colorScheme.secondary // Fondo corporativo
    ) {
        NavigationBarItem(
            selected = navController.currentDestination?.route == Screen.Temperatura.route,
            onClick = { navController.navigate(Screen.Temperatura.route) },
            label = {
                Text(
                    text = "Temperatura",
                    style = MaterialTheme.typography.labelSmall, // Aplica tipografía del tema
                    color = MaterialTheme.colorScheme.onPrimaryContainer // Asegura contraste
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.temp),
                    contentDescription = "Temperatura",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer // Mismo color que el texto
                )
            }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == Screen.Horas.route,
            onClick = { navController.navigate(Screen.Horas.route) },
            label = {
                Text(
                    text = "Horas",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "Horas",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == Screen.Contactos.route,
            onClick = { navController.navigate(Screen.Contactos.route) },
            label = {
                Text(
                    text = "Contactos",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.contacts),
                    contentDescription = "Contactos",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        )
    }
}


/**
 * Composable que representa la pantalla de conversión de temperatura.
 *
 * @param padding Espaciado superior proporcionado por `Scaffold` para evitar superposiciones.
 * @param viewModel Instancia de `TemperaturaViewModel` utilizada para gestionar el estado de la pantalla.
 *
 * La pantalla permite:
 * - **Visualizar la temperatura actual** en Celsius o Fahrenheit.
 * - **Cambiar la unidad de temperatura** mediante un `Switch`.
 * - **Ajustar la temperatura** utilizando un `Slider` (rango: -30°C a 55°C).
 * - **Guardar la temperatura en un historial** (últimas 50 temperaturas registradas).
 * - **Mostrar una imagen representativa** del clima según la temperatura.
 * - **Desplegar un historial de temperaturas** con íconos que representan el clima frío, templado o cálido.
 *
 * @Composable
 */
@Composable
fun TemperaturaScreen(padding: PaddingValues, viewModel: TemperaturaViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MaterialTheme.colorScheme.secondaryContainer), // Fondo con color secundario
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Conversión de temperatura",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        val temperaturaActual = if (viewModel.mostrarEnCelsius.value) {
            viewModel.temperatura.value
        } else {
            (viewModel.temperatura.value * 9 / 5) + 32
        }

        val unidad = if (viewModel.mostrarEnCelsius.value) "C" else "F"

        // Imagen de fondo
        val imagenRes = when (viewModel.temperatura.value) {
            in -30f..11f -> R.drawable.frio
            in 11f..25f -> R.drawable.templado
            else -> R.drawable.calor
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Temperatura en °C a la izquierda
            Text(
                text = "${viewModel.temperatura.value.toInt()}°C",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )

            // Imagen de temperatura
            Image(
                painter = painterResource(id = imagenRes),
                contentDescription = "Imagen temperatura",
                modifier = Modifier.size(180.dp) // Ajuste de tamaño para mejorar espacio
            )

            // Temperatura en °F a la derecha
            Text(
                text = "${((viewModel.temperatura.value * 9 / 5) + 32).toInt()}°F",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        // Texto con la temperatura actual
        Text(
            text = "${temperaturaActual.toInt()}°$unidad",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer // Texto con color de alto contraste sobre fondo secundario
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Switch para cambiar entre °C y °F
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("°C", color = MaterialTheme.colorScheme.onSecondaryContainer)
            Switch(
                checked = !viewModel.mostrarEnCelsius.value,
                onCheckedChange = { viewModel.mostrarEnCelsius.value = !viewModel.mostrarEnCelsius.value },
                colors = androidx.compose.material3.SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                    checkedTrackColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            )
            Text("°F", color = MaterialTheme.colorScheme.onSecondaryContainer)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Slider para seleccionar la temperatura
        Slider(
            value = viewModel.temperatura.value,
            onValueChange = { nuevaTemperatura ->
                viewModel.temperatura.value = nuevaTemperatura
            },
            valueRange = -30f..55f,
            modifier = Modifier
                .width(280.dp)  // Ajusta el ancho del Slider (más pequeño)
                .height(54.dp), // Reduce la altura del Slider
            colors = androidx.compose.material3.SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.tertiary,
                activeTrackColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        )

        // Botón para agregar la temperatura al historial
        Button(
            onClick = { viewModel.agregarTemperatura() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Text("Guardar temperatura", color = MaterialTheme.colorScheme.onTertiaryContainer)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Historial de temperaturas (últimas 50) con iconos
        Text("Historial de temperaturas:", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSecondaryContainer)

        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(viewModel.historial.reversed()) { temp ->
                val tempFahrenheit = (temp * 9 / 5) + 32
                val iconoRes = when (temp) {
                    in -30f..12f -> R.drawable.frio
                    in 13f..25f -> R.drawable.templado
                    else -> R.drawable.calor
                }

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                    Image(
                        painter = painterResource(id = iconoRes),
                        contentDescription = "Icono de temperatura",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("${temp.toInt()}°C - ${tempFahrenheit.toInt()}°F", color = MaterialTheme.colorScheme.onSecondaryContainer)
                }
            }
        }
    }
}


/**
 * Composable que representa la pantalla de visualización de horas en diferentes ciudades.
 *
 * @param padding Espaciado superior proporcionado por `Scaffold` para evitar superposiciones.
 * @param viewModel Instancia de `HorasViewModel` utilizada para gestionar el estado de la pantalla.
 *
 * La pantalla permite:
 * - **Seleccionar una ciudad** desde un `LazyRow` con botones de selección.
 * - **Mostrar la hora de la ciudad seleccionada** con un mapa del país correspondiente.
 * - **Modificar la hora de la ciudad seleccionada** a través de un `DropdownMenu`.
 * - **Listar las horas de otras ciudades** en una `LazyColumn`, mostrando:
 *   - Mapa del país correspondiente.
 *   - Nombre de la ciudad.
 *   - Hora local.
 *
 * Diseño y estilos:
 * - Utiliza colores de `MaterialTheme` para mantener coherencia visual.
 * - Diferencia secciones con fondos secundarios y terciarios.
 * - Usa iconografía y mapas representativos para cada ciudad.
 *
 * @Composable
 */
@Composable
fun HorasScreen(padding: PaddingValues, viewModel: HorasViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MaterialTheme.colorScheme.surfaceContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Horas en distintas locaciones",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Selector de ciudades en horizontal con Cards
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.obtenerHorasEnCiudades().keys.toList()) { ciudad ->
                Card(
                    modifier = Modifier.padding(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (viewModel.ciudadSeleccionada.value == ciudad)
                            MaterialTheme.colorScheme.secondary
                        else
                            MaterialTheme.colorScheme.secondaryContainer
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Button(
                        onClick = { viewModel.seleccionarCiudad(ciudad) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            ciudad,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de la ciudad seleccionada dentro de una Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = viewModel.mapaDePaises[viewModel.ciudadSeleccionada.value]!!),
                        contentDescription = "Mapa de ${viewModel.ciudadSeleccionada.value}",
                        modifier = Modifier.size(150.dp)
                    )
                    Text(
                        text = viewModel.ciudadSeleccionada.value,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    var expanded by remember { mutableStateOf(false) }
                    Button(
                        onClick = { expanded = true },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Text(
                            viewModel.horaSeleccionada.value,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        viewModel.listaHoras.forEach { hora ->
                            DropdownMenuItem(
                                text = { Text(hora, color = MaterialTheme.colorScheme.secondary) },
                                onClick = {
                                    viewModel.actualizarHora(hora)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de horas en otras ciudades dentro de Cards
        LazyColumn {
            items(viewModel.obtenerHorasEnCiudades().entries.toList()) { (ciudad, hora) ->
                if (ciudad != viewModel.ciudadSeleccionada.value) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = viewModel.mapaDePaises[ciudad]!!),
                                    contentDescription = "Mapa de $ciudad",
                                    modifier = Modifier.size(80.dp)
                                )
                                Text(
                                    text = ciudad,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            Text(
                                text = hora,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


/**
 * Composable que representa la pantalla de contacto con información de oficinas y servicios en distintas ciudades.
 *
 * @param padding Espaciado superior proporcionado por `Scaffold` para evitar superposiciones.
 * @param viewModel Instancia de `ContactosViewModel` utilizada para gestionar el estado de la pantalla.
 *
 * La pantalla permite:
 * - **Seleccionar una ciudad y un servicio** mediante `DropDownMenus`.
 * - **Mostrar un mapa de la ciudad seleccionada** con el número de teléfono sobreimpreso.
 * - **Visualizar el nombre de la ciudad** debajo del mapa.
 * - **Mostrar información de contacto** (nombre, teléfono y email) cuando el servicio seleccionado es "Oficina".
 *
 * Diseño y estilos:
 * - Usa colores de `MaterialTheme` para mantener coherencia visual.
 * - Resalta el mapa con un fondo terciario.
 * - Asegura legibilidad del teléfono sobre el mapa con un contraste adecuado.
 *
 * @Composable
 */
@Composable
fun ContactosScreen(padding: PaddingValues, viewModel: ContactosViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MaterialTheme.colorScheme.surfaceContainer), // Fondo neutro
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Teléfonos de ayuda y contactos",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de ciudad y servicio (DropDownMenus)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CiudadSelector(viewModel)
            ServicioSelector(viewModel)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Mapa con teléfono sobreimpreso
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = viewModel.obtenerMapa()),
                contentDescription = "Mapa de ${viewModel.ciudadSeleccionada.value}",
                modifier = Modifier
                    .size(320.dp)
                    .padding(8.dp)
            )
            Text(
                text = viewModel.obtenerTelefono(),
                style = MaterialTheme.typography.headlineLarge, // Tamaño adecuado para el teléfono
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre de la ciudad debajo del mapa
        Text(
            text = viewModel.ciudadSeleccionada.value,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Mostrar información del contacto solo si el servicio es "Oficina"
        if (viewModel.servicioSeleccionado.value == "Oficina") {
            val contacto = viewModel.obtenerContacto()
            contacto?.let {
                Spacer(modifier = Modifier.height(16.dp))
                ContactoDetalles(it)
            }
        }
    }
}


/**
 * Composable que permite seleccionar una ciudad a través de un `DropdownMenu`.
 *
 * @param viewModel Instancia de `ContactosViewModel` utilizada para obtener y actualizar la ciudad seleccionada.
 *
 * Funcionalidad:
 * - **Muestra un botón con la ciudad seleccionada**.
 * - **Al presionar el botón**, se despliega un `DropdownMenu` con la lista de ciudades disponibles.
 * - **Al seleccionar una ciudad**, esta se actualiza en el `viewModel` y se cierra el menú.
 *
 * Diseño y estilos:
 * - El botón tiene un fondo de color secundario (`secondary`).
 * - El texto mantiene el contraste utilizando `onSecondary`.
 * - El menú desplegable muestra las opciones en el color `secondary` del tema.
 *
 * @Composable
 */
@Composable
fun CiudadSelector(viewModel: ContactosViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text(viewModel.ciudadSeleccionada.value, color = MaterialTheme.colorScheme.onSecondary)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            viewModel.ciudades.keys.forEach { ciudad ->
                DropdownMenuItem(
                    text = { Text(ciudad, color = MaterialTheme.colorScheme.secondary) },
                    onClick = {
                        viewModel.seleccionarCiudad(ciudad)
                        expanded = false
                    }
                )
            }
        }
    }
}


/**
 * Composable que permite seleccionar un servicio a través de un `DropdownMenu`.
 *
 * @param viewModel Instancia de `ContactosViewModel` utilizada para obtener y actualizar el servicio seleccionado.
 *
 * Funcionalidad:
 * - **Muestra un botón con el servicio actualmente seleccionado**.
 * - **Al presionar el botón**, se despliega un `DropdownMenu` con la lista de servicios disponibles.
 * - **Al seleccionar un servicio**, este se actualiza en el `viewModel` y se cierra el menú.
 *
 * Diseño y estilos:
 * - El botón tiene un fondo de color secundario (`secondary`).
 * - El texto mantiene el contraste utilizando `onSecondary`.
 * - El menú desplegable muestra las opciones en el color `secondary` del tema.
 *
 * @Composable
 */
@Composable
fun ServicioSelector(viewModel: ContactosViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text(viewModel.servicioSeleccionado.value, color = MaterialTheme.colorScheme.onSecondary)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            viewModel.servicios.forEach { servicio ->
                DropdownMenuItem(
                    text = { Text(servicio, color = MaterialTheme.colorScheme.secondary) },
                    onClick = {
                        viewModel.seleccionarServicio(servicio)
                        expanded = false
                    }
                )
            }
        }
    }
}

/**
 * Composable que muestra los detalles de un contacto seleccionado.
 *
 * @param contacto Instancia de `Contacto` que contiene la información del contacto (nombre, teléfono y correo opcional).
 *
 * Funcionalidad:
 * - **Muestra el nombre del contacto** en un estilo destacado.
 * - **Muestra el número de teléfono** del contacto.
 * - **Si el correo electrónico está disponible**, lo muestra debajo del teléfono.
 *
 * Diseño y estilos:
 * - Fondo con color `surfaceVariant` para mantener un estilo neutro.
 * - Tipografía acorde con `MaterialTheme` para garantizar legibilidad.
 * - Texto alineado al centro para una presentación limpia.
 *
 * @Composable
 */
@Composable
fun ContactoDetalles(contacto: Contacto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant), // Fondo neutro
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = contacto.nombre,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Teléfono: ${contacto.telefono}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Correo: ${contacto.email}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    AppTheme {
        AppScaffold()
    }
}