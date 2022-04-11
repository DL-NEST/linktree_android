package dl.example.linktree.view.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dl.example.linktree.view.router.MainSections
import dl.example.linktree.view.theme.LinkTreeTheme
import com.google.accompanist.navigation.animation.navigation

@Composable
fun BottomBar(
    navController: NavHostController,
    backgroundColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(12.dp),
) {
    // 路由列表
    val bottomBarTabs = MainSections.values()
    // 获取当前路由
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRouter = navBackStackEntry?.destination?.route
    // 永远悬浮在最上层
    Column(
        modifier = Modifier.padding(28.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ){
        Surface(
            modifier = Modifier.height(60.dp),
            elevation = 2.dp,
            color = backgroundColor,
            shape =shape,
        ) {
            Box(
                Modifier
            ){
                Row(
                    // 垂直位置
                    verticalAlignment = Alignment.CenterVertically,
                    // 水平位置
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    bottomBarTabs.map {
                        BtnItem(
                            id = it.icon,
                            clickState = currentRouter == it.path,
                            onClick= {
                                // 判断是否不等于的当前路由
                                navController.navigate(it.path){
                                    // 返回开始目标
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun BtnItem(
    @DrawableRes id: Int,
    clickState: Boolean,
    onClick: () -> Unit,
){
    Column(
        verticalArrangement=Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .width(64.dp)
            .clickable(onClick = onClick, indication = null, interactionSource = remember {
                MutableInteractionSource()
            })
    ) {
        if (clickState){
            Icon(
                painter = painterResource(id),
                contentDescription = null,
                tint = LinkTreeTheme.colors.bottomBarClickColor,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Surface(
                modifier = Modifier
                    .size(6.dp)
                    .align(Alignment.CenterHorizontally),
                shape = CircleShape,
                color = LinkTreeTheme.colors.bottomBarClickColor,
                elevation = 1.dp
            ){}
        } else{
            Icon(
                painter = painterResource(id),
                contentDescription = null,
                tint = LinkTreeTheme.colors.bottomBarColor,
                modifier = Modifier.size(24.dp).align(Alignment.CenterHorizontally)
            )
        }
    }
}


