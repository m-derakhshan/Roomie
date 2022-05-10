package m.derakhshan.roomie.feature_wish_list.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.derakhshan.roomie.core.data.data_source.ApplicationDatabase
import m.derakhshan.roomie.feature_wish_list.data.repository.WishRepositoryImpl
import m.derakhshan.roomie.feature_wish_list.domain.repository.WishRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WishModule {

    @Provides
    @Singleton
    fun provideWishesRepository(database: ApplicationDatabase): WishRepository {
        return WishRepositoryImpl(database = database)
    }


}