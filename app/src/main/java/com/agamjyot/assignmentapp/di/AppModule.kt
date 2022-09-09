package com.agamjyot.assignmentapp.di

import com.agamjyot.assignmentapp.data.RetrofitInstance
import com.agamjyot.assignmentapp.data.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAuthApi(instance: RetrofitInstance): Service {
        return instance.buildApi(Service::class.java)
    }

}