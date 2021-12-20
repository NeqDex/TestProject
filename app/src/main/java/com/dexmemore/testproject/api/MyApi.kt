package com.dexmemore.testproject.api

import com.dexmemore.testproject.model.NumbResponse
import com.dexmemore.testproject.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET("raw/8wJzytQX")
    suspend fun getPost(): Response<NumbResponse>
}