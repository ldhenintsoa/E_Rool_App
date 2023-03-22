package mg.pearl.e_rool.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import mg.pearl.e_rool.R
import mg.pearl.e_rool.data.*


@Composable
fun Information(platList: List<Conducteur>){
    /*
    val navController = rememberNavController()
    Scaffold(
        //modifier = Modifier.background(color = Color.Black),
        /*topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text("test view") }
            )
        }*/
            bottomBar = { BottomBar(navController = navController) },

    ) {
        Modifier.padding(it)
        BottomNavGraph(
            navController = navController
        )

*/
        LazyColumn(
            Modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 60.dp) ,
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 25.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if(typeUtilisateur.ifClientClicked) {
                        Text(
                            "\uD83C\uDF3F  Liste de ligne",
                            style = MaterialTheme.typography.h5,
                            color = colorResource(id = R.color.themeColor))
                    }
                    if (typeUtilisateur.ifConducteurClicked) {
                        Text(
                            "\uD83C\uDF3F  Liste de client",
                            style = MaterialTheme.typography.h5,
                            color = colorResource(id = R.color.themeColor))}
                }
            }
            items(platList){ conducteur ->
                ConducteurCard(conducteur.name, conducteur.description, conducteur.imageRes)
            }
        }
    }
