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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectointerfaces.ui.theme.AppTheme
import com.example.proyectointerfaces.ui.theme.AppTypography
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {

            }
        }
    }
}

@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    // 🔹 Crear el ViewModel aquí para que se mantenga en toda la app
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(64.dp) // 🔹 Tamaño ajustado para no desbalancear la barra
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Splatnot",
                    style = MaterialTheme.typography.titleLarge, // 🔹 Aplica la tipografía del tema
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Sin funcionalidad, es estático */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer // 🔹 Asegura contraste
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
            containerColor = MaterialTheme.colorScheme.primaryContainer,
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

@Composable
fun AppBottomBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier.height(56.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer // 🔹 Fondo corporativo
    ) {
        NavigationBarItem(
            selected = navController.currentDestination?.route == Screen.Temperatura.route,
            onClick = { navController.navigate(Screen.Temperatura.route) },
            label = {
                Text(
                    text = "Temperatura",
                    style = MaterialTheme.typography.labelSmall, // 🔹 Aplica tipografía del tema
                    color = MaterialTheme.colorScheme.onPrimaryContainer // 🔹 Asegura contraste
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.temp),
                    contentDescription = "Temperatura",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer // 🔹 Mismo color que el texto
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


@Composable
fun TemperaturaScreen(padding: PaddingValues, viewModel: TemperaturaViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MaterialTheme.colorScheme.secondaryContainer), // 🔹 Fondo con color secundario
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        Image(
            painter = painterResource(id = imagenRes),
            contentDescription = "Imagen temperatura",
            modifier = Modifier
                .size(200.dp) // 🔹 Ajuste de tamaño para mejorar espacio
        )

        // Texto con la temperatura actual
        Text(
            text = "${temperaturaActual.toInt()}°$unidad",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer // 🔹 Texto con color de alto contraste sobre fondo secundario
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

        // 🔹 Historial de temperaturas (últimas 50) con iconos
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



@Composable
fun HorasScreen(padding: PaddingValues, viewModel: HorasViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MaterialTheme.colorScheme.surfaceContainer), // 🔹 Fondo con color neutro
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔹 Selector de ciudades en horizontal (LazyRow)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer), // 🔹 Fondo con color secundario
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.obtenerHorasEnCiudades().keys.toList()) { ciudad ->
                Button(
                    onClick = { viewModel.seleccionarCiudad(ciudad) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (viewModel.ciudadSeleccionada.value == ciudad)
                            MaterialTheme.colorScheme.secondary // 🔹 Resalta la ciudad seleccionada
                        else
                            MaterialTheme.colorScheme.secondaryContainer
                    ),
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(
                        ciudad,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Sección de la ciudad seleccionada con disposición horizontal
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant), // 🔹 Fondo más neutro
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 🔹 Columna izquierda (Mapa + Nombre de la ciudad)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(
                        id = viewModel.mapaDePaises[viewModel.ciudadSeleccionada.value]!!
                    ), // 🔹 Se carga el mapa correspondiente
                    contentDescription = "Mapa de ${viewModel.ciudadSeleccionada.value}",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                )
                Text(
                    text = viewModel.ciudadSeleccionada.value,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // 🔹 Columna derecha (Hora en grande + Selector de hora)
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ) {
                var expanded by remember { mutableStateOf(false) }
                Button(
                    onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Text(
                        viewModel.horaSeleccionada.value,
                        style = MaterialTheme.typography.headlineLarge, // 🔹 La hora debe ser grande y visible
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    viewModel.listaHoras.forEach { hora ->
                        DropdownMenuItem(
                            text = { Text(hora, color = MaterialTheme.colorScheme.onSecondaryContainer) },
                            onClick = {
                                viewModel.actualizarHora(hora)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Lista de horas en otras ciudades
        LazyColumn {
            items(viewModel.obtenerHorasEnCiudades().entries.toList()) { (ciudad, hora) ->
                if (ciudad != viewModel.ciudadSeleccionada.value) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.tertiaryContainer) // 🔹 Fondo con color terciario
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Image(
                                painter = painterResource(
                                    id = viewModel.mapaDePaises[ciudad]!!
                                ), // 🔹 Se carga el mapa específico
                                contentDescription = "Mapa de $ciudad",
                                modifier = Modifier.size(80.dp)
                            )
                            Text(
                                text = ciudad,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }

                        // 🔹 Texto con la hora en paralelo al mapa
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

@Composable
fun ContactosScreen(padding: PaddingValues, viewModel: ContactosViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            //.padding(16.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer), // 🔹 Fondo neutro
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔹 Selector de ciudad y servicio (DropDownMenus)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CiudadSelector(viewModel)
            ServicioSelector(viewModel)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Mapa con teléfono sobreimpreso
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer), // 🔹 Fondo del mapa resaltado
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = viewModel.obtenerMapa()),
                contentDescription = "Mapa de ${viewModel.ciudadSeleccionada.value}",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )
            Text(
                text = viewModel.obtenerTelefono(),
                style = MaterialTheme.typography.headlineSmall, // 🔹 Tamaño adecuado para el teléfono
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Nombre de la ciudad debajo del mapa
        Text(
            text = viewModel.ciudadSeleccionada.value,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Información del contacto (si aplica)
        val contacto = viewModel.obtenerContacto()
        contacto?.let {
            ContactoDetalles(it)
        }
    }
}

// 🔹 Composable para el selector de ciudad
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

// 🔹 Composable para el selector de servicio
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

// 🔹 Composable para mostrar los detalles del contacto
@Composable
fun ContactoDetalles(contacto: Contacto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant), // 🔹 Fondo neutro
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
        contacto.movil?.let {
            Text(
                text = "Móvil: $it",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        contacto.correo?.let {
            Text(
                text = "Correo: $it",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}





sealed class Screen(val route: String) {
    object Temperatura : Screen("temperatura")
    object Horas : Screen("horas")
    object Contactos : Screen("contactos")
}

data class Contacto(
    val nombre: String,
    val telefono: String,
    val movil: String?,
    val correo: String?
)



@Preview(showBackground = true)
@Composable
fun Preview() {
    AppTheme {
        AppScaffold()
    }
}