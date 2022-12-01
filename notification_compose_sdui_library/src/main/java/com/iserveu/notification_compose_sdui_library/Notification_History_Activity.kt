package com.iserveu.notification_compose_sdui_library

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.iserveu.notification_compose_sdui_library.ui.theme.Blue
import com.iserveu.notification_compose_sdui_library.ui.theme.Notification_compose_SDUITheme
import com.iserveu.notification_compose_sdui_library.ui.theme.greenColor
import kotlinx.coroutines.launch

class NotificationHistoryActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Notification_compose_SDUITheme {
                // A surface container using the 'background' color from the theme
                TabLayout()
            }
        }
    }
}




@Preview
@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState(pageCount = 2)
    val selectedTab = remember { mutableStateOf("") }

    Column(

        modifier = Modifier.background(Color.White)
    ) {
        TopAppBar(backgroundColor = Blue) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Row(modifier = Modifier.fillMaxSize()) {
                    IconButton(onClick = {
                        Log.d("onclick", "wallet")
                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Text(
                        text = if(selectedTab.value == "Update") "Update" else "Transaction",
                        style = TextStyle(color = Color.White),
                        fontWeight = FontWeight.Bold,
                        fontSize = TextUnit(
                            18F,
                            TextUnitType.Sp
                        ),
                        modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                        textAlign = TextAlign.Center
                    )
                }

            }
        }

        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState){
            selectedTab.value =it
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Transaction",
        "Update"
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Blue,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {

        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },

                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)

                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    onStateChanged: (String) -> Unit
) {

    HorizontalPager(state = pagerState) {
            page ->
        when (page) {
            0 ->{
                TabContentScreen(data = "Welcome Transaction")
                onStateChanged("Transaction")
            }
            1 -> {
                TabContentScreen(data = "Welcome to Update")
                onStateChanged("Update")
            }

        }
    }
}


@Composable
fun TabContentScreen(data: String) {

    Column(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = data,
            style = MaterialTheme.typography.h5,
            color = greenColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}