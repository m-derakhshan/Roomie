package m.derakhshan.roomie.feature_home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.derakhshan.roomie.core.data.data_source.ApplicationDatabase
import m.derakhshan.roomie.core.utils.AppConstants
import m.derakhshan.roomie.feature_home.data.data_source.HomeAPI
import m.derakhshan.roomie.feature_home.data.repository.HomeRepositoryImpl
import m.derakhshan.roomie.feature_home.domain.repository.HomeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(db: ApplicationDatabase, homeAPI: HomeAPI): HomeRepository {
        return HomeRepositoryImpl(database = db, homeAPI = homeAPI)
    }

    @Provides
    @Singleton
    fun provideHomeAPI(): HomeAPI {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeAPI::class.java)
    }
}