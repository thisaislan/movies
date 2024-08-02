package thisaislan.movies.di

import androidx.paging.Pager
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import thisaislan.movies.BuildConfig
import thisaislan.movies.constants.ApiClientConts.TMDB_BASE_URL
import thisaislan.movies.constants.MediaRepositoryConts.defaultPagingConfig
import thisaislan.movies.network.models.Movie
import thisaislan.movies.network.models.interfaces.IMovie
import thisaislan.movies.pagingsources.PopularMoviesPagingSource
import thisaislan.movies.repositories.MovieDetailsRepository
import thisaislan.movies.repositories.MovieRepository
import thisaislan.movies.repositories.interfaces.IMovieDetailsRepository
import thisaislan.movies.repositories.interfaces.IMovieRepository
import thisaislan.movies.network.services.interfaces.ITmdbService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideITmdbService(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): ITmdbService {
        return Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(ITmdbService::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory.of(IMovie::class.java, "media_type")
                    .withSubtype(Movie::class.java, "movie")
            )
            .add(KotlinJsonAdapterFactory())
            .build()

        return MoshiConverterFactory.create(moshi);
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            var request = chain.request()

            val url = request.url().newBuilder().addQueryParameter(
                "api_key", BuildConfig.API_KEY
            ).build()

            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }.build()
    }

    @Singleton
    @Provides
    fun providePopularMoviesPagingSource(tmdbService: ITmdbService): PopularMoviesPagingSource {
        return PopularMoviesPagingSource(tmdbService = tmdbService)
    }

    @Singleton
    @Provides
    fun providePopularMoviesPager(popularMoviesPagingSource: PopularMoviesPagingSource): Pager<Int, Movie> {
        return Pager(
            config = defaultPagingConfig,
            pagingSourceFactory = { popularMoviesPagingSource }
        )
    }

    @Singleton
    @Provides
    fun provideIMediaRepository(popularMoviesPager: Pager<Int, Movie>): IMovieRepository {
        return MovieRepository(popularMoviesPager = popularMoviesPager)
    }

    @Singleton
    @Provides
    fun provideIDetailsRepository(tmdbService: ITmdbService): IMovieDetailsRepository {
        return MovieDetailsRepository(tmdbService = tmdbService)
    }
}
