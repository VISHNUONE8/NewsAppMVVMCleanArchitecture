package com.andrayudu.newsappcomposemvvmtej.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrayudu.newsappcomposemvvmtej.R
import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.data.util.NoInternetException
import com.andrayudu.newsappcomposemvvmtej.data.util.ResultState
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.ShowError
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.ShowLoading
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    newsClicked:(Article)->Unit
) {


    val newsUIState  by newsViewModel.newsItem.collectAsStateWithLifecycle()

    val searchQuery = newsViewModel.searchQuery


    var isRefreshing by rememberSaveable { mutableStateOf(false) }

    val pullToRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
//          newsViewModel.getNewsHeadlines("us",1)
            newsViewModel.getUsNewsHeadlines()
            newsViewModel.isRefreshing()
        }
    )
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { enteredText ->
                newsViewModel.setSearchQuery(enteredText)
            },
            label = { Text("SearchQuery") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "searchQuery") },
            enabled = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text
            ),

            )


        Box(
            modifier = Modifier.fillMaxSize()
                .pullRefresh(pullToRefreshState)
        ) {

            when (newsUIState) {


                is ResultState.Loading -> {
                    Log.i("NewsScreen", "the news is loading...")
                    // if the news is  loading i.e usually on opening the app.
                    if (!isRefreshing) {
                        ShowLoading()
                    }
                }

                is ResultState.Error -> {
                    isRefreshing = false
                    var errorText = stringResource(id = R.string.something_went_wrong)
                    if ((newsUIState as ResultState.Error<ApiResponse>).throwable is NoInternetException) {
                        errorText = stringResource(id = R.string.no_internet_available)
                    }
                    ShowError(
                        text = errorText,
                        retryEnabled = true
                    ) {
                        newsViewModel.getUsNewsHeadlines()
                    }
                }

                is ResultState.Success -> {
                    isRefreshing = false
                    if ((newsUIState as ResultState.Success<ApiResponse>).data?.articles == null) {
                        ShowError(text = stringResource(R.string.no_data_available))
                    } else {

                            NewsLayout(newsList = (newsUIState as ResultState.Success<ApiResponse>).data!!.articles) { article ->
                                newsClicked(article)
                            }
                        }

                    }

                }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullToRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )


            }



        }

    }


@Composable
fun NewsLayout(
    newsList:List<Article>,
    articleClicked:(Article)->Unit
){
    LazyColumn(modifier = Modifier,
        contentPadding = PaddingValues(2.dp)
    ) {
        itemsIndexed(
            items = newsList,
            key = {index,item-> (index)}
        ){index,item->
            //only display the articles that have a proper title.
            if(item.title!="[Removed]"){
                Article(item) {
                    articleClicked(item)
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsLayoutWithDelete(
    newsList: List<Article>,
    articleClicked: (Article) -> Unit,
    deleteArticle: (Article) -> Unit
) {
    LazyColumn {
        items(newsList, key = { article -> article.url!! }) { article ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = {value->
                   if(value == SwipeToDismissBoxValue.EndToStart || value == SwipeToDismissBoxValue.StartToEnd ){
                       deleteArticle(article)
                       true
                   }
                    else{
                        false
                   }
                }
            )

            setOf(SwipeToDismissBoxValue.EndToStart,
                SwipeToDismissBoxValue.StartToEnd
            )
            SwipeToDismissBox(state = dismissState,
                backgroundContent =  {
                },
                enableDismissFromStartToEnd = true,
                enableDismissFromEndToStart = true,
                content = {
                    Article(article) {
                        articleClicked(it)
                    }
                })

        }
    }
}
