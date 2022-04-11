package dl.example.linktree.view.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.unit.Constraints

@Composable
fun MyCustomLayout(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables: List<Measurable>,
        constraints: Constraints ->
        // placeables 是经过测量的子元素，它拥有自身的尺寸值
        val placeables = measurables.map { measurable ->
            // 测量所有子元素，这里不编写任何自定义测量逻辑，只是简单地
            // 调用 Measurable 的 measure 函数并传入 constraints
            measurable.measure(constraints)
        }
        val width = 12// 根据 placeables 计算得出
        val height = 13// 根据 placeables 计算得出
            // 报告所需的尺寸
            layout (width, height) {
                placeables.forEach { placeable ->
                    // 通过遍历将每个项目放置到最终的预期位置
                    placeable.place(
                        x = 3,
                        y = 4
                    )
                }
            }
    }
}