package dl.example.linktree.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dl.example.linktree.R

@Composable
fun NoticeIcon(
    noticeBool:Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.clickable(onClick = onClick, indication = null, interactionSource = remember {
            MutableInteractionSource()
        })
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_notice_off),
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .zIndex(1F)
        )
        if (noticeBool){
            Box(modifier = Modifier.padding(start = 16.dp, top = 0.3.dp).zIndex(2f)){
                Surface(
                    modifier = Modifier
                        .size(7.dp)
                        .zIndex(1F),
                    shape = CircleShape,
                    color = Color.Red,
                    elevation = 1.dp
                ){}
            }
        }
    }

}

@Preview
@Composable
fun NoticeIconShow() {
}
