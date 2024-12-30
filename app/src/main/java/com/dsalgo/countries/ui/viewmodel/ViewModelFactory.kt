package com.dsalgo.countries.ui.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dsalgo.countries.data.repository.CountryRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: CountryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
