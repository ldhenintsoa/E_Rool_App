package mg.pearl.e_rool.sceen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mg.pearl.e_rool.R
import mg.pearl.e_rool.ui.theme.AppTheme
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import mg.pearl.e_rool.data.*

@Composable
fun CreerCompteScreen3(
    modifier: Modifier = Modifier,
    onContinuButtonClicked: () -> Unit = {},
    onBackButtonCliked:()->Unit={}

){

    val mNomUtilisateur= remember { mutableStateOf("") }
    val mAdresse= remember { mutableStateOf("") }
    val mTelephone= remember { mutableStateOf("") }
    val mBioConducteur= remember { mutableStateOf("") }


    val nomUtilsateur= stringResource(id = R.string.nom_Utilisateur)
    val nomAdresse= stringResource(id = R.string.adresse)
    val nomTelephone= stringResource(id = R.string.telephone)
    val nomBioConducteur= stringResource(id = R.string.bioconducteur)

    //color de buton enable et disable
    val textButtonColor=
        if (mNomUtilisateur.value.isNotEmpty() && mAdresse.value.isNotEmpty() && mTelephone.value.isNotEmpty())
            Color.White
        else
            colorResource(id = R.color.textColorEnabledButton)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        val mContext = LocalContext.current
        Row( modifier= Modifier.padding(0.dp, 16.dp))
        {
            IconButton(onClick = {onBackButtonCliked()}, modifier = Modifier.padding(0.dp,0.dp))
            {
                Icon(imageVector = Icons.Filled.ArrowBack, null)
            }
            Text(text = "Compléter votre profile", color = colorResource(id = R.color.black), style = MaterialTheme.typography.h3,fontSize = 25.sp,modifier=Modifier.padding(
                AppTheme.dimens.mediumLarge,5.dp))
            Text(text ="3/3", color = colorResource(id = R.color.themeColor),style= MaterialTheme.typography.h6, modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp, 9.dp),textAlign = TextAlign.End)
        }
        ImagePickerView(
            modifier=Modifier.align(Alignment.CenterHorizontally),
            lastSelectedImage = pickedImage.value,
            onSelection = { pickedImage.value = it }
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = nomUtilsateur,color= colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,18.dp))
            OutlinedTextField(
                value =mNomUtilisateur.value ,
                onValueChange = {mNomUtilisateur.value=it},
                colors= TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent),
                modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = nomAdresse,color= colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,18.dp))
            TextField(
                value =mAdresse.value ,
                onValueChange = {mAdresse.value=it},
                colors=TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Transparent,unfocusedBorderColor = Color.Transparent),
                modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = nomTelephone,color= colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,18.dp))
            TextField(
                value =mTelephone.value ,
                onValueChange = {mTelephone.value=it},
                colors=TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Transparent,unfocusedBorderColor = Color.Transparent),
                modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text =nomBioConducteur,color= colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,18.dp))
            TextField(
                value =mBioConducteur.value ,
                onValueChange = {mBioConducteur.value=it},
                colors=TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent),
                modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
        }
        Button(onClick =
        {
            infoPlus.nomUtilisateur=mNomUtilisateur.value
            infoPlus.adresse=mAdresse.value
            infoPlus.numeroTelephone=mTelephone.value
            infoPlus.bioConducteur=mBioConducteur.value
            infoPlus.profil=0
            val db: FirebaseFirestore = FirebaseFirestore.getInstance()
            //Création d'un reference pour notre Firebase Firestore database.
            val dbUtilisateur: CollectionReference = db.collection("Utilisateur")
            //ajouter notre données dans l'objet utilisateur.
            val utilisateur= Utilisateur(
                login.adresseEmail,
                login.motDePasse,
                apropos.nomComplet,
                infoPlus.nomUtilisateur,
                apropos.dateDeNaissance,
                apropos.sexe,
                apropos.pays,
                infoPlus.adresse,
                infoPlus.numeroTelephone,
                infoPlus.bioConducteur,
                infoPlus.profil
            )

            //Methode pour ajouter l'utilisateur dans la base de données.
            dbUtilisateur.add(utilisateur).addOnSuccessListener {
                // Si l'enregistrement est un succès
                Toast.makeText(mContext,"Votre informations sont enregistrés dans la base de données",Toast.LENGTH_SHORT ).show()
                onContinuButtonClicked()

            }.addOnFailureListener { e ->
                // Si l'enregistrement des données est echoué.
                Toast.makeText(mContext, "Enregistrement échoué \n$e", Toast.LENGTH_SHORT).show()
            }

        },
            colors =ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.themeColor),disabledBackgroundColor = colorResource(id = R.color.backgroundEnabledColor)),
            shape= RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
            enabled=mNomUtilisateur.value.isNotEmpty() && mAdresse.value.isNotEmpty() && mTelephone.value.isNotEmpty(),
            modifier = Modifier
                .padding(AppTheme.dimens.mediumLarge)
                .fillMaxWidth()
                .height(58.dp)

        ) {
            Text(stringResource(id = R.string.creerProfil), color = textButtonColor, style = MaterialTheme.typography.h5)
        }

    }


}
var pickedImage= mutableStateOf<Uri?>(null)
@Composable
fun ImagePickerView(
    modifier: Modifier = Modifier,
    lastSelectedImage: Uri?,
    onSelection: (Uri?) -> Unit
) {
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()) {
        onSelection(it)
    }
    Image(
        modifier = modifier
            .size(140.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.LightGray)
            .border(1.dp, color = colorResource(id = R.color.themeColor), RoundedCornerShape(20.dp))
            .clickable {
                galleryLauncher.launch("image/*")
            },

        painter = rememberImagePainter(lastSelectedImage),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop
    )

}


