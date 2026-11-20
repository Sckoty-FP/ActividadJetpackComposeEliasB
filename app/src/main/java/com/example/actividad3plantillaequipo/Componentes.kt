package com.example.actividad3plantillaequipo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actividad3plantillaequipo.ui.theme.FondoApp
import com.example.actividad3plantillaequipo.ui.theme.TextoClaro
import com.example.actividad3plantillaequipo.ui.theme.TextoSuave
import com.example.actividad3plantillaequipo.ui.theme.VerdeNeon

@Composable
fun FormularioJugador(
    onAnadir: (nombre: String, nickname: String, rol: String) -> Unit
) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var nickname by rememberSaveable { mutableStateOf("") }
    var rol by rememberSaveable { mutableStateOf("") }
    var hayError by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            singleLine = true,
            colors = coloresCampo(),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("Nickname") },
            singleLine = true,
            colors = coloresCampo(),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = rol,
            onValueChange = { rol = it },
            label = { Text("Rol") },
            singleLine = true,
            colors = coloresCampo(),
            modifier = Modifier.fillMaxWidth()
        )

        if (hayError) {
            Text(
                text = "Rellena nombre, nickname y rol",
                color = Color(0xFFF87171),
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
        }

        Button(
            onClick = {
                if (nombre.isBlank() || nickname.isBlank() || rol.isBlank()) {
                    hayError = true
                } else {
                    onAnadir(nombre.trim(), nickname.trim(), rol.trim())  // avisa al jefe con los 3 textos
                    nombre = ""
                    nickname = ""
                    rol = ""
                    hayError = false
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeNeon,
                contentColor = FondoApp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Añadir jugador", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DialogoEliminar(
    jugador: Jugador,
    onConfirmar: () -> Unit,
    onCancelar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Eliminar jugador") },
        text = {
            Text("¿Estás seguro de eliminar a ${jugador.nickname}?")
        },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun DialogoEditar(
    jugador: Jugador,
    onGuardar: (Jugador) -> Unit,
    onCancelar: () -> Unit
) {
    var nombre by rememberSaveable { mutableStateOf(jugador.nombre) }
    var nickname by rememberSaveable { mutableStateOf(jugador.nickname) }
    var rol by rememberSaveable { mutableStateOf(jugador.rol) }
    var hayError by rememberSaveable { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Editar jugador") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    label = { Text("Nickname") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = rol,
                    onValueChange = { rol = it },
                    label = { Text("Rol") },
                    singleLine = true
                )
                if (hayError) {
                    Text(
                        text = "Ningún campo puede quedar vacío",
                        color = Color(0xFFF87171),
                        fontSize = 13.sp,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (nombre.isBlank() || nickname.isBlank() || rol.isBlank()) {
                    hayError = true
                } else {
                    onGuardar(
                        jugador.copy(
                            nombre = nombre.trim(),
                            nickname = nickname.trim(),
                            rol = rol.trim()
                        )
                    )
                }
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}
@Composable
fun TarjetaJugador(jugador: Jugador,
                   onEditar: () -> Unit,
                   onEliminar: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ){
        Image(
            painter = painterResource(id = jugador.imagen),
            contentDescription = "Foto de ${jugador.nickname}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(74.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier
            .padding(start = 18.dp)
        ){
            Text(text = jugador.nickname,
                color = TextoClaro,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp)
            Text(text = "${jugador.nombre} · ${jugador.rol}",
                color = TextoSuave,
                fontSize = 14.sp)
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Button(onClick = { onEditar( ) }) {
                    Text("Editar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { onEliminar() }) {
                    Text("Eliminar")
                }
            }
        }
    }
}
@Composable
fun SeparadorJugadores(){
    HorizontalDivider(thickness = 1.dp, color = VerdeNeon.copy(alpha = 0.5f),modifier = Modifier.padding(vertical = 4.dp))

}
@Composable
fun coloresCampo() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = TextoClaro,
    unfocusedTextColor = TextoClaro,
    focusedBorderColor = VerdeNeon,
    unfocusedBorderColor = TextoSuave,
    focusedLabelColor = VerdeNeon,
    unfocusedLabelColor = TextoSuave,
    cursorColor = VerdeNeon
)
