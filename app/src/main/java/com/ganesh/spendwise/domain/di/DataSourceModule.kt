package com.ganesh.spendwise.domain.di

import android.content.Context
import com.ganesh.spendwise.data.datasource.AndroidFileDataSource
import com.ganesh.spendwise.data.datasource.FileDataSource
import com.ganesh.spendwise.data.datasource.JsonExpenseDataSource
import com.ganesh.spendwise.data.datasource.LocalExpenseDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Provides
    @Singleton
    fun provideJson() : Json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }


    @Provides
    @Singleton
    fun provideFileDataSource(
        @ApplicationContext context: Context
    ): FileDataSource {
        return AndroidFileDataSource(context)
    }

/*    @Provides
    @Singleton
    fun provideJsonExpenseDataSource(
        fileDataSource: FileDataSource, json : Json
    ): JsonExpenseDataSource =
        JsonExpenseDataSource(fileDataSource, json)*/

    @Provides
    @Singleton
    fun provideLocalExpenseDataSource(
        jsonDataSource: FileDataSource,
        json : Json
    ): LocalExpenseDataSource =
        JsonExpenseDataSource(jsonDataSource, json)

}