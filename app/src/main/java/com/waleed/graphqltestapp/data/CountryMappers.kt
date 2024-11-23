package com.waleed.graphqltestapp.data

import com.waleed.graphqltestapp.CountriesQuery
import com.waleed.graphqltestapp.CountryQuery
import com.waleed.graphqltestapp.domain.DetailedCountry
import com.waleed.graphqltestapp.domain.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
    )
}