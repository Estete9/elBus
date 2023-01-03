package com.notylines.elbus.ui.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notylines.elbus.R

@Composable
fun ResultScreen(navController: NavController) {

// TODO   this is a placeholder list
    val excesosDeVelocidad = listOf<String>(
        "speed 1",
        "speed 2",
        "speed 3",
        "speed 4",
        "speed 5",
        "speed 6",
        "speed 7",
        "speed 8",
        "speed 9",
        "speed 10",
        "speed 11",
        "speed 12",
        "speed 13",
        "speed 14"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) {


        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.cropped_map),
                contentDescription = "Result Map",
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
// here is lazyColumn instead of column
            Column(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(2f),
                verticalArrangement = Arrangement.spacedBy(8.dp),

                ) {

                Spacer(modifier = Modifier.height(4.dp))

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(text = "Duracion", style = MaterialTheme.typography.caption)
                    Text(
                        text = "00:00:00",
                        modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 5.dp)
                    )
                }

                Divider()

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(text = "Velocidad Maxima", style = MaterialTheme.typography.caption)
                    Text(
                        text = "0 Km/H",
                        modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 5.dp)
                    )
                }

                Divider()

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(
                        text = "Duracion de intervalo en Velocidad Maxima",
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = "00:00:00",
                        modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 5.dp)
                    )
                }

                Divider()

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(
                        text = "Numero de Excesos de Velocidad (mas de 5s)",
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = "17",
                        modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 5.dp)
                    )
                }

                Divider()

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(
                        text = "Lista de veces que hubo exceso de velocidad",
                        style = MaterialTheme.typography.caption
                    )
// here column instead of lazyColumn
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        items(excesosDeVelocidad) { exceso ->
                            Text(text = exceso)
                        }
                    }

                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,

                ) {

                Button(onClick = { /*TODO*/ }, modifier = Modifier.width(130.dp)) {
                    Text(text = "Guardar")
                }
                Spacer(modifier = Modifier.width(30.dp))
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.width(130.dp)) {
                    Text(text = "Borrar")
                }
            }
            Spacer(modifier = Modifier.width(30.dp))

        }
    }

}