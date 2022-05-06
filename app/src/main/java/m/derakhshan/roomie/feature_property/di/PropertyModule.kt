package m.derakhshan.roomie.feature_property.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.derakhshan.roomie.core.data.data_source.ApplicationDatabase
import m.derakhshan.roomie.feature_property.data.data_source.repository.PropertyRepositoryImpl
import m.derakhshan.roomie.feature_property.domain.repository.PropertyRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PropertyModule {

    @Provides
    @Singleton
    fun providePropertyRepository(database: ApplicationDatabase): PropertyRepository {
        return PropertyRepositoryImpl(database = database.propertyDao)
    }
}