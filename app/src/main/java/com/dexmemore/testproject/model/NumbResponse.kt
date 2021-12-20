package com.dexmemore.testproject.model

import com.google.gson.annotations.SerializedName

data class NumbResponse(
    @SerializedName("numbers")
    var numbers: ArrayList<Post> = ArrayList()
)

