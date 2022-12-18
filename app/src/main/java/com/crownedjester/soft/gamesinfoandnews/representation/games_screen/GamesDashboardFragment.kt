package com.crownedjester.soft.gamesinfoandnews.representation.games_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crownedjester.soft.gamesinfoandnews.R
import com.crownedjester.soft.gamesinfoandnews.databinding.FragmentGamesDashboardBinding
import com.crownedjester.soft.gamesinfoandnews.representation.SharedViewModel
import com.crownedjester.soft.gamesinfoandnews.representation.common.UiEvent
import com.crownedjester.soft.gamesinfoandnews.representation.games_screen.adapter.AdapterCallback
import com.crownedjester.soft.gamesinfoandnews.representation.games_screen.adapter.GamesAdapter
import com.crownedjester.soft.gamesinfoandnews.representation.games_screen.viewmodel.GamesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesDashboardFragment : Fragment(R.layout.fragment_games_dashboard), AdapterCallback {

    private var _binding: FragmentGamesDashboardBinding? = null
    private val binding get() = _binding!!

    private val adapter = GamesAdapter(this)

    private val gamesViewModel: GamesViewModel by viewModel()
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGamesDashboardBinding.bind(view)

        binding.rvGames.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gamesViewModel.gamesDataStateFlow.collectLatest { state ->
                    when {
                        state.isLoading -> {
//                            TODO("Response to Loading State")
                        }

                        state.data?.isNotEmpty() == true -> adapter.differ.submitList(state.data)

                        state.error.isNotBlank() -> {
//                            TODO("Response to error occurred")
                        }
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(id: Int) {
        sharedViewModel.sendEvent(
            UiEvent.OnNavigate(
                R.id.action_gamesDashboardFragment_to_gameDetailFragment,
                id
            )
        )
    }

}