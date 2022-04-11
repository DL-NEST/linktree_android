package dl.example.linktree.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dl.example.linktree.view.app.rememberAppState
import dl.example.linktree.view.component.BottomBar
import dl.example.linktree.view.component.HomeScaffold
import dl.example.linktree.view.router.MainSections
import dl.example.linktree.view.router.OtherSections
import dl.example.linktree.view.router.mainRouter
import dl.example.linktree.view.router.otherRouter
import dl.example.linktree.view.theme.LinkTreeTheme
import dl.example.linktree.viewModel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置ui在状态栏的后面,必须要全面屏支持
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            LinkTreeTheme {
                // 初始化app的全局状态
                val appState = rememberAppState()
                // 主页的构造
                HomeScaffold(
                    backgroundColor = LinkTreeTheme.colors.mainBackground,
                    bottomBar = {
                        if (appState.shouldShowBottomBar){
                            BottomBar(
                                navController = appState.navController,
                                backgroundColor = LinkTreeTheme.colors.bottomBarBackground
                            )
                        }
                    }
                ){
                    AnimatedNavHost(
                        navController = appState.navController,
                        startDestination = "main",
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                    ) {
                        // 添加主页路由
                        navigation(
                            startDestination = MainSections.DEVICE.path,
                            route = "main",
                            enterTransition = {
                                 slideIntoContainer(
                                     towards = AnimatedContentScope.SlideDirection.Left,
                                     animationSpec = tween(200)
                                 )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentScope.SlideDirection.Right,
                                    animationSpec = tween(200)
                                )
                            }
                        ){
                            mainRouter(viewModel,appState.navController)
                        }
                        navigation(startDestination = OtherSections.MSG.path, route = "other"){
                            otherRouter(viewModel,appState.navController)
                        }

                    }
                }
            }
        }
    }

    // 活动的后退事件
    override fun onBackPressed() {
        super.onBackPressed()
    }
}

