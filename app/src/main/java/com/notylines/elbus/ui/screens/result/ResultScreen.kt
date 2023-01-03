package com.notylines.elbus.ui.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
                    .fillMaxWidth()
                    .weight(0.67f)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp, top = 16.dp, bottom = 8.dp)
                    .weight(1f)

            ) {
//                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                        Text(text = "Duracion", style = MaterialTheme.typography.caption)
                        Text(
                            text = "00:00:00",
                            modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 5.dp)
                        )
                    }
                }
                item { Divider(modifier = Modifier.padding(vertical = 4.dp)) }
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                        Text(text = "Velocidad Maxima", style = MaterialTheme.typography.caption)
                        Text(
                            text = "0 Km/H",
                            modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 5.dp)
                        )
                    }

                }
                item { Divider(modifier = Modifier.padding(vertical = 4.dp)) }

                item {
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

                }

                item { Divider(modifier = Modifier.padding(vertical = 4.dp)) }

                item {
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

                }

                item { Divider(modifier = Modifier.padding(vertical = 4.dp)) }

                item {
                    Text(
                        text = "Reiteraciones de exceso de velocidad",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.caption,
                    )

                }

                items(excesosDeVelocidad) { exceso ->
                    Text(
                        text = exceso,
                        modifier = Modifier.padding(end = 10.dp, start = 26.dp, top = 3.dp)
                    )
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.12f)
                    .shadow(elevation = 20.dp)
                    .background(color = Color.White),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Button(onClick = { /*TODO*/ }, modifier = Modifier.width(130.dp)) {
                    Text(text = "Continuar")
                }
                Spacer(modifier = Modifier.width(30.dp))
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.width(130.dp)) {
                    Text(text = "Borrar")
                }
            }
        }
    }

}