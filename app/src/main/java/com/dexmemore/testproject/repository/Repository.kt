package com.dexmemore.testproject.repository

import com.dexmemore.testproject.api.RetrofitInstance
import com.dexmemore.testproject.model.NumbResponse
import com.dexmemore.testproject.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<NumbResponse> {
        return RetrofitInstance.api.getPost()
    }
}