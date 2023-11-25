package com.loc.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow


interface LocalUserManager {
    //in this interface we will save the user entry when user click on GET STARTED button
    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}