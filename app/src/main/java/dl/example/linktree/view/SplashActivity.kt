package dl.example.linktree.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import dl.example.linktree.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置全屏
        setContent {
            Splash()
        }
        val sharedPreferences = getSharedPreferences("share", ComponentActivity.MODE_PRIVATE)
        // 使用协程在                                                                                                                                                                                                                                                                                                                                                                                                                     画图结束延时跳转
        GlobalScope.launch{
            delay(500)
            nextMain(this@SplashActivity,sharedPreferences)
        }
    }

}
// 跳转到主活动
private fun nextMain(splashActivity: SplashActivity, sharedPreferences: SharedPreferences) {
    // 获取配置文件的isFirstRun
    val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)
    // 设置配置文件
    val editor = sharedPreferences.edit()
    val intent = if (isFirstRun) {
        Log.d("debug", "第一次运行")
        editor.putBoolean("isFirstRun", false)
        editor.apply()
        Intent(splashActivity, GuideActivity::class.java)
    } else {
        Log.d("debug", "不是第一次运行")
        Intent(splashActivity, MainActivity::class.java)
    }
    splashActivity.startActivity(intent)
    splashActivity.finish();
}

@Composable
fun Splash(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(modifier = Modifier.size(160.dp), painter = painterResource(R.drawable.ic_logo), contentDescription = null, tint = Color(0xFF1296db))
            Text(text = "LinkTree", fontSize = 8.em)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    Splash()
}
