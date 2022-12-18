package com.crownedjester.soft.gamesinfoandnews.representation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.crownedjester.soft.gamesinfoandnews.R
import com.crownedjester.soft.gamesinfoandnews.representation.bundle.BundlePrefs
import com.crownedjester.soft.gamesinfoandnews.representation.common.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val sharedViewModel by viewModel<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                onViewCreated(navController)

            }
        }

    }

    private suspend fun onViewCreated(navController: NavController) {
        sharedViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.OnBack ->
                    navController.navigateUp()

                is UiEvent.OnNavigate<*> -> {
                    navController.navigate(
                        event.resId,
                        bundleOf(BundlePrefs.GAME_ID_KEY to event.arg)
                    )

                }

                is UiEvent.ShowToast ->
                    Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_LONG)
                        .show()
            }
        }
    }

}