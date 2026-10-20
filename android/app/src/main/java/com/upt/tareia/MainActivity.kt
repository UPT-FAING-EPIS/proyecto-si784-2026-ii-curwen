package com.upt.tareia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.upt.tareia.ui.TareiaApp
import com.upt.tareia.ui.theme.TareiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            TareiaTheme { TareiaApp() }
        }
    }
}
