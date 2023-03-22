package mg.pearl.e_rool.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mg.pearl.e_rool.R
import mg.pearl.e_rool.ui.theme.AppTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import mg.pearl.e_rool.data.getDataUserCurrentFromFireBase
import mg.pearl.e_rool.data.utilisateurCurrent


@Composable
fun profile(
    modifier: Modifier = Modifier,
  ){

    val nomUtilsateur= stringResource(id = R.string.nom_Utilisateur)
    val nomAdresse= stringResource(id = R.string.adresse)
    val nomTelephone= stringResource(id = R.string.telephone)
    val nomBioConducteur= stringResource(id = R.string.bioconducteur)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    )
    {

        Row( modifier= Modifier.padding(0.dp, 16.dp))
        {

            Text(text = "votre profil", color = colorResource(id = R.color.black), style = MaterialTheme.typography.h3,fontSize = 25.sp,modifier=Modifier.padding(
                AppTheme.dimens.mediumLarge,5.dp))

        }
        Image(
            modifier = modifier
                .size(140.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray)
                .border(
                    1.dp,
                    color = colorResource(id = R.color.themeColor),
                    RoundedCornerShape(20.dp)
                )
                .padding(0.dp, 0.dp, 0.dp, 6.dp)
            ,

            painter = painterResource(id = R.drawable.usr),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop
        )
    Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = nomUtilsateur,color= colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            //Text(text = utilisateurCurrent.nomUtilisateur,color= Color.Black,modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp).fillMaxSize(), textAlign = Alignment.End)
            Text(text = utilisateurCurrent.nomUtilisateur,color= Color.Black,style=MaterialTheme.typography.h6, modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.mediumLarge, 7.dp), textAlign = TextAlign.End)

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = nomAdresse,color= colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
           // Text(text = utilisateurCurrent.adresse,color= Color.Black,modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp).fillMaxSize(), textAlign = Alignment.End)
            Text(text = utilisateurCurrent.adresse,color= Color.Black,style=MaterialTheme.typography.h6, modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.mediumLarge, 7.dp), textAlign = TextAlign.End)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = nomTelephone,color= colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            //Text(text =utilisateurCurrent.numeroTelephone,color= Color.Black,modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp).fillMaxSize(), textAlign = Alignment.End)
            Text(text =utilisateurCurrent.numeroTelephone,color= Color.Black,style=MaterialTheme.typography.h6, modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.mediumLarge, 7.dp), textAlign = TextAlign.End)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text =nomBioConducteur,color= colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
           // Text(text = utilisateurCurrent.bioConducteur,color= Color.Black,modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp).fillMaxSize(), textAlign = Alignment.End)
            Text(text = utilisateurCurrent.bioConducteur,color= Color.Black,style=MaterialTheme.typography.h6, modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.mediumLarge, 7.dp), textAlign = TextAlign.End)
        }


    }

}
@Composable
fun GetProfile() {
    val navController = rememberNavController()
    Scaffold(
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
        profile(modifier= Modifier
            .fillMaxSize()
            .padding(0.dp, 6.dp))
    }
}