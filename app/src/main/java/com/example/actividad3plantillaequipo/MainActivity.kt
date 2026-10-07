package com.example.actividad3plantillaequipo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actividad3plantillaequipo.ui.theme.Actividad3PlantillaEquipoTheme

private val FondoApp = Color(0xFF0B0F14)
private val TextoClaro = Color(0xFFE8EDF5)
private val TextoSuave = Color(0xFF95A3B8)
private val VerdeNeon  = Color(0xFF4ADE80)

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
    Column(
        modifier = Modifier
            .fillMaxSize()       // Ocupamos Toda la pantalla
            .background(FondoApp)          // fondo con color declarado
            .statusBarsPadding() // Respeta la barra de estado superior (reloj, batería, etc.)
            .padding(horizontal = 20.dp) // Margen lateral
    ) {
        // Titulo
        Text(
            text = "TEAM TITANS",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 3.sp,
            modifier = Modifier
                .padding(top = 28.dp, bottom = 20.dp)
                .align(Alignment.CenterHorizontally) //CentraHorizontal
        )

        Jugador(
            nombre = "AlexPro",
            rol = "Atacante",
            edad = 21,
            nivel = 87,
            imagen = R.drawable.m01,

        )
        SeparadorJugadores()
        Jugador (
            nombre = "PedroPro",
            rol = "Defensor",
            edad = 27,
            nivel = 67,
            imagen = R.drawable.m02,
        )
        SeparadorJugadores()
        Jugador (
            nombre = "Sckoty",
            rol = "Soporte",
            edad = 14,
            nivel = 99,
            imagen = R.drawable.m03,
        )
        SeparadorJugadores()
        Jugador (
            nombre = "Raiden",
            rol = "Jungla",
            edad = 41,
            nivel = 87,
            imagen = R.drawable.m04,
        )
    }
}

@Composable
fun Jugador(
    nombre: String,
    rol: String,
    edad: Int,
    nivel: Int,
    @DrawableRes imagen: Int,
)
{
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically // foto y textos centrados entre si
    ) {
        Image(
            painter = painterResource(id = imagen),
            contentDescription = "Foto de $nombre",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(74.dp)          // 1o fijo el tamaño
                .clip(CircleShape)       // 2o recorto ESE tamaño en círculo
        )
        Column(
            modifier = Modifier
                .padding(start = 18.dp)
        ) {
            Text(
                text = nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = TextoClaro,
                letterSpacing = 0.5.sp
            )
            Text(
                text = "$rol . $edad añós",
                fontSize = 14.sp,
                color = TextoSuave,
            )
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)                             // separa el badge de arriba
                    .clip(RoundedCornerShape(50))                      // forma de pastilla
                    .background(VerdeNeon)                              // pinta dentro del recorte
                    .padding(horizontal = 12.dp, vertical = 4.dp)   // aire INTERNO
            ) {
                Text(
                    text = "Nivel $nivel",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = FondoApp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}




@Composable
fun SeparadorJugadores() {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 4.dp),
        thickness = 1.dp,
        color = VerdeNeon.copy(alpha = 0.25f)
    )
}

@Preview(showBackground = true, name = "Pantalla Completa")
@Composable
fun PantallaPreview() {
    Actividad3PlantillaEquipoTheme {
            PantallaPlantilla()
        }
}
@Preview(showBackground = true)
@Composable
fun JugadorPreview() {
    Actividad3PlantillaEquipoTheme {
        Box(
            modifier = Modifier
                .background(FondoApp)
                .padding(16.dp)
        ) {
            Jugador (
                nombre = "AlexPro",
                rol = "Atacante",
                edad = 21,
                nivel = 87,
                imagen = R.drawable.m01,
            )
        }
    }
}