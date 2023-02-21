package com.ltech.test.presentation.post

import androidx.lifecycle.*
import com.ltech.test.domain.model.Post
import com.ltech.test.domain.repository.LTechRepository
import com.ltech.test.utils.Constants.IO_ERROR_MESSAGE
import com.ltech.test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: LTechRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val mPost: MutableLiveData<Post?> = MutableLiveData(null)
    val post: LiveData<Post?>
        get() = mPost

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>("postId") ?: ""
            val response = repository.getPostById(id)
            mPost.postValue(response)
        }
    }
}