package com.corps.fuelmate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corps.fuelmate.fuel.Fuel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fuelRepository: FuelRepository
) : ViewModel() {

    val fuelPriceFlow = MutableStateFlow<List<Fuel>>(emptyList())

    fun getFuelPrices() {
        viewModelScope.launch {
            val date = "2021-12-12"
            fuelPriceFlow.value = fuelRepository.getFuelPrices(date)
        }
    }
}
