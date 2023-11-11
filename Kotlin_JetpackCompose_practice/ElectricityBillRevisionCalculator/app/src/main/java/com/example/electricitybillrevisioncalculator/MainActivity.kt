package com.example.electricitybillrevisioncalculator

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricMeter
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.outlined.ElectricMeter
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.electricitybillrevisioncalculator.Composables.tabRowBuild
import com.example.electricitybillrevisioncalculator.Composables.topAppBarComposable
import com.example.electricitybillrevisioncalculator.data.tabRowElement
import com.example.electricitybillrevisioncalculator.ui.theme.ElectricityBillRevisionCalculatorTheme

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tabItems = listOf(
            tabRowElement(
                title = "Check Meter",
                selectedIcon = Icons.Filled.ElectricMeter,
                unselectedIcon = Icons.Outlined.ElectricMeter),
            tabRowElement(
                title = "Bill Revision",
                selectedIcon = Icons.Filled.RateReview,
                unselectedIcon = Icons.Outlined.RateReview),
            tabRowElement(
                title = "Theft Assessment",
                selectedIcon = Icons.Filled.MonetizationOn,
                unselectedIcon = Icons.Outlined.MonetizationOn)
        )
        setContent {
            ElectricityBillRevisionCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        modifier = Modifier
                            .fillMaxSize(),
                        topBar = {
                            topAppBarComposable(
                                modifier = Modifier
                            )
                        }
                    ){values ->
                        tabRowBuild(
                            modifier = Modifier
                                .padding(values),
                            tabItems
                        )
                    }
                }
            }
        }
    }
}
