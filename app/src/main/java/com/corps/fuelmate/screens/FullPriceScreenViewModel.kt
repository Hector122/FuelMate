package com.corps.fuelmate.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corps.fuelmate.FuelRepository
import com.corps.fuelmate.fuel.Fuel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FullPriceScreenViewModel @Inject constructor(
    private val fuelRepository: FuelRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FuelUiState>(Loading)
    val uiState: StateFlow<FuelUiState> = _uiState.asStateFlow()

//    val uiState: StateFlow<FuelUiState> = getFuelPrices().map {
//        when (it) {
//            is Success -> Success(it.fuelList)
//            is Error -> Error(it.message)
//            is Loading -> Loading
//        }
//    }.stateIn(
//        scope = viewModelScope,
//        started = WhileSubscribed(5000),
//        initialValue = Loading
//    )

    suspend fun getFuelPrices() {
        viewModelScope.launch {
            _uiState.value = Loading
            try {
                val date = "2021-12-12" //TODO: just to get the example data. Remove this
                //val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                val fuelList = fuelRepository.getFuelPrices(date)
                _uiState.value = Success(fuelList)

            } catch (e: Exception) {
                _uiState.value = Error(e.message ?: "Failed to load fuel prices")
            }
        }
    }
}

sealed interface FuelUiState
data object Loading : FuelUiState
data class Error(val message: String) : FuelUiState
data class Success(val fuelList: List<Fuel> = emptyList()) : FuelUiState

