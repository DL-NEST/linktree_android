package dl.example.linktree.view.page.otherPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import dl.example.linktree.view.router.MainSections
import dl.example.linktree.viewModel.MainViewModel

@Composable
fun MessageBox(viewModel: MainViewModel, navController: NavHostController) {
    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            Box(
                Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(48.dp)
                    .background(Color.Gray)
                    .clickable(
                        onClick = {
                            navController.navigate(MainSections.DEVICE.path){
                                // 返回开始目标
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                                  },
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        })
            ){
            }
        }
    ) {
        
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "消息盒子")
    }
}