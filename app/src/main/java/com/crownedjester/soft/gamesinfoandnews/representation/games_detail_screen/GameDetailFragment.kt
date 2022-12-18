package com.crownedjester.soft.gamesinfoandnews.representation.games_detail_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.crownedjester.soft.gamesinfoandnews.R
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameFullData
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameFullData.Companion.setupLogoWithScreenshots
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.Screenshot
import com.crownedjester.soft.gamesinfoandnews.databinding.FragmentGameDetailBinding
import com.crownedjester.soft.gamesinfoandnews.representation.SharedViewModel
import com.crownedjester.soft.gamesinfoandnews.representation.common.UiEvent
import com.crownedjester.soft.gamesinfoandnews.representation.games_detail_screen.adapter.ScreenshotsAdapter
import com.crownedjester.soft.gamesinfoandnews.representation.games_detail_screen.viewmodel.GameDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class GameDetailFragment : Fragment(R.layout.fragment_game_detail) {

    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!

    private val gameDetailViewModel by viewModel<GameDetailViewModel>()

    private var screenshotsAdapter: ScreenshotsAdapter? = null
    private val sharedViewModel by activityViewModels<SharedViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGameDetailBinding.bind(view)

        lifecycleScope.launch {
            gameDetailViewModel.currentGameDataStateFlow.collectLatest { dataState ->
                when {
                    dataState.isLoading -> {
                        binding.showLoadingProgress()
                    }

                    dataState.data != null -> {
                        delay(Random.nextLong(600) + 600)
                        val screenshots = setupLogoWithScreenshots(
                            dataState.data.screenshots,
                            Screenshot(-1, dataState.data.thumb)
                        )

                        screenshotsAdapter = ScreenshotsAdapter(
                            screenshots = screenshots
                        )


                        binding.setupScreen(dataState.data)

                    }

                    dataState.error.isNotBlank() -> {
                        binding.hideLoadingProgress()

                        sharedViewModel.sendEvent(UiEvent.ShowToast("Error Loading Data"))
                    }
                }

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun FragmentGameDetailBinding.setupScreen(gameData: GameFullData) {

        hideLoadingProgress()

        tagGenre.apply {
            tvTag.text = gameData.genre
            cardView.setCardBackgroundColor(
                resources.getColor(
                    R.color.genre_bg_color
                )
            )
            root.visibility = View.VISIBLE
        }

        tagPlatform.apply {
            tvTag.text = gameData.platform
            cardView.setCardBackgroundColor(
                resources.getColor(
                    R.color.platform_bg_color
                )
            )
            root.visibility = View.VISIBLE
        }
        textViewGameContent.text = "\t  ${(gameData.fullDescription ?: gameData.shortDescription)}"

        screenshotsAdapter?.let {
            viewPagerScreenshots.adapter = it
        }

        TabLayoutMediator(tabLayoutScreenshots, viewPagerScreenshots) { tab, position ->

        }.attach()

    }

    private fun FragmentGameDetailBinding.showLoadingProgress() {
        progressLoading.visibility = View.VISIBLE
    }

    private fun FragmentGameDetailBinding.hideLoadingProgress() {
        progressLoading.visibility = View.GONE
    }


}