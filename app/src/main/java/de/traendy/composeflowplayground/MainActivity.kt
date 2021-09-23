package de.traendy.composeflowplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import de.traendy.composeflowplayground.ui.theme.*
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFlowPlaygroundTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ComposeFlowPlaygroundApp()
                }
            }
        }
    }
}
