package com.dsalgo.countries.ui.viewmodel

import com.dsalgo.countries.data.model.Country

sealed interface UiState {
    data object Loading : UiState
    data class Success(val countries: List<Country>) : UiState
    data class Error(val message: String) : UiState
}