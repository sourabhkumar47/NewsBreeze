package com.loc.newsapp.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Source
import com.loc.newsapp.presentation.details.components.DetailsTopBar
import com.loc.newsapp.presentation.onboarding.Dimens.ArticleImageHeight
import com.loc.newsapp.presentation.onboarding.Dimens.MediumPadding0
import com.loc.newsapp.presentation.onboarding.Dimens.MediumPadding1
import com.loc.newsapp.ui.theme.NewsAppTheme
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material.swipeable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current


    //Fun to swipe to dismiss this screen and navigate back to the previous screen
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                navigateUp()
            }
            true
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
        background = { },
        dismissContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                // Your existing code...
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                ) {
                    DetailsTopBar(
                        onBrowsingClick = {
                            Intent(Intent.ACTION_VIEW).also {
                                it.data = Uri.parse(article.url)
                                if (it.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(it)
                                }
                            }
                        },
                        onShareClick = {
                            Intent(Intent.ACTION_SEND).also {
                                it.putExtra(Intent.EXTRA_TEXT, article.url)
                                it.type = "text/plain"
                                if (it.resolveActivity(context.packageManager) != null)
                                    context.startActivity(it)
                            }
                        },
                        onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
                        onBackClick = navigateUp
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MediumPadding0)
                    ) {
                        item {
                            AsyncImage(
                                model = ImageRequest.Builder(context = context)
                                    .data(article.urlToImage)
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(ArticleImageHeight)
                                    .clip(MaterialTheme.shapes.medium),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(MediumPadding1))

//                val customTextSelectionColors = TextSelectionColors(
//                    handleColor = Color.Green,
//                    backgroundColor = Color.Yellow
//                )

//                CompositionLocalProvider(
//                    LocalTextSelectionColors provides
//                            customTextSelectionColors
//                ) {
                            SelectionContainer {
                                Text(
                                    text = article.title,
                                    style = MaterialTheme.typography.displaySmall,
                                    color = colorResource(id = R.color.text_title)
                                )
                            }

                            SelectionContainer {
                                Text(
                                    text = article.content,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colorResource(id = R.color.body)
                                )
//                    }
                            }

                        }
                    }
                }
            }
        }
    )


}

@Preview(showBackground = true)
@Composable
fun DetaitsScreenPreview() {
    NewsAppTheme {
        DetailsScreen(
            article = Article(
                author = "",
                title = "Coinbase says Apple blocked its tast app release on NFTs in Wallet",
                description = "Coinbase says Apple blocked its tast app release on NFTs in Wallet",
                content = "We use cookies and data to Deliver and maintain Google services Track",
                publishedAt = "3252423",
                source = Source(
                    id = "", name = "bbc",
                ),
                url = "http",
                urlToImage = "http"
            ),
            event = {}
        ) {

        }
    }
}