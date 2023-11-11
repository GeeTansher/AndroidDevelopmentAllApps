package com.example.electricitybillrevisioncalculator.Composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.electricitybillrevisioncalculator.data.tabRowElement

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun tabRowBuild(
    modifier: Modifier,
    tabItems: List<tabRowElement>
){
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val pageState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex){
        pageState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pageState.currentPage, pageState.isScrollInProgress){
        if(!pageState.isScrollInProgress){
            selectedTabIndex = pageState.currentPage
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, element ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(element.title)
                    },
                    icon = {
                        Icon(
                            imageVector = if(index == selectedTabIndex){ element.selectedIcon}
                            else{element.unselectedIcon},
                            contentDescription = element.title
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {index ->
            if (index == 0){
                checkMeterComposable(
                    modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                )
            }
            else if(index == 1){
                billRevisionComposable(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                )
            }
            else if(index == 2){
                theftAssessmentComposable(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                )
            }
        }
    }
}