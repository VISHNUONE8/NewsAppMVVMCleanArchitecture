package com.andrayudu.newsappcomposemvvmtej.presentation.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.data.util.NoInternetException
import com.andrayudu.newsappcomposemvvmtej.data.util.ResultState
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.DeleteSavedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetNewsHeadlinesUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetSavedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetSearchedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetUSNewsHeadlinesUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.SaveNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.presentation.common.networkhelper.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getUSNewsHeadlinesUseCase: GetUSNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {




    private val tag = "NewsViewModel"

    private val _newsItem = MutableStateFlow<ResultState<ApiResponse>>(ResultState.Loading())
    val newsItem: StateFlow<ResultState<ApiResponse>> = _newsItem


    private val _searchQuery = mutableStateOf("")
    val searchQuery : State<String> = _searchQuery



    init {
//        getNewsHeadlines("us",1)
        getUsNewsHeadlines()
    }


    // on refreshing with pull refresh bar , we will display usHeadlines so clearing the search query.
    fun isRefreshing(){
        _searchQuery.value = ""
    }

    fun setSearchQuery(enteredQuery:String){
        _searchQuery.value = enteredQuery
        getSearchedNews(enteredQuery,"us",1)
    }
    fun getNewsHeadlines(country:String,page:Int)= viewModelScope.launch(Dispatchers.IO){


            _newsItem.value = ( ResultState.Loading())


        try{

                val apiResult = getNewsHeadlinesUseCase.execute(country,page)
                _newsItem.value = apiResult
        }catch (e:Exception){
                _newsItem.value = ResultState.Error(message = e.message.toString())


        }

    }

    fun getUsNewsHeadlines() = viewModelScope.launch{
        if(!networkHelper.isNetworkConnected()){
            _newsItem.value = ResultState.Error(
                throwable = NoInternetException(),
                message = "Please Check Your Internet Connection"
            )
            return@launch
        }
        _newsItem.value =  ResultState.Loading()


        try{

            val apiResult = getUSNewsHeadlinesUseCase.execute()

            _newsItem.value = apiResult

        }catch (e:Exception){
                _newsItem.value = ResultState.Error(message = e.message.toString())

        }

    }


    private fun getSearchedNews(searchQuery:String, country:String, page:Int)= viewModelScope.launch{
        _newsItem.value = (ResultState.Loading())
        try{

                val apiResult = getSearchedNewsUseCase.execute(searchQuery,country,page)

                _newsItem.value = apiResult

        }catch (e:Exception){
            _newsItem.value = ResultState.Error(message = e.message.toString())

        }

    }

    fun saveArticle(article: Article) = viewModelScope.launch { saveNewsUseCase.execute(article) }

    fun getSavedNews() = getSavedNewsUseCase.execute()

    fun deleteSaveNews(article: Article) = viewModelScope.launch { deleteSavedNewsUseCase.execute(article) }

}