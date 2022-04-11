package dl.example.linktree.view.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dl.example.linktree.view.router.MainSections

// 状态的类
class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
){
    init {

    }
    // 对状态的操作函数或者参数
    fun jump(){
        navController.backQueue[1]
    }

    private val bottomBarTabs = MainSections.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.path }

    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes
}

// 声明所有公共的app状态
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(
    // 占位视图的状态
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    // 全局导航的状态
    navController: NavHostController = rememberAnimatedNavController(),
    // 全局的app内通知的状态
) = remember(scaffoldState, navController) {
    AppState(scaffoldState, navController)
}