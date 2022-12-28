package com.notylines.elbus.ui.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                    .weight(1.2f)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(2f),
                verticalArrangement = Arrangement.spacedBy(12.dp),

                ) {

                Spacer(modifier = Modifier.height(12.dp))

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(text = "Duracion")
                    Text(text = "00:00:00")
                }

                Divider()

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(text = "Velocidad Maxima")
                    Text(text = "0 Km/H")
                }

                Divider()

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(text = "Duracion de intervalo en Velocidad Maxima")
                    Text(text = "00:00:00")
                }

                Divider()

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(text = "Numero de Excesos de Velocidad (mas de 5s)")
                    Text(text = "17")
                }

                Divider()
                Box() {

                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                        Text(text = "Lista de veces que hubo exceso de velocidad")

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
                    Box(
                        modifier = Modifier.fillMaxSize().padding(12.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Continuar")
                        }
                    }
                }


            }

        }
    }

}