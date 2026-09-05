package com.ganesh.spendwise.domain.di

import android.content.Context
import androidx.room.Room
import com.ganesh.spendwise.data.local.dao.ExpenseDao
import com.ganesh.spendwise.data.local.database.SpendWiseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SpendWiseDatabase =
        Room.databaseBuilder(
            context,
            SpendWiseDatabase::class.java,
            "spendwise.db"
        ).build()

    @Provides
    fun provideExpenseDao(
        db: SpendWiseDatabase
    ): ExpenseDao =
        db.expenseDao()
}