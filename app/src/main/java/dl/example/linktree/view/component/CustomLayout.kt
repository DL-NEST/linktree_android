package dl.example.linktree.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.unit.dp
import kotlin.time.measureTime


@Composable
fun HomeScaffold(
    modifier: Modifier = Modifier,
    backgroundColor : Color,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
){
    Surface(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        color = backgroundColor,
        content = {
            content()
            bottomBar()
        }
    )
}