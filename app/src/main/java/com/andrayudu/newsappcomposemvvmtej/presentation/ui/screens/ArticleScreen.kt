package com.andrayudu.newsappcomposemvvmtej.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.andrayudu.newsappcomposemvvmtej.R
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.ShowError
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.WebViewPage
import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.viewmodel.NewsViewModel


@Composable
fun ArticleScreen(
    article: Article?,
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current

    Scaffold(
        floatingActionButton = {

            FloatingActionButton(onClick = {
                if (article != null) {
                    newsViewModel.saveArticle(article)
                }
                Toast.makeText(
                    mContext,
                    mContext.resources.getString(R.string.article_saved_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Icon(painter = painterResource(id = R.drawable.baseline_save), contentDescription = null)
            }
        }
    ) {
        if (article?.url == null) {
            ShowError(text = stringResource(id = R.string.something_went_wrong))
        } else {
            WebViewPage(
                url = article.url,
                modifier = Modifier.padding(it)
            )
        }
    }
}