package mg.pearl.e_rool.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mg.pearl.e_rool.R
import mg.pearl.e_rool.ui.theme.AppTheme

@Composable
fun BienvenueScreenPortrait(
    modifier: Modifier = Modifier,
    onConnexionButtonClicked:() -> Unit,
    onCreateCompteButtonClicked:() -> Unit,
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
         Image(
            painter = painterResource(R.drawable.picturewelcom),
            contentDescription = "Image de bienvenue",
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = AppTheme.dimens.medium,
                    bottomStart = AppTheme.dimens.medium))
                .fillMaxWidth()

            )

        Text(text = stringResource(R.string.simplifier_vos_course), style = MaterialTheme.typography.h4)
        Text(text = stringResource(R.string.textBienvenue),
            color = colorResource(id =R.color.gris ),
            textAlign = TextAlign.Center,
            modifier=Modifier.padding(AppTheme.dimens.mediumLarge)
        )
        Button(
            onClick = {onConnexionButtonClicked()},
            shape= RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.themeColor)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.mediumLarge)
                .height(58.dp)

        ) {
            Text(
                stringResource(id = R.string.se_connecter),
                color= colorResource(id = R.color.white),
                style = MaterialTheme.typography.h5
            )
        }
        TextButton(
            onClick = {

                onCreateCompteButtonClicked() }

        ) {
            Text(
                stringResource(id = R.string.creer_compte),
                color= colorResource(id = R.color.themeColor),
                style = MaterialTheme.typography.h5
                )
        }

    }
}
@Composable
fun BienvenueScreenLandScape(
    modifier: Modifier = Modifier,
    onConnexionButtonClicked:() -> Unit,
    onCreateCompteButtonClicked:() -> Unit,
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(R.drawable.picturewelcom),
            contentDescription = "Image de bienvenue",
            modifier = Modifier
                .clip(RoundedCornerShape(
                    bottomEnd = AppTheme.dimens.medium,
                    bottomStart = AppTheme.dimens.medium))
                .fillMaxHeight()
                .padding(AppTheme.dimens.mediumLarge),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(2f)
                .padding(AppTheme.dimens.mediumLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = stringResource(R.string.simplifier_vos_course),
                style = MaterialTheme.typography.h3)
            Text(text = stringResource(R.string.textBienvenue),
                style = MaterialTheme.typography.h6,
                color = colorResource(id = R.color.gris),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(AppTheme.dimens.mediumLarge)
            )
            Column( horizontalAlignment = Alignment.CenterHorizontally){

                Button(
                    onClick = { onConnexionButtonClicked() },
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.courbure)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.themeColor)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.dimens.mediumLarge)
                        .height(58.dp)

                ) {
                    Text(
                        stringResource(id = R.string.se_connecter),
                        color = colorResource(id = R.color.white),
                        style = MaterialTheme.typography.h5
                    )
                }
                TextButton(
                    onClick = {

                        onCreateCompteButtonClicked()
                    }

                ) {
                    Text(
                        stringResource(id = R.string.creer_compte),
                        color = colorResource(id = R.color.themeColor),
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }

    }
}
