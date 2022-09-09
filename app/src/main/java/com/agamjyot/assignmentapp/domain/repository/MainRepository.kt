package com.agamjyot.assignmentapp.domain.repository

import com.agamjyot.assignmentapp.data.SafeApiCall
import com.agamjyot.assignmentapp.data.Service
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: Service
) : SafeApiCall {

    suspend fun getResponse() = safeApiCall {
        api.getResponse()
    }

}