package com.ltech.test.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltech.test.domain.repository.LTechRepository
import com.ltech.test.utils.Constants.IO_ERROR_MESSAGE
import com.ltech.test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: LTechRepository
): ViewModel() {

    private val mPostsState = MutableLiveData(MainStateList())
    val postsState: LiveData<MainStateList>
        get() = mPostsState

    init {
        getPosts()
    }

    fun getPosts() {
        repository.getPosts().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    mPostsState.postValue(MainStateList(posts = result.data))
                }
                is Resource.Error -> {
                    mPostsState.postValue(MainStateList(error = result.message ?: IO_ERROR_MESSAGE))
                }
                is Resource.Loading -> {
                    mPostsState.postValue(MainStateList(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

}