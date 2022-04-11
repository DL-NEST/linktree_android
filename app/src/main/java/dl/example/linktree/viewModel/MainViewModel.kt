package dl.example.linktree.viewModel

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dl.example.linktree.R

class User (
    var id: Int,
    var name: String,
    var say: String,
    @DrawableRes val headImg: Int = 0
)

class Device(
    var id: Int,
    var name: String,
    var topic: String,
    var click: Boolean,
)

class DeviceGroup(
    var name: String,
    var DeviceList: MutableList<Device>,
)

class MainViewModel : ViewModel() {
    // 使用mutableStateOf声明可变的状态，也就是参数或者量

    val user by mutableStateOf(
        User(id = 232, name = "DL-NEST", say = "活得漂亮是种本能", R.drawable.head)
    )

    var msg = mutableStateOf(true)

    // 信息的操作
    fun userUpdate(){
        user.id = 554325
        user.name = "XXXX"
        user.say = "wsgseg"
    }

    val pageGroup = mutableStateListOf(
        DeviceGroup(name = "全部", DeviceList = mutableStateListOf(
            Device(id = 325, name = "台灯", topic = "test", click = true),
            Device(id = 325, name = "空调", topic = "test", click = true),
            Device(id = 325, name = "空调", topic = "test", click = true),
            Device(id = 325, name = "空调", topic = "test", click = true),
            Device(id = 325, name = "空调", topic = "test", click = true),
            Device(id = 325, name = "空调", topic = "test", click = true)

        )
        ),
        DeviceGroup(name = "客厅", DeviceList = mutableStateListOf(
            Device(id = 12243, name = "栈灯", topic = "test", click = true)
        )),
        DeviceGroup(name = "卧室",DeviceList = mutableStateListOf(
            Device(id = 12243, name = "台灯", topic = "test", click = true)
        ))
    )

    fun addPageGroup(){
        pageGroup.add(DeviceGroup(name = "归看过台",DeviceList = mutableStateListOf(
            Device(id = 12243, name = "台灯", topic = "test", click = true)
        )))
    }
    fun addDevice(listIndex: Int){
        pageGroup[listIndex].DeviceList.add(Device(id = 12243, name = "台灯", topic = "test", click = true))
    }
}

