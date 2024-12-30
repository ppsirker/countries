package com.dsalgo.countries.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsalgo.countries.data.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {
    private val _countries = MutableLiveData<UiState>(UiState.Loading)
    val uiState: LiveData<UiState>
        get() = _countries

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = countryRepository.getCountries()
                _countries.postValue(UiState.Success(result))
            } catch (e: Exception) {
                _countries.postValue(UiState.Error("Error loading data"))
            }

        }
    }

}