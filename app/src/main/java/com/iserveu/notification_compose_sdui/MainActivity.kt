package com.iserveu.notification_compose_sdui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.iserveu.notification_compose_sdui.ui.theme.Notification_compose_SDUITheme
import com.iserveu.notification_compose_sdui_library.NotificationHistoryActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Notification_compose_SDUITheme {
                Greeting(name = "Notification", this@MainActivity)
            }
        }
    }
}

@Composable
fun Greeting(name: String, context: Context) {
    Text(text = "Hello $name!", modifier = Modifier.clickable {
        val intent = Intent(context, NotificationHistoryActivity::class.java)
        context.startActivity(intent)
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Notification_compose_SDUITheme {
//        Greeting("Android")
    }
}