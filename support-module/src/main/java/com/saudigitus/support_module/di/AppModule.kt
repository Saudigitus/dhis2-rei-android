package com.saudigitus.support_module.di

import android.app.Application
import androidx.room.Room
import com.saudigitus.support_module.data.local.ManualsRepository
import com.saudigitus.support_module.data.local.database.AppDatabase
import com.saudigitus.support_module.data.local.repository.ManualsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.hisp.dhis.android.core.D2
import org.hisp.dhis.android.core.D2Manager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun manualsDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).build()

    /**
     * Inject ManualsRepository
     */

    @Provides
    @Singleton fun providesManualRepository(appDatabase: AppDatabase, d2: D2): ManualsRepository {
        return ManualsRepositoryImp(appDatabase.manualsDAO(), d2 = d2)
    }

}