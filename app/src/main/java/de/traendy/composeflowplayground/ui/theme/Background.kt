package de.traendy.composeflowplayground.ui.theme

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@ExperimentalAnimationApi
@Preview
@Composable
fun Background(){
    val elements = createBunch("♥", 400, 800, 30, 5000, 2000, 5)

        Box(Modifier.fillMaxSize()) {
            for (e in elements) {
                Element(
                    text = e.text,
                    xPos = e.xPos,
                    yEnd = e.yEnd,
                    size = e.size,
                    duration = e.duration,
                    delayMillis = e.delayMillis
                )
            }
    }
}

@Composable
fun Element(text:String, xPos:Int, yEnd:Int, size:Int, duration: Int, delayMillis: Int){
    val transition = rememberInfiniteTransition()
    val yPosition by transition.animateFloat(yEnd.toFloat(),0f, infiniteRepeatable(
        animation = tween(durationMillis = duration, delayMillis = delayMillis, easing = LinearEasing),
        repeatMode = RepeatMode.Restart
    ))
    val colorTransition by transition.animateColor(
        Color.Red, Transparent, infiniteRepeatable(
        animation = tween(durationMillis = duration, delayMillis = delayMillis, easing = LinearEasing),
        repeatMode = RepeatMode.Restart
    )
    )
    Text(text = text, color = colorTransition, modifier = Modifier.padding(top = yPosition.dp, start = xPos.dp), fontSize = size.sp)
}

data class ElementParams(val text: String, val xPos: Int, val yEnd: Int, val size: Int, val duration: Int, val delayMillis: Int)

fun createBunch(text:String, xRange:Int, yEnd:Int, sizeRange:Int, durationRange:Int, delayRange:Int,  numberOfElements:Int):List<ElementParams>{
    val list = mutableListOf<ElementParams>()
    for( i in 0..numberOfElements){
        list.add(ElementParams(
            text = text, xPos = randomHelp(0, xRange), yEnd = yEnd, size = randomHelp(5, sizeRange),
            duration = randomHelp(500,durationRange),
            delayMillis = randomHelp(0, delayRange)
        ))
    }
    return list
}

fun randomHelp(min:Int, max:Int):Int = Random.nextInt(min, max)

@Preview
@Composable
fun Element(){
    Element("$", 0, 100, 16, 2000,0)
}


// X muss initial random sein
// dauer muss iinitial random sein
// höhe muss initial random sein
// höhe muss sich ändern
// größe muss initial radom sein
