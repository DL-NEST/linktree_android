package dl.example.linktree.view.router

import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import dl.example.linktree.R
import dl.example.linktree.view.page.main.BoardPage
import dl.example.linktree.view.page.main.DevicePage
import dl.example.linktree.view.page.main.RulesPage
import dl.example.linktree.view.page.main.SetupPage
import dl.example.linktree.view.page.otherPage.MessageBox
import dl.example.linktree.viewModel.MainViewModel

enum class MainSections(
    var path: String,
    @DrawableRes val icon: Int,
) {
    // 主路由
    DEVICE("main/device", R.drawable.ic_device_bottom),
    BOARD("main/board", R.drawable.ic_board_bottom),
    RULES("main/rules", R.drawable.ic_rules_bottom),
    SETUP("main/setup", R.drawable.ic_setup_bottom),
    // 其他路由
}

enum class OtherSections(
    var path: String,
) {
    // 其他路由
    MSG("messageBox")
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainRouter(viewModel: MainViewModel, navController: NavHostController) {
    composable(MainSections.DEVICE.path) {
        DevicePage(viewModel = viewModel, navController = navController)
    }
    composable(MainSections.BOARD.path) {
        BoardPage(viewModel = viewModel, navController = navController)
    }
    composable(MainSections.RULES.path) {
        RulesPage(viewModel = viewModel, navController = navController)
    }
    composable(MainSections.SETUP.path) {
        SetupPage(viewModel = viewModel, navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.otherRouter(viewModel: MainViewModel, navController: NavHostController) {
    composable(OtherSections.MSG.path) {
        MessageBox(viewModel = viewModel, navController = navController)
    }
}


