package com.ganesh.spendwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ganesh.spendwise.core.navigation.AppContent
import com.ganesh.spendwise.core.navigation.SpendWiseNavHost
import com.ganesh.spendwise.data.datasource.AndroidFileDataSource
import com.ganesh.spendwise.data.datasource.JsonExpenseDataSource
import com.ganesh.spendwise.data.repository.ExpenseRepositoryImpl
import com.ganesh.spendwise.feature.dashboard.ui.DashboardRoute
import com.ganesh.spendwise.feature.dashboard.viewmodel.DashboardViewModel
import com.ganesh.spendwise.ui.theme.SpendWiseTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fileDataSource = AndroidFileDataSource(this)

        val jsonDataSource = JsonExpenseDataSource(
            fileDataSource = fileDataSource,
            json = Json {
                ignoreUnknownKeys = true
            }
        )

       // val repository = ExpenseRepositoryImpl(jsonDataSource)






        enableEdgeToEdge()
        setContent {
            SpendWiseTheme {

                /*val viewModel = remember {
                    DashboardViewModel(repository)

                }

                DashboardRoute(viewModel = viewModel )*/

               // SpendWiseNavHost()


                val navController = rememberNavController()

                AppContent(
                    navController = navController
                )

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpendWiseTheme {
        Greeting("Android")
    }
}