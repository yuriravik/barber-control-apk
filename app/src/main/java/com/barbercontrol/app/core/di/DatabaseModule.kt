package com.barbercontrol.app.core.di

import android.content.Context
import androidx.room.Room
import com.barbercontrol.app.core.util.Constants
import com.barbercontrol.app.data.local.AppDatabase
import com.barbercontrol.app.data.local.dao.AppointmentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo Hilt responsável por fornecer as dependências do banco de dados Room.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideAppointmentDao(database: AppDatabase): AppointmentDao =
        database.appointmentDao()
}
