package com.waleed.graphqltestapp.data

import com.apollographql.apollo3.ApolloClient
import com.waleed.graphqltestapp.CountriesQuery
import com.waleed.graphqltestapp.CountryQuery
import com.waleed.graphqltestapp.domain.CountryClient
import com.waleed.graphqltestapp.domain.DetailedCountry
import com.waleed.graphqltestapp.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?.sortedBy { it.name }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}