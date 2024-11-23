package com.waleed.graphqltestapp.di


import com.apollographql.apollo3.ApolloClient
import com.waleed.graphqltestapp.data.ApolloCountryClient
import com.waleed.graphqltestapp.domain.CountryClient
import com.waleed.graphqltestapp.domain.DetailedCountry
import com.waleed.graphqltestapp.domain.SimpleCountry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
   suspend fun provideGetCountriesUseCase(countryClient: CountryClient): List<SimpleCountry> {
        return countryClient.getCountries()
    }

    @Provides
    @Singleton
    suspend fun provideGetCountryUseCase(countryClient: CountryClient , code :String): DetailedCountry? {
        return countryClient.getCountry(code)
    }
}