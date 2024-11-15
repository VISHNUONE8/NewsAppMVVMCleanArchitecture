package com.andrayudu.newsappcomposemvvmtej.presentation.ui.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrayudu.newsappcomposemvvmtej.R
import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.ShowError
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.viewmodel.NewsViewModel

@Composable
fun SavedNewsScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
    newsClicked:(Article)->Unit

) {
    val context = LocalContext.current
    val savedNewsList : List<Article> by newsViewModel.getSavedNews().collectAsStateWithLifecycle(initialValue = emptyList())

    if (savedNewsList.isEmpty()){
        ShowError(text = stringResource(R.string.no_saved_news))

    }
    else{
        NewsLayoutWithDelete(newsList = savedNewsList,
            articleClicked = {
                newsClicked(it)
            },
            deleteArticle = {
                newsViewModel.deleteSaveNews(it)
                Toast.makeText(context," Article Removed from the saved list.",Toast.LENGTH_SHORT).show()
            })
    }

}
