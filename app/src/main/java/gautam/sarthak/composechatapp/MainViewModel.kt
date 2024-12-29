package gautam.sarthak.composechatapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gautam.sarthak.composechatapp.data.DogsDetail
import gautam.sarthak.composechatapp.data.DogsInterface
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: DogsInterface
) : ViewModel() {

    private val _state = mutableStateOf(DogState())
    val state: State<DogState> = _state

    init {
        getRandomDogs()
    }

    fun getRandomDogs() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    dog = api.getRandomDog(),
                    isLoading = false
                )
            } catch(e: Exception) {
                Log.e("MainViewModel", "getRandomDog: ", e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    data class DogState(
        val dog: DogsDetail? = null,
        val isLoading: Boolean = false
    )
}