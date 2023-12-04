package com.loc.newsapp.presentation.details

import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.usecases.news.DeleteArticle

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}