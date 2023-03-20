package mg.pearl.e_rool.sceen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import mg.pearl.e_rool.data.*

@Composable
fun Accueil(modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val mContext = LocalContext.current
        val message=remember { mutableStateOf("") }


        Text(text =message.value)
        Button(onClick = {
            getDataUserCurrentFromFireBase(mContext, utilisateurCurrent)
            message.value = """
            Votre adresse Email est: ${utilisateurCurrent.adresseEmail}
            Votre mot de passe est:  ${utilisateurCurrent.motDePasse}
            Vous adresse est:         ${utilisateurCurrent.adresse}
            votre genre est :        ${utilisateurCurrent.sexe}                                         
            """.trimMargin()
        }) {
            Text(text ="Get Data User")
        }
        Button(onClick = {
            var chaine=""""""
            getDataAllUserFromFireBase(listeAllUser = listeUtilisateur)
            for (user in listeUtilisateur){
                chaine+= """
                |Nom: ${user.nomUtilisateur}
                |Adresse Email : ${user.adresseEmail}
                |***************
                |
                """.trimMargin()
            }
            message.value =chaine

        }) {
            Text(text ="Get Data All User")
        }
    }

}