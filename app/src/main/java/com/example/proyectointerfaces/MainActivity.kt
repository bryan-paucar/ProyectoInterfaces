package com.example.proyectointerfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectointerfaces.ui.theme.ProyectoInterfacesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoInterfacesTheme {

            }
        }
    }
}

@Composable
fun AppScaffold() {
    Scaffold (
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar() },
        content = { padding -> AppContent(padding) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    MediumTopAppBar(
        title = {
            // Box para poder mover el texto dentro de la barra superior.
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Contactos") // Texto que se muestra como título.
            }
        },
        actions = {
            Row(

            ){
                Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(100.dp)
                    )
            }
            // Ícono de editar
            IconButton(onClick = { /* TODO */ }) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Login")
            }
            // Ícono de fav
            IconButton(onClick = { /* TODO*/ }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }

        }
    )
}

@Composable
fun AppBottomBar() {

}
@Composable
fun AppContent(padding: PaddingValues) {

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ProyectoInterfacesTheme {
        AppScaffold()
    }
}