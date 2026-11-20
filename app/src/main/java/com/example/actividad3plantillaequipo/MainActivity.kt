package com.example.actividad3plantillaequipo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actividad3plantillaequipo.ui.theme.Actividad3PlantillaEquipoTheme
import com.example.actividad3plantillaequipo.ui.theme.FondoApp
import com.example.actividad3plantillaequipo.ui.theme.TextoSuave
import com.example.actividad3plantillaequipo.ui.theme.VerdeNeon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Actividad3PlantillaEquipoTheme {
                PantallaPlantilla()
            }
        }
    }
}

@Composable
fun PantallaPlantilla() {
    val jugadores = remember {
        mutableStateListOf(
            // 1. los datos, ANTES del Column
            Jugador(1, "Alejandro Ruiz", "AlexPro", "Atacante", R.drawable.m01),
            Jugador(2, "Pedro Gil", "PedroPro", "Defensor", R.drawable.m02),
            Jugador(3, "Carlos Aponte", "Miniduke", "Mediocentro", R.drawable.m03),
            Jugador(4, "Marlon Guedez", "Selder", "Portero", R.drawable.m04),
            Jugador(5, "Carla Guedez", "Ray", "Lateral")
        )
    }
    var jugadorAEliminar by remember { mutableStateOf<Jugador?>(null) }
    var jugadorAEditar by remember { mutableStateOf<Jugador?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoApp)
            .statusBarsPadding()
            .imePadding()
            .padding(horizontal = 20.dp)

    )
    {
        TituloEquipo()
        FormularioJugador(
            onAnadir = { nombre, nickname, rol ->
                val nuevoId = (jugadores.maxOfOrNull { it.id } ?: 0) + 1
                jugadores.add(Jugador(nuevoId, nombre, nickname, rol))
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(
                items = jugadores,
                key = { jugador -> jugador.id }
            ) { jugador ->
                TarjetaJugador(jugador = jugador,
                    onEditar = {jugadorAEditar = jugador},
                    onEliminar = { jugadorAEliminar = jugador })
                SeparadorJugadores()
            }
        }
        jugadorAEliminar?.let { jugador ->
            DialogoEliminar(
                jugador = jugador,
                onConfirmar = {
                    jugadores.remove(jugador)
                    jugadorAEliminar = null
                },
                onCancelar = {
                    jugadorAEliminar = null
                }
            )
        }
        jugadorAEditar?.let { jugador ->
            DialogoEditar(
                jugador = jugador,
                onGuardar = { editado ->
                    val indice = jugadores.indexOfFirst { it.id == editado.id }
                    if (indice != -1) {
                        jugadores[indice] = editado
                    }
                    jugadorAEditar = null
                },
                onCancelar = {
                    jugadorAEditar = null
                }
            )
        }
    }
}

@Composable
fun TituloEquipo(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 20.dp)
    ){
        Text(text = "TEAM TITANS",
            color = VerdeNeon,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 3.sp,
        )

        Text(text = "Plantilla oficial",
            color = TextoSuave,
            fontSize = 14.sp,
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PantallaPreview() {
    Actividad3PlantillaEquipoTheme {
        PantallaPlantilla()
    }
}