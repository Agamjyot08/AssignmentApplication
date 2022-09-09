package com.agamjyot.assignmentapp.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agamjyot.assignmentapp.data.models.ContactsResponse
import com.agamjyot.assignmentapp.domain.repository.MainRepository
import com.agamjyot.assignmentapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private var repository: MainRepository) : ViewModel() {

    private val _getDataRes = MutableStateFlow<Resource<ContactsResponse>>(Resource.Initial)
    val getDataRes: StateFlow<Resource<ContactsResponse>> = _getDataRes.asStateFlow()

    fun getItems() = viewModelScope.launch {
        _getDataRes.value = Resource.Loading
        _getDataRes.value = repository.getResponse()
    }
}