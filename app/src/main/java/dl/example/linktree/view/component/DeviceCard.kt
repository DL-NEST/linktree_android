package dl.example.linktree.view.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DeviceCard(
    modifier: Modifier = Modifier,
    background: Color,
    element: Dp = 2.dp,
    content: @Composable () -> Unit
){
    Surface(
        modifier = modifier,
        color = background,
        shape = RoundedCornerShape(24.dp),
        elevation = element,
        content = content
    )
}