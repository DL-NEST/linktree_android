package dl.example.linktree.view.page.main

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dl.example.linktree.R
import dl.example.linktree.viewModel.MainViewModel
import dl.example.linktree.view.router.OtherSections
import dl.example.linktree.viewModel.Device
import dl.example.linktree.viewModel.DeviceGroup
import kotlinx.coroutines.launch
import androidx.compose.foundation.combinedClickable
import dl.example.linktree.view.component.*
import dl.example.linktree.view.component.TabRowDefaults
import dl.example.linktree.view.component.TabRowDefaults.tabIndicatorOffset

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DevicePage(viewModel: MainViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 20.dp, 33.dp, 24.dp)
        ) {
            TopCard(viewModel.user)
            NoticeIcon(viewModel.msg.value, onClick = {
                navController.navigate(OtherSections.MSG.path)
            })
        }
        // 场景列表
        Scenario(viewModel)
        // 设备列表
        DeviceList(viewModel)
    }
}

/*
* 场景列表
* */
@Composable
fun Scenario(
    viewModel: MainViewModel
) {
    Text(text = "场景列表",
        fontSize = 4.em,
        fontWeight = Bold,
        modifier = Modifier.padding(start = 24.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Color.Transparent)
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.width(18.dp))
            for (i in 1..5){
                    Surface(
                        modifier = Modifier
                            .height(64.dp)
                            .width(100.dp),
                        color = Color.White,
                        tonalElevation = 4.dp,
                        shadowElevation = 4.dp,
                    ) {

                    }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    Spacer(modifier = Modifier.height(18.dp))
}
/*
* 设备列表
* */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun DeviceList(
    viewModel : MainViewModel
){
    Text(text = "设备列表",
        fontSize = 4.em,
        fontWeight = Bold,
        modifier = Modifier.padding(start = 24.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    val pagerState = rememberPagerState()
    var expanded by remember { mutableStateOf(false) }
    // TabRow
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
    ) {
        Box(
            modifier = Modifier
            .fillMaxHeight()
            .width(330.dp).padding(start = 6.dp)
        ){
            ScrollableTab(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.height(32.dp),
                containerColor = Color.Transparent,
                contentColor = Color.Black,
                ScrollableTabRowMinimumTabWidth = 75.dp,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .padding(start = 18.dp, end = 18.dp)
                            .height(2.dp),
                        color = Color.Black
                    )
                },
                divider = {},
                edgePadding = 1.dp
            ) {
                val scope = rememberCoroutineScope() // 创建 CoroutineScope
                viewModel.pageGroup.forEachIndexed { index, data ->
                    TabU(
                        modifier = Modifier.fillMaxHeight(), //这里必须设置高度，否则会展示不正常
                        selected = pagerState.currentPage == index,
                        interactionSource = remember { MutableInteractionSource() },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Gray,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = index)
                            }
                        }) {
                        Text(text = data.name, fontSize = 3.6.em, fontWeight = Bold)
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .clickable { expanded = true },
        ) {
            Icon(painter = painterResource(R.drawable.ic_list), contentDescription = null, tint = Color.Black, modifier = Modifier.size(20.dp))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(color = Color.White)
            ) {
                DropdownMenuItem(
                    onClick = {
                        viewModel.addDevice(pagerState.currentPage)
                        expanded = false },
                    text = {Text("添加设备", style = TextStyle(color = Color.Black))}
                )
                DropdownMenuItem(
                    onClick = {
                        viewModel.addPageGroup()
                        expanded = false },
                    text = {Text("添加组", style = TextStyle(color = Color.Black))}
                )
                DropdownMenuItem(onClick = { /* Handle refresh! */ }, text = {Text("设备管理", style = TextStyle(color = Color.Black))})

            }
        }
    }
    // page
    HorizontalPager(
        count = viewModel.pageGroup.size,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
       DevicePage(deviceGroup = viewModel.pageGroup[page])
    }
}

@Composable
fun DevicePage(
    deviceGroup: DeviceGroup
) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(top = 16.dp),
    ) {
//        Spacer(modifier = Modifier.width(16.dp))
        FlowRow(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            // 布局方向的间距
            mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
            // 布局方向的相反的间距
            crossAxisSpacing = 10.dp,
            crossAxisAlignment = FlowCrossAxisAlignment.Center,
            lastLineMainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
        ) {
            deviceGroup.DeviceList.forEachIndexed{ _, device ->
                Device(device)
            }
        }
//        Spacer(modifier = Modifier.width(16.dp))
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Device(device: Device) {
    Surface(
        modifier = Modifier
            .width(170.dp)
            .height(140.dp)
            .clickable {  },
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
        color = Color.White,
        shape = MaterialTheme.shapes.small
    ) {
        Text(text = ""+device.name)
    }
}


