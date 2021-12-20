package com.dexmemore.testproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dexmemore.testproject.model.NumbResponse
import com.dexmemore.testproject.model.Post

import com.dexmemore.testproject.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<NumbResponse>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response

        }
    }
}