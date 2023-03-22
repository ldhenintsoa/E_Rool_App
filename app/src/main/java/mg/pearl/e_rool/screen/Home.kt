package mg.pearl.e_rool.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.*
import mg.pearl.e_rool.R
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mg.pearl.e_rool.data.listDeClient
import mg.pearl.e_rool.data.listDeConducteur
import mg.pearl.e_rool.data.typeUtilisateur


@Composable
fun Home() {
    //HomeScreen()
   // Scaffold() {
        BottomNav()
    //}
}


//bouton conducteur
@Composable
fun WhiteButton(onWhiteButtonClicked:() -> Unit={},backgroundColor:Color,textColor:Color,iconColor: Color,borderColor:Color) {
    val screenw = (LocalContext.current.resources.displayMetrics.widthPixels.dp)/6
    val density = LocalDensity.current
    val t = with(density) {160.dp}
    Button(
        onClick = { /* action à exécuter lors du clic */ onWhiteButtonClicked()},
        border = BorderStroke(2.dp, borderColor),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        elevation=ButtonDefaults.elevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(7.dp)
            //.width(screenw + 4.dp)
            .width(t)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
                Icon(painter = painterResource(id = R.drawable.car), contentDescription = null, modifier = Modifier.size(30.dp,30.dp), tint = iconColor)

            // Partie droite du bouton (texte)
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Je suis",
                    fontSize = 12.sp,
                    color = textColor,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Conducteur",
                    fontSize = 15.sp,
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun GreenButton(onGreenButtonClicked:() -> Unit={},backgroundColor:Color,textColor:Color,iconColor: Color,borderColor: Color) {
    //val screenw = (LocalContext.current.resources.displayMetrics.widthPixels.dp)/6
    val density = LocalDensity.current
    //val screenw =

    val t = with(density) {160.dp}
    Button(
        onClick = { /* action à exécuter lors du clic */onGreenButtonClicked() },
        border = BorderStroke(2.dp, borderColor),
        colors = ButtonDefaults.buttonColors(backgroundColor =backgroundColor),
        elevation=ButtonDefaults.elevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(7.dp)
            //.width(screenw + 4.dp)
            .width(t)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Partie gauche du bouton (texte)
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Je suis",
                    fontSize = 12.sp,
                    color =textColor,
                    textAlign = TextAlign.Right
                )
                Text(
                    text = "Client",
                    fontSize = 15.sp,
                    color = textColor,
                    textAlign = TextAlign.Right
                )
            }
            // Partie droite du bouton (icône)
            Icon(painter = painterResource(id = R.drawable.customer), contentDescription = null, modifier = Modifier.size(30.dp,30.dp), tint = iconColor)
        }
    }
}

//alignement

@Composable
fun ButtonsRow() {
    Row(
        //horizontalArrangement = Arrangement.Center,
        //verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        val buttonBackgroundColor:Color= colorResource(id = R.color.themeColor)

        val colorGreenButton= remember{ mutableStateOf(Color.Transparent) }
        val colorWhiteButton= remember{ mutableStateOf(Color.Transparent) }

        val colorGreenText= remember{ mutableStateOf(buttonBackgroundColor) }
        val colorWhiteText= remember{ mutableStateOf(buttonBackgroundColor) }

        val colorBorder= remember{ mutableStateOf(buttonBackgroundColor) }
        WhiteButton(
            onWhiteButtonClicked =
             {
                 colorWhiteButton.value=buttonBackgroundColor
                 colorGreenButton.value=Color.White
                 colorWhiteText.value=Color.White
                 colorGreenText.value=buttonBackgroundColor

                 typeUtilisateur.ifConducteurClicked=true
                 typeUtilisateur.ifClientClicked=false
             },
            backgroundColor = colorWhiteButton.value,
            textColor =colorWhiteText.value ,
            iconColor = colorWhiteText.value,
            borderColor = colorBorder.value
        )
        GreenButton(
            onGreenButtonClicked=
            {
                colorGreenButton.value=buttonBackgroundColor
                colorWhiteButton.value=Color.White
                colorGreenText.value=Color.White
                colorWhiteText.value=buttonBackgroundColor

                typeUtilisateur.ifConducteurClicked=false
                typeUtilisateur.ifClientClicked=true

            },
            backgroundColor = colorGreenButton.value,
            textColor = colorGreenText.value,
            iconColor = colorGreenText.value,
            borderColor =colorBorder.value
        )
    }
}

@Composable
fun HomeScreen() {
    val screenh = (LocalContext.current.resources.displayMetrics.heightPixels.dp)/4
    Column(
        modifier = Modifier.fillMaxSize().padding(0.dp, 0.dp, 0.dp, 60.dp) ,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texte de bienvenue
        Row( verticalAlignment = Alignment.Bottom,
            ) {
            Text(
                text = "Bienvenue sur",
                fontSize = 24.sp,
                fontWeight = FontWeight.Thin,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "E-rooL!",
                color = colorResource(id = R.color.themeColor),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
        // Grande image PNG
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
               // .weight(1f)

        )
        // Rangée de boutons
        Column() {
            ButtonsRow()
            //NavigationBar()
            //BottomNav()

        }

    }
}
//Navigation bar
@Composable
fun NavigationBar() {
    val items = listOf(
        NavigationItem("Accueil", Icons.Filled.Home),
        NavigationItem(" Information", Icons.Filled.Info),
        NavigationItem("Recherche", Icons.Filled.Search)
    )
    BottomAppBar(
        backgroundColor = colorResource(id = R.color.themeColor),
        cutoutShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach { item ->
                    IconButton(
                        onClick = { /* TODO: naviguer vers la page correspondante */ },
                        content = {
                           // Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    //.fillMaxWidth()
                                    .padding(6.dp)
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                                Text(
                                    text = item.label,
                                    fontSize = 14.sp,
                                    color = Color.White
                                )
                            }
                        }
                    )
                }
            }
        }
    )
}

data class NavigationItem(val label: String, val icon: ImageVector)

//Test 2

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home: BottomBarScreen(
        route = "home",
        title = "Accueil",
        icon = R.drawable.baseline_home_24,
        icon_focused = R.drawable.baseline_home_24,
    )

    // for report
    object Report: BottomBarScreen(
        route = "report",
        title = "Information",
        icon = R.drawable.baseline_info_24,
        icon_focused = R.drawable.baseline_info_24
    )

    // for profile
    object Profile: BottomBarScreen(
        route = "profil",
        title = "Profile",
        icon = R.drawable.baseline_account_circle_24,
        icon_focused = R.drawable.baseline_account_circle_24
    )

}

//Navhost
@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            //
            HomeScreen()
        }
        composable(route = BottomBarScreen.Report.route) {
            //
            if(typeUtilisateur.ifClientClicked) {
                Information(listDeConducteur)
            }
            if (typeUtilisateur.ifConducteurClicked) {
                Information(listDeClient)}
        }
        composable(route = BottomBarScreen.Profile.route) {
            //
            profile()
        }
    }
}

//Add bottom nav
@Composable
fun BottomNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Modifier.padding(it)
        BottomNavGraph(
            navController = navController
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Report,
        BottomBarScreen.Profile
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                .background(color = Color.Transparent)
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        //if (selected) MaterialTheme.colors.primary.copy(alpha = 0.6f) else Color.Transparent
        if (selected) colorResource(id = R.color.themeColor) else Color.Transparent

    val contentColor =
        if (selected) Color.White else colorResource(id = R.color.themeColor)

    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon",
                tint = contentColor
            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    color = contentColor
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, )
fun HomePrev() {
    //BoutonCard()
   // HomeScreen()
    //ButtonsRow()
    //NavigationBar()
    //BottomNav()
}