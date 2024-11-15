package com.andrayudu.newsappcomposemvvmtej.presentation.viewmodel

import android.util.MainDispatcherRule
import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.data.util.ResultState
import com.andrayudu.newsappcomposemvvmtej.domain.repository.NewsRepository
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.DeleteSavedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetNewsHeadlinesUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetSavedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetSearchedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetUSNewsHeadlinesUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.SaveNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.presentation.common.networkhelper.TestNetworkHelper
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.viewmodel.NewsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


//In this class, using mockito Annotation but in NewsApiServiceTest we no annotations used
@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @Mock
    private lateinit var newsRepository: NewsRepository


    private lateinit var newsViewModel: NewsViewModel

    private lateinit var getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
    private lateinit var getUSNewsHeadlinesUseCase: GetUSNewsHeadlinesUseCase
    private lateinit var getSearchedNewsUseCase: GetSearchedNewsUseCase
    private lateinit var saveNewsUseCase: SaveNewsUseCase
    private lateinit var getSavedNewsUseCase: GetSavedNewsUseCase
    private lateinit var deleteSavedNewsUseCase: DeleteSavedNewsUseCase
    private lateinit var networkHelper: TestNetworkHelper


    @Before
    fun setUp() {

        // initializes the class marked with @Mock Annotation.
        MockitoAnnotations.openMocks(this)
        getNewsHeadlinesUseCase = GetNewsHeadlinesUseCase(newsRepository)
        getUSNewsHeadlinesUseCase = GetUSNewsHeadlinesUseCase(newsRepository)
        getSearchedNewsUseCase = GetSearchedNewsUseCase(newsRepository)
        saveNewsUseCase = SaveNewsUseCase(newsRepository)
        getSavedNewsUseCase = GetSavedNewsUseCase(newsRepository)
        deleteSavedNewsUseCase = DeleteSavedNewsUseCase(newsRepository)
        networkHelper = TestNetworkHelper()

        newsViewModel = NewsViewModel(
            getNewsHeadlinesUseCase,
            getUSNewsHeadlinesUseCase,
            getSearchedNewsUseCase,
            saveNewsUseCase,
            getSavedNewsUseCase,
            deleteSavedNewsUseCase,
            networkHelper
        )

    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()



    @Test
    fun getUsNewsHeadlines_whenResponseSuccess_isReturningSuccess() {
        runTest {

            `when`(newsRepository.getUSNewsHeadlines())
                .thenReturn(ResultState.Success(ApiResponse(
                articles = emptyList<Article>(),
                status = "", totalResults = 1
            )))
            newsViewModel.getUsNewsHeadlines()

            val result = ApiResponse(
                articles = emptyList<Article>(),
                status = "", totalResults = 1
            )
            assertEquals(result,newsViewModel.newsItem.first().data)

        }
    }
    @After
    fun tearDown(){
        Dispatchers.shutdown()
    }
}