package dl.example.linktree.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import dl.example.linktree.R


@Composable
fun ItemList (
    name:String,
    tel:String,
    email:String
) {
    Row(
        modifier = Modifier
            .width(280.dp)
            .background(Color.White)
            .onFocusEvent {  }
            .clickable {  }
    ) {
        Surface(
            modifier = Modifier.size(50.dp).padding(4.dp).align(Alignment.CenterVertically),
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(R.drawable.head),
                contentDescription = null,
                Modifier
                    .size(48.dp)
                    // Clip image to be shaped as a circle
                    .clip(RoundedCornerShape(6.dp))
                    .align(Alignment.CenterVertically)
            )
        }
        Column(
            Modifier.padding(4.dp)
        ) {
            Text(text = name,fontSize = 3.em)
            Text(text = tel,fontSize = 2.2.em)
            Text(text = email,fontSize = 2.2.em)
        }
    }
}


@Preview
@Composable
fun ItemListShow(){
    Column() {
        ItemList("周润发","Tel:12414135535","Email:235234@163.com")
        ItemList("马云","Tel:455234523","Email:235234@163.com")
        ItemList("马如玉","Tel:400-300-8678","Email:235234@163.com")
    }
}