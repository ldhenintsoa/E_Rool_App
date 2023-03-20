package mg.pearl.e_rool.sceen
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mg.pearl.e_rool.R
import mg.pearl.e_rool.data.apropos
import mg.pearl.e_rool.ui.theme.AppTheme
import java.util.*

@Composable
fun CreerCompteScreen2(
    modifier: Modifier = Modifier,
    onContinuButtonClicked: () -> Unit = {},
    onBackButtonCliked:()->Unit={}

){
    val mContext = LocalContext.current
    val nomComplet = remember { mutableStateOf("") }
    val annee = remember { mutableStateOf("") }
    val mois = remember { mutableStateOf("") }
    val jours = remember { mutableStateOf("") }
    val pays= remember { mutableStateOf("") }

    val buttonBackgroundColor:Color= colorResource(id = R.color.themeColor)
    val colorMaleButton=remember{ mutableStateOf(Color.Transparent) }
    val colorFemeleButton=remember{ mutableStateOf(Color.Transparent) }
    val colorMaleText=remember{ mutableStateOf(buttonBackgroundColor) }
    val colorFemeleText=remember{ mutableStateOf(buttonBackgroundColor) }

    val nomcomplet=stringResource(id = R.string.nomComptlet)
    val namePays=stringResource(id = R.string.pays)

    //Choix du sexe
    val genre = if (colorMaleButton.value==buttonBackgroundColor)
        "Male"
    else if (colorFemeleButton.value==buttonBackgroundColor)
        "Femele"
    else
        "None"

    //color de buton enable et disable
    val textButtonColor=
        if (nomComplet.value.isNotEmpty() && annee.value.isNotEmpty() && mois.value.isNotEmpty() && jours.value.isNotEmpty() && pays.value.isNotEmpty() && genre!="None")
            Color.White
        else
            colorResource(id = R.color.textColorEnabledButton)

    //date
    val year:Int
    val month:Int
    val day: Int

    val calandar= Calendar.getInstance()
    year=calandar.get(Calendar.YEAR)
    month=calandar.get((Calendar.MONTH))
    day=calandar.get(Calendar.DAY_OF_MONTH)
    calandar.time=Date()
    val j=remember{ mutableStateOf("") }
    val m=remember{ mutableStateOf("") }
    val a=remember{ mutableStateOf("") }
    val datePickerDialog=DatePickerDialog(
        mContext,
        {_:DatePicker,year:Int,month:Int,dayOfMonth:Int ->
            a.value="$year"
            m.value="${month+1}"
            j.value= "$dayOfMonth"
        },year,month,day

    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {   mois.value = m.value
        jours.value = j.value
        annee.value = a.value
        Row{
            IconButton(onClick = {onBackButtonCliked()}, modifier = Modifier.padding(AppTheme.dimens.mediumLarge,5.dp))
            {
                Icon(imageVector = Icons.Filled.ArrowBack, "Retour",modifier=Modifier.size(25.dp))
            }
            Text(text ="2/3", color = colorResource(id = R.color.themeColor), style=MaterialTheme.typography.h6,modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 15.dp), textAlign = TextAlign.End)
        }
        //Adresse Email
        Column {
            Text(text = nomcomplet, color = colorResource(id = R.color.gris), modifier = Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            OutlinedTextField(
                value = nomComplet.value,
                onValueChange = { nomComplet.value = it },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                colors=TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource( id = R.color.themeColor),
                    unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.mediumLarge, 5.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.mediumLarge, 0.dp) ) {
            Text(text = stringResource(id = R.string.date_anniversaie),color= colorResource(id = R.color.gris),modifier=Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(5.dp))
            Row{
                OutlinedTextField(
                    value = jours.value,
                    onValueChange = {jours.value = it },
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                    trailingIcon=
                    {
                        IconButton(onClick = {datePickerDialog.show()})
                        {
                            Icon(imageVector = Icons.Filled.ArrowDropDown, null,  modifier=Modifier.size(25.dp), tint= colorResource(id = R.color.gris))
                        }
                    },
                    colors=TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource( id = R.color.themeColor),
                        unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                    modifier = Modifier
                        .width(90.dp)

                )
                Spacer(modifier = Modifier.width(15.dp))
                OutlinedTextField(
                    value = mois.value,
                    onValueChange = { mois.value = it },
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                    trailingIcon=
                    {
                        IconButton(onClick = {datePickerDialog.show()})
                        {
                            Icon(imageVector = Icons.Filled.ArrowDropDown, null,  modifier=Modifier.size(25.dp), tint= colorResource(id = R.color.gris))
                        }
                    },
                    colors=TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource( id = R.color.themeColor),
                        unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                    modifier = Modifier.width(90.dp) )
                Spacer(modifier = Modifier.width(15.dp))
                OutlinedTextField(
                    value = annee.value,
                    onValueChange = { annee.value = it },
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                    trailingIcon=
                    {
                        IconButton(onClick = {datePickerDialog.show()})
                        {
                            Icon(imageVector = Icons.Filled.ArrowDropDown, null,  modifier=Modifier.size(25.dp), tint= colorResource(id = R.color.gris))
                        }
                    },
                    colors=TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource( id = R.color.themeColor),
                        unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                    modifier = Modifier
                        .width(120.dp)
                    )

            }

        }
        //Pays
        Column {
            Text(text =namePays, color = colorResource(id = R.color.gris),modifier=Modifier.padding(AppTheme.dimens.mediumLarge,0.dp))
            OutlinedTextField(
                value = pays.value,
                onValueChange = { pays.value = it },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                colors=TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource( id = R.color.themeColor),
                    unfocusedBorderColor = colorResource(id = R.color.themeColor)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.mediumLarge, 5.dp)
            )
        }
        Row {
            //buttonMale
            OutlinedButton(
                    onClick = {
                                    colorMaleButton.value= buttonBackgroundColor
                                    colorMaleText.value= Color.White
                                    colorFemeleButton.value= Color.Transparent
                                    colorFemeleText.value=buttonBackgroundColor
                              },
                    border= BorderStroke(1.dp, colorResource( id = R.color.themeColor)),
                    shape= RoundedCornerShape(10.dp),
                    colors=ButtonDefaults.buttonColors(backgroundColor =  colorMaleButton.value),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                ) {

                Text( stringResource(id = R.string.male),fontSize = 25.sp,color=colorMaleText.value)
            }
            Spacer(modifier = Modifier.width(20.dp))
            //Buton femele
            OutlinedButton(
                    onClick = {
                            colorFemeleButton.value= buttonBackgroundColor
                            colorFemeleText.value= Color.White
                            colorMaleButton.value= Color.Transparent
                            colorMaleText.value= buttonBackgroundColor
                             },
                    border= BorderStroke(1.dp, buttonBackgroundColor),
                    shape= RoundedCornerShape(10.dp),
                    colors=ButtonDefaults.buttonColors(backgroundColor = colorFemeleButton.value),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                ) {
                    Text( stringResource(id = R.string.femele),fontSize = 25.sp,color=colorFemeleText.value)
                }



        }
        Button(onClick = {
            apropos.nomComplet=nomComplet.value
            apropos.dateDeNaissance="${jours.value}/${mois.value}/${annee.value}"
            apropos.pays=pays.value
            apropos.sexe=genre
            onContinuButtonClicked()
        },
            enabled=nomComplet.value.isNotEmpty() && annee.value.isNotEmpty() && mois.value.isNotEmpty() && jours.value.isNotEmpty() && pays.value.isNotEmpty() && genre!="None",
            colors =ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.themeColor),
                disabledBackgroundColor = colorResource( id = R.color.backgroundEnabledColor)
            ),
            shape= RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
            modifier = Modifier
                .padding(AppTheme.dimens.mediumLarge)
                .fillMaxWidth()
                .height(50.dp)

        ) {
            Text(stringResource(id = R.string.continuer), color = textButtonColor, style = MaterialTheme.typography.h5)
        }

        }

}

