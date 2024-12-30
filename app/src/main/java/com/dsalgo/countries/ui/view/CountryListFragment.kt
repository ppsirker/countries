package com.dsalgo.countries.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsalgo.countries.data.api.CountryApi
import com.dsalgo.countries.data.repository.CountryRepository
import com.dsalgo.countries.databinding.CountryListFragmentBinding
import com.dsalgo.countries.ui.adapter.CountryAdapter
import com.dsalgo.countries.ui.viewmodel.CountryViewModel
import com.dsalgo.countries.ui.viewmodel.UiState
import com.dsalgo.countries.ui.viewmodel.ViewModelFactory

class CountryListFragment: Fragment() {
    private val viewModel: CountryViewModel by viewModels {
        ViewModelFactory(CountryRepository(CountryApi.getClient()))
    }
    private lateinit var binding: CountryListFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CountryListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CountryAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Error -> {
                    binding.recyclerView.visibility = GONE
                    binding.progressBar.visibility = GONE
                    binding.errorTextView.visibility = VISIBLE
                    binding.errorTextView.text=it.message
                }

                UiState.Loading -> {
                    binding.recyclerView.visibility = GONE
                    binding.progressBar.visibility = VISIBLE
                    binding.errorTextView.visibility = GONE

                }

                is UiState.Success -> {
                    binding.recyclerView.visibility = VISIBLE
                    binding.progressBar.visibility = GONE
                    binding.errorTextView.visibility = GONE
                    adapter.updateData(it.countries)
                }
            }
        }


    }
}