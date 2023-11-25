package com.loc.newsapp.presentation.onboarding

import com.loc.newsapp.domain.manager.usecases.SaveAppEntry

sealed class OnBoardingEvent {
    object SaveAppEntry: OnBoardingEvent()
}