package dl.example.linktree.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dl.example.linktree.view.page.guide.Page1
import dl.example.linktree.view.theme.LinkTreeTheme

/*
* 引导页面
* */

@OptIn(ExperimentalPagerApi::class)
class GuideActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LinkTreeTheme {
                val pagerState = rememberPagerState()
                val scope = rememberCoroutineScope() // 创建 CoroutineScope
                
                HorizontalPager(
                    count = 3,
                    modifier = Modifier,
                    state = pagerState,
                    userScrollEnabled = false
                ) { page ->
                    when (page) {
                        0 -> { GuidePage(backgroundColor = Color.Red){ Page1(scope,pagerState,page) }}
                        1 -> { GuidePage(backgroundColor = Color.White){}}
                        2 -> { GuidePage(backgroundColor = Color.Cyan){} }
                    }
                }
            }
        }
    }
}

@Composable
fun GuidePage(
    backgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        color = backgroundColor,
        modifier = modifier.fillMaxSize().navigationBarsPadding(),
    ) {
        Surface(
            color = backgroundColor,
            modifier = Modifier.statusBarsPadding()
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}