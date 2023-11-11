package com.example.electricitybillrevisioncalculator.Composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBarComposable(
    modifier: Modifier
){
    TopAppBar(
        modifier = Modifier
//            .bottomBorder(5.dp, MaterialTheme.colorScheme.primary)
            /*.drawBehind {
                val strokeWidth = 100f
                val y = size.height - strokeWidth / 2

                drawLine(
                    color = Color(0xFF6650a4),
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }*/
        ,title = {
            Text(
                text = "Electricity Bill Calculator",
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.EditNote,
                    contentDescription = "Edit Tariff rates"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Information regarding app"
                )
            }
        }
    )
}


//fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
//    factory = {
//        val density = LocalDensity.current
//        val strokeWidthPx = density.run { strokeWidth.toPx() }
//
//        Modifier.drawBehind {
//            val width = size.width
//            val height = size.height - strokeWidthPx/2
//
//            drawLine(
//                color = color,
//                start = Offset(x = 0f, y = height),
//                end = Offset(x = width , y = height),
//                strokeWidth = strokeWidthPx
//            )
//        }
//    }
//)
//fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed {
//    val density = LocalDensity.current
//    val strokeWidthPx = density.run { strokeWidth.toPx() }
//
//    Modifier.drawBehind {
//        val width = size.width
//        val height = size.height - strokeWidthPx / 2
//
//        drawLine(
//            color = color,
//            start = Offset(x = 0f, y = height),
//            end = Offset(x = width, y = height),
//            strokeWidth = strokeWidthPx
//        )
//    }
//}