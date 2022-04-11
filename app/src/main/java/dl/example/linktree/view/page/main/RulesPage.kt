package dl.example.linktree.view.page.main

import android.util.Log
import android.util.Size
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import dl.example.linktree.view.component.GridLayout
import dl.example.linktree.viewModel.MainViewModel
import kotlin.math.roundToInt

/*
-   detectTapGestures 提供了四个可选参数
-   onDoubleTap (可选)：双击时回调
-   onLongPress (可选)：长按时回调
-   onPress (可选)：按下时回调
-   onTap (可选)：轻触时回调
*/

enum class LikedStates {
    Initial,
    Liked,
    Disappeared
}

@Composable
fun RulesPage(viewModel: MainViewModel, navController: NavHostController) {
    Column(
        verticalArrangement= Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        for (i in 1..20){
            TransformGestures(){
                Text(text = ""+i)
            }
        }
    }
}

@Composable
fun TransformGestures(
    content: @Composable () -> Unit
) {
    // 大小
    var zoom by remember { mutableStateOf(1f) }
    // 移动显示的位置
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var elevation by remember { mutableStateOf(2.dp) }
    var zindex by remember { mutableStateOf(2f) }
    Surface(
        elevation = elevation,
        color = Color.White,
        shape =  RoundedCornerShape(12.dp),
        modifier = Modifier
            .offset { IntOffset(0, offsetY.roundToInt()) }
//            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .zIndex(zindex)
            .fillMaxWidth()
            .height(80.dp)
            .padding(8.dp)
            .hoverable(
                interactionSource = MutableInteractionSource(),
                enabled = true
            )
            .graphicsLayer(
                scaleX = zoom,
                scaleY = zoom,
            )
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(
                    onDragStart = {
                        zoom += 0.1f
                        elevation = 4.dp
                        zindex = 10f
                    },
                    onDragCancel = {
                        zoom -= 0.1f
                        elevation = 2.dp
                        zindex = 2f
                    },
                    onDragEnd = {
                        zoom -= 0.1f
                        elevation = 2.dp
                        zindex = 2f
                    }
                ) { _, dragAmount ->
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ) {
        content()
    }
}