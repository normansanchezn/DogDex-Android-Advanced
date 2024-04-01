package dev.norman.dogdex.doglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.norman.dogdex.Dog
import dev.norman.dogdex.DogRepository
import dev.norman.dogdex.api.ApiResponseStatus
import kotlinx.coroutines.launch

class DogListViewModel: ViewModel() {

    private val _dogList = MutableLiveData<List<Dog>>()

    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus>
        get() = _status

    val dogList: LiveData<List<Dog>>
        get() = _dogList

    private val dogRepository = DogRepository()

    init {
        downloadDogs()
    }

    private fun downloadDogs() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading
            handleResponseStatus(dogRepository.downloadDogs())
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            _dogList.value = apiResponseStatus.dogList
        }
        _status.value = apiResponseStatus
    }
}