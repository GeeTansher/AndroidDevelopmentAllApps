package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            StylingText()
            ColorBox()
        }
    }
}

@Preview
@Composable
fun ColorBox(){
    val color = remember {
        mutableStateOf(Color.Yellow)
    }
    Column(Modifier.fillMaxSize()) {
        ColorBoxMaking(
            Modifier
                .weight(1f)
                .fillMaxSize()
        ){
            color.value = it
        }
        Box(modifier = Modifier
            .background(color.value)
            .weight(1f)
            .fillMaxSize())
    }

}

@Composable
fun ColorBoxMaking(modifier: Modifier, updateColor: (Color) -> Unit){

    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            // updateColor is lambda function
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
//            color.value = Color(
//                Random.nextFloat(),
//                Random.nextFloat(),
//                Random.nextFloat(),
//                1f
//            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun StylingText() {
    val fontFamily = FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_bold, FontWeight.Bold),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
        Font(R.font.lexend_extralight, FontWeight.ExtraLight),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_medium, FontWeight.Medium)
    )
    JetpackComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))
        ) {
            Text(
//                text = "Jetpack Compose",
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontSize = 50.sp,
                            textDecoration = TextDecoration.None
                        )
                    ){
                        append('J')
                    }

                    append("etpack ")

                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontSize = 50.sp,
                            textDecoration = TextDecoration.None
                        )
                    ){
                        append('C')
                    }

                    append("ompose")
                },
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center
            )
        }
    }
}