package dl.example.linktree.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dl.example.linktree.view.theme.material.AppColors



// 黑暗模式下
private val DarkColorPalette = AppColors(
    mainBackground = Main_Background_Dark,
    bottomBarBackground = BottomBar_Background_Dark,
    bottomBarClickColor = BottomBar_click_Color_Dark,
    bottomBarColor = BottomBar_Color_Dark
)
// 正常模式
private val LightColorPalette = AppColors(
    mainBackground = Main_Background,
    bottomBarBackground = BottomBar_Background,
    bottomBarClickColor = BottomBar_click_Color,
    bottomBarColor = BottomBar_Color
)

object LinkTreeTheme {
    val colors: AppColors
        @Composable
        get() = LocalLinkTreeColors.current
}

val LocalLinkTreeColors = staticCompositionLocalOf<AppColors> {
    error("No LinkTreeColorPalette provided")
}

@Composable
fun LinkTreeTheme(
    // 获取主题模式
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // 设置状态栏
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !darkTheme
        )
    }
    // 根据主题模式更换主题的颜色
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    AppProvideColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun AppProvideColors(
    colors: AppColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalLinkTreeColors provides colorPalette, content = content)
}

fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)