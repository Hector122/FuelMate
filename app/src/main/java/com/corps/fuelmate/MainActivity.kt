package com.corps.fuelmate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.corps.fuelmate.screens.MainScreen
import com.corps.fuelmate.ui.theme.FuelMateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(),
    AnalyticsLogger by AnalyticsLoggerImpl(),
    DeepLinkHandler by DeepLinkHandlerImpl() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerLivecycleOwner(this)

        enableEdgeToEdge() //TODO: check if this is needed
        setContent {
            FuelMateTheme {
                MainScreen()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeepLink(intent)
    }
}

// Example delegation
interface DeepLinkHandler {
    fun handleDeepLink(intent: Intent?)
}

class DeepLinkHandlerImpl : DeepLinkHandler {
    override fun handleDeepLink(intent: Intent?) {
        //Do something with the intent
    }
}

interface AnalyticsLogger {
    fun registerLivecycleOwner(owner: LifecycleOwner)
}

class AnalyticsLoggerImpl : AnalyticsLogger, LifecycleEventObserver {
    override fun registerLivecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> println("User Open Screen")
            Lifecycle.Event.ON_PAUSE -> println("User Leave Screen")
            Lifecycle.Event.ON_STOP -> TODO()
            Lifecycle.Event.ON_DESTROY -> TODO()
            Lifecycle.Event.ON_ANY -> TODO()
            else -> Unit
        }
    }

    // Example delegation finish
}
