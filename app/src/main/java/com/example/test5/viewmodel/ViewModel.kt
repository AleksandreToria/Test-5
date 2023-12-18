package com.example.test5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test5.dataclass.ApiResponse
import com.example.test5.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class YourViewModel : ViewModel() {

    private val _dataFlow = MutableStateFlow<ApiResponse?>(null)
    val dataFlow: StateFlow<ApiResponse?> get() = _dataFlow

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.get()
                _dataFlow.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

