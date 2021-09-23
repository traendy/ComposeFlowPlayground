package de.traendy.composeflowplayground.ui.theme

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExpanseInput(addExpanse: (String, Float) -> Unit) {
    var name by remember { mutableStateOf("Name") }
    var amount by remember {
        mutableStateOf(0.0f)
    }

    Column {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = {
            Text("Name")
        })
        Row {
            OutlinedTextField(
                value = amount.toString(),
                onValueChange = { amount = it.toFloat() },
                label = {
                    Text("Amount")
                })
            ColorChangingButton(onClick = { addExpanse(name, amount) }) {
                Text(text = "Add")
            }
        }
    }
}

@Composable
fun ColorChangingButton(onClick: () -> Unit, modifier: Modifier = Modifier, animOffset: Int = 0, content: @Composable RowScope.() -> Unit){
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Red,
        targetValue = Green,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, delayMillis = animOffset, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Button(onClick = { onClick() }, modifier = modifier, colors = buttonColors(backgroundColor = color)) {
        content()

    }
}

private enum class State{
    Start,
    End
}




@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun Example(){
    var selected by remember { mutableStateOf(false) }
// Animates changes when `selected` is changed.
    val transition = updateTransition(selected)
    val borderColor by transition.animateColor { isSelected ->
        if (isSelected) Color.Magenta else Color.White
    }
    val elevation by transition.animateDp { isSelected ->
        if (isSelected) 10.dp else 2.dp
    }
    Surface(
        onClick = { selected = !selected },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, borderColor),
        elevation = elevation
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(text = "Hello, world!")
            // AnimatedVisibility as a part of the transition.
            transition.AnimatedVisibility(
                visible = { targetSelected -> targetSelected },
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Text(text = "It is fine today.")
            }
            // AnimatedContent as a part of the transition.
            transition.AnimatedContent { targetState ->
                if (targetState) {
                    Text(text = "Selected")
                } else {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
                }
            }
        }
    }

}