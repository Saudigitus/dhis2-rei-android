package org.saudigitus.rei.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.hisp.dhis.android.core.D2
import org.saudigitus.rei.utils.SearchTeiStyle
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class ReiModule {
    @Provides
    @Singleton
    open fun providesSearchTeiStyle(d2: D2) = SearchTeiStyle(d2)
}
