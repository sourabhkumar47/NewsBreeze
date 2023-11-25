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
        description = "Your go-to source for breaking news, personalized content, and in-depth stories",
        image = R.drawable.onboarding6
    ),
    Page(
        title = "Your News, Your Way",
        description = " Select your favorite topics and sources to create a personalized news " +
                "feed that reflects your interests and preferences.",
        image = R.drawable.onboarding7
    ),
    Page(
        title = "Stay Ahead with Instant Updates",
        description = "Enable push notifications to receive breaking news alerts in real-time.",
        image = R.drawable.onboarding8_
    )
)