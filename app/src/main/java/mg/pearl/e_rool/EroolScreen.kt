package mg.pearl.e_rool
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mg.pearl.e_rool.sceen.*
import mg.pearl.e_rool.ui.theme.AppTheme
import mg.pearl.e_rool.ui.theme.Orientation
enum class EroolScreen{
    Bienvenue,
    CreerCompte1,
    CreerCompte2,
    CreerCompte3,
    Connexion,
    Accueil
}


@Composable
fun E_roolApp(modifier: Modifier = Modifier){
    val navController= rememberNavController()
    Scaffold(modifier=modifier) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = EroolScreen.Bienvenue.name,
            modifier= modifier
                .padding(innerPadding)
                .fillMaxSize()
            )
        {
            composable(EroolScreen.Bienvenue.name)
            {
                if (AppTheme.orientation == Orientation.Portrait){
                    //Appel l'ecran de bienvenu
                    BienvenueScreenPortrait(
                        //Naviguer vers l'ecran de connexion
                        onConnexionButtonClicked =
                        {
                            navController.navigate(EroolScreen.Connexion.name)
                        },
                        //Naviguer vers l'ecran de création d'un compte
                        onCreateCompteButtonClicked =
                        {
                            navController.navigate(EroolScreen.CreerCompte1.name)
                        },
                        modifier = Modifier .fillMaxSize()
                    )
                }
                else{
                    BienvenueScreenLandScape(
                        //Naviguer vers l'ecran de connexion
                        onConnexionButtonClicked =
                        {
                            navController.navigate(EroolScreen.Connexion.name)
                        },
                        //Naviguer vers l'ecran de création d'un compte
                        onCreateCompteButtonClicked =
                        {
                            navController.navigate(EroolScreen.CreerCompte1.name)
                        },
                        modifier = Modifier .fillMaxSize()

                    )
                }

            }

            composable(route = EroolScreen.Connexion.name)
            {
                if (AppTheme.orientation == Orientation.Portrait){
                    Connexion(
                        onConnexionButtonClicked = {navController.navigate(EroolScreen.Accueil.name)},
                        onMotdepasseOublierClicked = {},
                        modifier = Modifier.fillMaxSize())
                }
                else{
                    Column(modifier=Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
                        Connexion(
                            onConnexionButtonClicked = {},
                            onMotdepasseOublierClicked = {},
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(380.dp)
                                .verticalScroll(rememberScrollState())
                        )
                    }

                }
            }

            //Création d'un compte etape1
            composable(route = EroolScreen.CreerCompte1.name)
            {
                if (AppTheme.orientation == Orientation.Portrait) {
                    CreerCompteScreen1(
                        onContinuButtonClicked = { navController.navigate(EroolScreen.CreerCompte2.name) },
                        onSeconnectonButtonClicked = { navController.navigate(EroolScreen.Connexion.name) },
                        modifier = Modifier.fillMaxSize()
                    )
                }else{
                    Column(modifier=Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
                        CreerCompteScreen1(
                            onContinuButtonClicked = { navController.navigate(EroolScreen.CreerCompte2.name) },
                            onSeconnectonButtonClicked = { navController.navigate(EroolScreen.Connexion.name) },
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(380.dp)
                                .verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
            //Création d'un compte etape2
            composable(route = EroolScreen.CreerCompte2.name)
            {
                if (AppTheme.orientation == Orientation.Portrait) {
                    CreerCompteScreen2(
                        modifier = Modifier.fillMaxSize(),
                        onContinuButtonClicked = { navController.navigate(EroolScreen.CreerCompte3.name) },
                        onBackButtonCliked = { navController.navigateUp() }
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        CreerCompteScreen2(
                            onContinuButtonClicked = { navController.navigate(EroolScreen.CreerCompte3.name) },
                            onBackButtonCliked = { navController.navigateUp() },
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(380.dp)
                                .verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
            //Création d'un compte etape3
            composable(route = EroolScreen.CreerCompte3.name)
            {

                if (AppTheme.orientation == Orientation.Portrait) {
                    CreerCompteScreen3(
                        onContinuButtonClicked = {navController.navigate(EroolScreen.Accueil.name)},
                        onBackButtonCliked = {navController.navigateUp()},
                        modifier = Modifier.fillMaxSize()
                    )
                }
                else
                {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        CreerCompteScreen3(
                            onContinuButtonClicked = {navController.navigate(EroolScreen.Accueil.name)},
                            onBackButtonCliked = {navController.navigateUp()},
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(380.dp)
                                .verticalScroll(rememberScrollState())
                        )

                    }

                }
            }

            composable(route = EroolScreen.Accueil.name)
            {
                Accueil(modifier=Modifier.fillMaxSize())

            }
        }
    }
}

