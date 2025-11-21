package com.example.thebeacon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebeacon.data.remote.model.ContentItem
import com.example.thebeacon.data.repository.ContentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ContentDetailUIState(
    val isLoading: Boolean = false,
    val item: ContentItem? = null,
    val error: String? = null
)

class ContentDetailViewModel(
    private val repository: ContentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContentDetailUIState())
    val uiState: StateFlow<ContentDetailUIState> = _uiState

    fun loadContent(id: String) {
        viewModelScope.launch {
            _uiState.value = ContentDetailUIState(isLoading = true)
            try {
                val result = repository.getContentDetail(id)
                _uiState.value = ContentDetailUIState(item = result)
            } catch (e: Exception) {
                _uiState.value = ContentDetailUIState(error = e.message)
            }
        }
    }
}
