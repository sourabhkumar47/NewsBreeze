package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page (
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Welcome to NewsApp",
        description = "This app provides you with the latest news from around the world.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Get the latest news",
        description = "NewsApp provides you with the latest news from around the world.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Save your favorite news",
        description = "NewsApp allows you to save your favorite news.",
        image = R.drawable.onboarding3
    )
)