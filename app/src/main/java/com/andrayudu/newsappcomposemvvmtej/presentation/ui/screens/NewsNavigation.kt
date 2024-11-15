package com.andrayudu.newsappcomposemvvmtej.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andrayudu.newsappcomposemvvmtej.R
import com.andrayudu.newsappcomposemvvmtej.presentation.common.NavigationUtil.navigateSingleTopTo
import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.data.util.ValidationUtil.checkIfValidArgNews
import com.google.gson.Gson
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8


@Composable
fun NewsNavigation(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentScreen = bottomNavBarScreens.find { it.route == currentDestination?.route }?: Route.TopNews

    Scaffold(modifier = Modifier,
        topBar = {
            NewsTopBar()
        },
        bottomBar = {
            NewsBottomNavigation(
                currentScreen = currentScreen
            ) {
                navigateSingleTopTo(it.route, navController)
            }
        }
    ) {innerPadding->
        NewsNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }

}

@Composable
private fun NewsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.TopNews.route,
        modifier = modifier
    ) {
        composable(
            route = Route.TopNews.route,
        ) {
            NewsScreen { article ->
                navigateToArticleScreen(article, navController)
            }
        }


        composable(route = Route.SavedNews.route) {
            SavedNewsScreen { article->
                navigateToArticleScreen(article, navController)
            }
        }
        composable(
            route = Route.NewsArticle.route,
            arguments = listOf(navArgument("article") { type = NavType.StringType })
        ) {
            val articleJson = it.arguments?.getString("article")
            val gson = Gson()
            val article = gson.fromJson(articleJson, Article::class.java)
            ArticleScreen(
                article = article
            )
        }
    }
}


private fun navigateToArticleScreen(
    it: Article,
    navController: NavHostController
) {
    val articleJsonString = Gson().toJson(it)
    //encodes the article which is in json format to string format for passing it as an argument while navigating.
    val encodedArticle = URLEncoder.encode(articleJsonString, UTF_8.name())
    navController.navigate(Route.NewsArticle.routeWithoutArgs + "/${encodedArticle}") {
        launchSingleTop = true
    }
}

    @Composable
    fun NewsBottomNavigation(
        currentScreen: Route,
        onIconSelected: (Route) -> Unit
    ) {
        NavigationBar {
            bottomNavBarScreens.forEach { screen ->
                NavigationBarItem(
                    selected = screen == currentScreen,
                    label = {
                        Text(text = stringResource(id = screen.resourceId))
                    },
                    icon = {
                        Icon(painter = painterResource(id = screen.icon), null)
                    },
                    onClick = {
                        onIconSelected.invoke(screen)
                    }
                )
            }
        }
    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(id = R.string.app_name))
        },
    )
}