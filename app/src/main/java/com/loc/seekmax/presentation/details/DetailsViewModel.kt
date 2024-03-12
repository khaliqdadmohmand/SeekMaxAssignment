package com.loc.seekmax.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.domain.usecases.jobs.JobsUseCases
import com.loc.seekmax.presentation.bookmark.BookmarkState
import com.loc.seekmax.util.UIComponent
import com.loc.seekmax.presentation.details.DetailsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val jobsUseCases: JobsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = jobsUseCases.getArticle(url = event.article.url)
                    if (article == null){
                        upsertArticle(article = event.article)
                    }else{
                        deleteArticle(article = event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }

            else -> {
                sideEffect
            }
        }
    }

    init {
        getArticles()
    }

    private suspend fun deleteArticle(article: Article) {
        jobsUseCases.deleteArticle(article = article)
        sideEffect = UIComponent.Toast("Job deleted")
    }

    private suspend fun upsertArticle(article: Article) {
        jobsUseCases.upsertArticle(article = article)
        sideEffect = UIComponent.Toast("Job Inserted")
    }

    private fun getArticles() {
        jobsUseCases.getArticles().onEach {
            _state.value = _state.value.copy(articles = it)
        }.launchIn(viewModelScope)
    }

}