package com.dexmemore.testproject.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("number")
    var number: String
)

