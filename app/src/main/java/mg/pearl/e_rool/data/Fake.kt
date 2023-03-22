package mg.pearl.e_rool.data

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import mg.pearl.e_rool.R


@Composable
fun ConducteurCard(name: String, description: String, image: Int) {
    Card(
        modifier = Modifier
            // The space between each card and the other
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier.size(140.dp).fillMaxWidth()
                        .padding(8.dp).clip(RoundedCornerShape(50.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black),
                    contentScale = ContentScale.Fit,

                    )


            Column(Modifier.padding(8.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

