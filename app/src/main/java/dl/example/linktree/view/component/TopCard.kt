package dl.example.linktree.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import dl.example.linktree.R
import dl.example.linktree.view.theme.Shapes
import dl.example.linktree.viewModel.User

/*
* 设备页面顶部的用户卡片
* */
@Composable
fun TopCard(user: User, modifier: Modifier=Modifier) {
    Row(
        modifier = modifier
    ) {
        // 图片的占位
        Surface(
            modifier = Modifier.size(50.dp),
            shape = Shapes.small,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(user.headImg),
                contentDescription = "user head",
                Modifier.size(48.dp).clip(Shapes.small)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = user.name,
                fontSize = 4.em,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = user.say,
                fontSize = 2.8.em
            )
        }

    }
}

@Preview
@Composable
fun TopCardBarShow() {
    Scaffold(
        Modifier.padding(20.dp)
    ){
        TopCard(user = User(id = 424, name = "DL-NEST","活得漂亮是种本能",R.drawable.head))
    }
}