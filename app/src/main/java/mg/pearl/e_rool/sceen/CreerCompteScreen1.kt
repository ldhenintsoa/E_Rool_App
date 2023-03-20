
package mg.pearl.e_rool.sceen
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mg.pearl.e_rool.R
import mg.pearl.e_rool.data.getDataAllUserFromFireBase
import mg.pearl.e_rool.data.listeUtilisateur
import mg.pearl.e_rool.data.login
import mg.pearl.e_rool.ui.theme.AppTheme

@Composable
fun CreerCompteScreen1(
    modifier: Modifier = Modifier,
    onContinuButtonClicked: () -> Unit = {},
    onSeconnectonButtonClicked:()-> Unit ={},
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        val mContext = LocalContext.current

        val mEmail = remember { mutableStateOf("") }
        val mPassword = remember { mutableStateOf("") }
        val mConfirmePassword = remember { mutableStateOf("") }

        val email=stringResource(id = R.string.email_adresse)
        val motdepasse=stringResource(id = R.string.mot_de_passe)
        val repetemotdepasse=stringResource(id = R.string.repete_mot_de_passe)

        val isPasswordVisible1 = remember{ mutableStateOf(false) }
        val isPasswordVisible2 = remember{ mutableStateOf(false) }

        val textButtonColor=
            if (mEmail.value.isNotEmpty() && mPassword.value.isNotEmpty() && mConfirmePassword.value.isNotEmpty())
                 Color.White
            else
                 colorResource(id = R.color.textColorEnabledButton)
        Row{
            Text(text = "Création d'un compte", color = colorResource(id = R.color.black), style =MaterialTheme.typography.h3 , textAlign = TextAlign.Center,modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            Text(text ="1/3", color = colorResource(id = R.color.themeColor),style=MaterialTheme.typography.h6, modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.mediumLarge, 7.dp), textAlign = TextAlign.End)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            ExtendedFloatingActionButton(
                text = { Text("FACEBOOK", color = colorResource(id = R.color.textfb),fontSize = 18.sp,style = MaterialTheme.typography.h5) },
                shape = RoundedCornerShape(9.dp),
                onClick = { /*TODO*/ },
                backgroundColor = Color.Transparent,
                contentColor = colors.primary,
                icon ={ Image(painter = painterResource(id = R.drawable.facebook) , null, modifier = Modifier.size(25.dp,25.dp) )},
                elevation=FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                modifier = Modifier.width(160.dp)
            )
            ExtendedFloatingActionButton(
                text = { Text("GOOGLE", color = colorResource(id = R.color.textgoogle),fontSize = 18.sp,style = MaterialTheme.typography.h5) },
                shape = RoundedCornerShape(9.dp),
                onClick = { /*TODO*/ },
                backgroundColor = Color.Transparent,
                icon ={ Image(painter = painterResource(id = R.drawable.google), null, modifier = Modifier.size(25.dp,25.dp))},
                elevation=FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                modifier = Modifier.width(160.dp))
        }
        //Adresse Email
        Column{
            Text(text = email, color = colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            OutlinedTextField(
                value = mEmail.value,
                onValueChange = { mEmail.value = it },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                colors=TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource( id = R.color.themeColor),
                    unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.mediumLarge, 5.dp)

            )
        }
        //Mot de passe
        Column {
            Text(text = motdepasse, color = colorResource(id = R.color.gris), modifier = Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            OutlinedTextField(
                value = mPassword.value,
                onValueChange = { mPassword.value = it },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                trailingIcon = {
                    IconButton( onClick = { isPasswordVisible1.value = !isPasswordVisible1.value })
                    {
                        Icon(
                            painter =
                            if (isPasswordVisible1.value)
                                painterResource(id = R.drawable.eye)
                            else
                                painterResource(id = R.drawable.eyehide),
                            contentDescription = "Password Visibility",
                            modifier=Modifier.size(25.dp),
                            tint= colorResource(id = R.color.gris)
                        )

                    }},
                visualTransformation = if(isPasswordVisible1.value) VisualTransformation.None else PasswordVisualTransformation(),
                colors=TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource( id = R.color.themeColor),
                    unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.mediumLarge, 5.dp)
            )
        }
        //Repeté le mot de passe
        Column {
            Text(text = repetemotdepasse, color = colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = mConfirmePassword.value,
                onValueChange = { mConfirmePassword.value = it },
                enabled= mPassword.value.length>=8,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                trailingIcon = {
                    IconButton( onClick = { isPasswordVisible2.value = !isPasswordVisible2.value })
                    {
                        Icon(
                            painter =
                            if (isPasswordVisible2.value)
                                painterResource(id = R.drawable.eye)
                            else
                                painterResource(id = R.drawable.eyehide),
                            contentDescription = "Password Visibility",
                            modifier=Modifier.size(25.dp),
                            tint= colorResource(id = R.color.gris)
                        )

                    }},
                visualTransformation = if(isPasswordVisible2.value) VisualTransformation.None else PasswordVisualTransformation(),
                colors=TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource( id = R.color.themeColor),
                    unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.mediumLarge, 5.dp)
            )
        Row {
            Text(text = stringResource(id = R.string.conseil_mot_de_passe),color= colorResource(id = R.color.gris), modifier = Modifier.padding(AppTheme.dimens.mediumLarge,13.dp),fontSize = 15.sp)
            Spacer(modifier = Modifier.width(20.dp))
            TextButton(onClick = { /*TODO*/ }) {
                Text(stringResource(id = R.string.genere_mot_de_passe),color= colorResource(id = R.color.gris),fontSize = 15.sp)
            }
        }
        Button(
            onClick =
            {
                var isExiste=false
                getDataAllUserFromFireBase(listeUtilisateur)
                for (user in listeUtilisateur){

                    if (mEmail.value==user.adresseEmail){
                        if(mPassword.value==user.motDePasse)
                        {
                            Toast.makeText(mContext, "Compte déjà existant !!!", Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            Toast.makeText(mContext, "Impossible de créer ce compte!Adresse email déjà utilisée", Toast.LENGTH_SHORT).show()
                        }
                        isExiste=true
                        break
                    }
                    else{
                        isExiste=false
                    }
                }
                if (!isExiste){
                    login.adresseEmail=mEmail.value
                    login.motDePasse=mPassword.value
                    onContinuButtonClicked()
                }


            },
            enabled= mEmail.value.isNotEmpty() && mPassword.value.isNotEmpty() && mConfirmePassword.value.isNotEmpty() && mPassword.value==mConfirmePassword.value
            ,
            colors =ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.themeColor),
                disabledBackgroundColor = colorResource(id = R.color.backgroundEnabledColor)
            ),
            shape= RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
            modifier = Modifier
                .padding(AppTheme.dimens.mediumLarge, 0.dp)
                .fillMaxWidth()
                .height(50.dp)

        ) {
            Text(stringResource(id = R.string.continuer), color =textButtonColor,  style = MaterialTheme.typography.h5)
        }
        }
        Row {

            Text(text = stringResource(id = R.string.question),color= colorResource(id = R.color.gris),fontSize = 15.sp,modifier = Modifier.padding(0.dp,15.dp))
            TextButton(onClick = { onSeconnectonButtonClicked()}) {
                Text(stringResource(id = R.string.petit_se_connecter),color= colorResource(id = R.color.black),fontSize = 16.sp)
            }
        }
}
}
