package m.derakhshan.roomie.feature_filter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.derakhshan.roomie.core.data.data_source.ApplicationDatabase
import m.derakhshan.roomie.feature_filter.data.repository.FilterRepositoryImpl
import m.derakhshan.roomie.feature_filter.domain.repository.FilterRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FilterModule {


    @Provides
    @Singleton
    fun provideFilterRepository(database: ApplicationDatabase): FilterRepository {
        return FilterRepositoryImpl(database.filterDao)
    }
}