package com.example.proyectointerfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(navController) },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Temperatura.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screen.Temperatura.route) {
                    TemperaturaScreen(padding = paddingValues, viewModel = temperaturaViewModel) // Pasamos el ViewModel
                }
                composable(Screen.Horas.route) {
                    HorasScreen(padding = paddingValues)
                }
                composable(Screen.Contactos.route) {
                    ContactosScreen(padding = paddingValues)
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
            // Box para centrar el texto dentro de la barra
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(88.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Splatnot",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { /* Sin funcionalidad, es estático */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
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
        containerColor = MaterialTheme.colorScheme.primaryContainer // Fondo corporativo
    ) {
        NavigationBarItem(
            selected = navController.currentDestination?.route == Screen.Temperatura.route,
            onClick = { navController.navigate(Screen.Temperatura.route) },
            label = {
                Text(
                    text = "Temperatura",
                    style = AppTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.temp),
                    contentDescription = "Temperatura",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer // Siempre el mismo color
                )
            }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == Screen.Horas.route,
            onClick = { navController.navigate(Screen.Horas.route) },
            label = {
                Text(
                    text = "Horas",
                    style = AppTypography.labelSmall,
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
                    style = AppTypography.labelSmall,
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
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Convertimos la temperatura si es necesario
        val temperaturaActual = if (viewModel.mostrarEnCelsius.value) {
            viewModel.temperatura.value
        } else {
            (viewModel.temperatura.value * 9 / 5) + 32 // Conversión a Fahrenheit
        }

        // Texto con la temperatura actual
        Text(
            text = "${temperaturaActual.toInt()}°${if (viewModel.mostrarEnCelsius.value) "C" else "F"}",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Switch para cambiar entre °C y °F
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("°C")
            Switch(
                checked = !viewModel.mostrarEnCelsius.value,
                onCheckedChange = { viewModel.mostrarEnCelsius.value = !viewModel.mostrarEnCelsius.value }
            )
            Text("°F")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Slider para seleccionar la temperatura
        Slider(
            value = viewModel.temperatura.value,
            onValueChange = { nuevaTemperatura -> viewModel.temperatura.value = nuevaTemperatura },
            valueRange = -30f..55f
        )

        // Mostrar ambas temperaturas
        Text("${viewModel.temperatura.value.toInt()}°C / ${(viewModel.temperatura.value * 9 / 5 + 32).toInt()}°F")
    }
}




@Composable
fun HorasScreen(padding: PaddingValues) {
    Text(text = "Pantalla de Horas en Diferentes Ciudades")
}

@Composable
fun ContactosScreen(padding: PaddingValues) {
    Text(text = "Pantalla de Contactos y Teléfonos de Emergencia")
}


sealed class Screen(val route: String) {
    object Temperatura : Screen("temperatura")
    object Horas : Screen("horas")
    object Contactos : Screen("contactos")
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    AppTheme {
        AppScaffold()
    }
}