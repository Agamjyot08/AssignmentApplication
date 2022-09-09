package com.agamjyot.assignmentapp.data

import com.agamjyot.assignmentapp.data.models.ContactsResponse
import retrofit2.http.GET

interface Service {

    @GET("1017754678385197056")
    suspend fun getResponse(): ContactsResponse
}