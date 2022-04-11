package dl.example.linktree.view.page.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import dl.example.linktree.view.component.DeviceCard
import dl.example.linktree.viewModel.MainViewModel

@Composable
fun BoardPage(viewModel: MainViewModel, navController: NavHostController) {

    val cardColor = remember {
        mutableStateOf<Color>(Color(0xFFEEF1F4))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .navigationBarsPadding()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color.Blue)
        ){
            Text(text = "服务器看板:服务器的运行时间",fontSize = 5.em)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
        ) {
            DeviceCard(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp),
                background = cardColor.value
            ) {
                Text(text = "ef")
            }
            DeviceCard(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp),
                background = cardColor.value
            ) {
                Text(text = "ef68")
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        DeviceCard(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            background = cardColor.value
        ) {
            Text(text = "ef")
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
        ) {
            DeviceCard(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp),
                background = cardColor.value
            ) {
                Text(text = "ef")
            }
            DeviceCard(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp),
                background = cardColor.value
            ) {
                Text(text = "ef68")
            }
        }
    }
}
