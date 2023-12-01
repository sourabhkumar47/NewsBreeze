package com.loc.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.presentation.common.ArticleCard
import com.loc.newsapp.presentation.common.ArticlesList
import com.loc.newsapp.presentation.common.SearchBar
import com.loc.newsapp.presentation.nvgraph.Route
import com.loc.newsapp.presentation.onboarding.Dimens.MediumPadding0
import com.loc.newsapp.presentation.onboarding.Dimens.MediumPadding1

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(MediumPadding0)
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) }
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        state.articles?.let {
            val article = it.collectAsLazyPagingItems()
            ArticlesList(articles = article, onClick = { navigate(Route.DetailsScreen.route) })
        }

    }

}