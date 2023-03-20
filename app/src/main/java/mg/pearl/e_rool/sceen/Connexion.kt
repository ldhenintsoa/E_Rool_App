package mg.pearl.e_rool.sceen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import mg.pearl.e_rool.R
import mg.pearl.e_rool.data.idUser
import mg.pearl.e_rool.ui.theme.AppTheme

@Composable
fun Connexion(
    modifier:Modifier=Modifier,
    onConnexionButtonClicked:()->Unit,
    onMotdepasseOublierClicked:()->Unit,
)
{
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val mContext = LocalContext.current
        val mAdresseEmail = remember { mutableStateOf("") }
        val mPassword = remember { mutableStateOf("") }
        val isPasswordVisible = remember{ mutableStateOf(false) }

        val textButtonColor=
            if (mAdresseEmail.value.isNotEmpty() && mPassword.value.isNotEmpty())
                Color.White
            else
                colorResource(id = R.color.textColorEnabledButton)
        Row(verticalAlignment = Alignment.CenterVertically) {
            ExtendedFloatingActionButton(
                text = { Text("FACEBOOK", color = colorResource(id = R.color.textfb),fontSize = 18.sp,style = MaterialTheme.typography.h5) },
                shape = RoundedCornerShape(9.dp),
                onClick = { /*TODO*/ },
                backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.colors.primary,
                icon ={ Image(painter = painterResource(id = R.drawable.facebook) , "Facebook", modifier = Modifier.size(25.dp,25.dp) ) },
                elevation=FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                modifier = Modifier
                    .width(160.dp)

            )

            ExtendedFloatingActionButton(
                text = { Text("GOOGLE", color = colorResource(id = R.color.textgoogle),fontSize = 18.sp,style = MaterialTheme.typography.h5) },
                shape = RoundedCornerShape(9.dp),
                onClick = { /*TODO*/ },
                backgroundColor = Color.Transparent,
                icon ={ Image(painter = painterResource(id = R.drawable.google), "Google", modifier = Modifier.size(25.dp,25.dp)) },
                elevation=FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                modifier = Modifier.width(160.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.login),
            contentDescription ="Image Connexion",
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = AppTheme.dimens.medium,
                    bottomStart = AppTheme.dimens.medium))
                .fillMaxWidth()
        )
        Column{
            Text(text = stringResource(id = R.string.email_adresse), color = colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            OutlinedTextField(
                value = mAdresseEmail.value,
                onValueChange = { mAdresseEmail.value = it },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                colors= TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource( id = R.color.themeColor),
                    unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.mediumLarge,5.dp)

            )
        }
        Column{
            Text(text = stringResource(id = R.string.mot_de_passe), color = colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            OutlinedTextField(
                value = mPassword.value,
                onValueChange = { mPassword.value = it },
                trailingIcon = {
                    IconButton( onClick = { isPasswordVisible.value = !isPasswordVisible.value })
                {
                    Icon(
                        painter =
                        if (isPasswordVisible.value)
                            painterResource(id = R.drawable.eye)
                        else
                            painterResource(id = R.drawable.eyehide),
                        contentDescription = "Password Visibility",
                        modifier= Modifier.size(25.dp),
                        tint= colorResource(id = R.color.gris)
                    )

                }},
                visualTransformation = if(isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                colors=TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource( id = R.color.themeColor),
                    unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.mediumLarge,5.dp)
            )
        }
        Row(modifier= Modifier.fillMaxWidth().padding(AppTheme.dimens.mediumLarge,0.dp), horizontalArrangement = Arrangement.End)
        {
            TextButton(onClick = {onMotdepasseOublierClicked()}) {
                Text( stringResource(id = R.string.motdepasseOublier),color= colorResource(id = R.color.gris), fontSize = 14.sp )
            }
        }

        Button(
            onClick = {
                var dataNoExiste=false
                val db = FirebaseFirestore.getInstance()
                db.collection("Utilisateur").get().addOnSuccessListener { utilisateur ->
                    for (document in utilisateur) {
                        if (mAdresseEmail.value== document.data["adresseEmail"] && mPassword.value== document.data["motDePasse"]){
                            idUser.idUserCurrent=document.id
                            onConnexionButtonClicked()
                            Toast.makeText(mContext ,"Authentification avec succès", Toast.LENGTH_SHORT ).show()
                            dataNoExiste=true
                            break
                        }
                        else if (mAdresseEmail.value== document.data["adresseEmail"] && mPassword.value!= document.data["motDePasse"]){
                            Toast.makeText(mContext,"Mot de passe incorrecte !", Toast.LENGTH_SHORT ).show()
                        }
                    }
                    if (!dataNoExiste){
                        Toast.makeText(mContext,"Ce compte n'existe pas", Toast.LENGTH_SHORT ).show()
                    }
                }
                    .addOnFailureListener { e ->
                        Toast.makeText(mContext, "Erreur de connexion \n$e", Toast.LENGTH_SHORT).show()
                    }

                    },
            enabled=mAdresseEmail.value.isNotEmpty() && mPassword.value.isNotEmpty(),
            shape= RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.themeColor),disabledBackgroundColor = colorResource(id = R.color.backgroundEnabledColor)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.mediumLarge)
                .height(58.dp)
        ) {
            Text(
                stringResource(id = R.string.se_connecter),
                color= textButtonColor,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

