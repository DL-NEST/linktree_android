package dl.example.linktree.view.theme.material

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

/**
 * AppColors 自定义材质类
 */

@Stable
class AppColors(
    mainBackground: Color,
    bottomBarBackground: Color,
    bottomBarClickColor: Color,
    bottomBarColor: Color
) {
    var mainBackground by mutableStateOf(mainBackground)
        private set
    var bottomBarBackground by mutableStateOf(bottomBarBackground)
        private set
    var bottomBarClickColor by mutableStateOf(bottomBarClickColor)
        private set
    var bottomBarColor by mutableStateOf(bottomBarColor)
        private set

    fun copy(): AppColors = AppColors(
        mainBackground = mainBackground,
        bottomBarBackground = bottomBarBackground,
        bottomBarClickColor = bottomBarClickColor,
        bottomBarColor = bottomBarColor,
    )

    fun update(other: AppColors) {
        mainBackground = other.mainBackground
        bottomBarBackground = other.bottomBarBackground
        bottomBarClickColor = other.bottomBarClickColor
        bottomBarColor = other.bottomBarColor
    }
}

