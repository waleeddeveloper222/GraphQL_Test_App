package com.waleed.graphqltestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.waleed.graphqltestapp.presentation.CountriesScreen
import com.waleed.graphqltestapp.presentation.CountriesViewModule
import com.waleed.graphqltestapp.ui.theme.GraphQLTestAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphQLTestAppTheme {

                val viewModule = hiltViewModel<CountriesViewModule>()
                val state by viewModule.state.collectAsState()
                CountriesScreen(
                    state = state,
                    onSelectCountry = viewModule::selectCountry,
                    onDismissCountryDialog =viewModule::dismissCountryDialog
                )

            }
        }
    }
}
