package mg.pearl.e_rool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mg.pearl.e_rool.ui.theme.E_RoolTheme
import mg.pearl.e_rool.ui.theme.rememberWindowSizeClass

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val window = rememberWindowSizeClass()
            E_RoolTheme (window){
                    E_roolApp(modifier=Modifier.fillMaxSize())
            }
        }

    }
}



