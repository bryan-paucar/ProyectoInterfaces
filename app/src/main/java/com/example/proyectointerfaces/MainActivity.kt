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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectointerfaces.ui.theme.AppTheme

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
    Scaffold (
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar() },
        content = { padding -> AppContent(padding) }
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
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Splatnot",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer // Color del texto en el contenedor primario
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Sin funcionalidad, es estático */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer // Color del ícono
                )
            }
            IconButton(onClick = { /* Sin funcionalidad, es estático */ }) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Login",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer // Color del ícono
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer, // Usa primaryContainer definido en Theme.kt
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer // Usa onPrimaryContainer para el título
        )
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
    AppTheme {
        AppScaffold()
    }
}