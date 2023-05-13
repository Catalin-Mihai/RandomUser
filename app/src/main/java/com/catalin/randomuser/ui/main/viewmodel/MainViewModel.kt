package com.catalin.randomuser.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.catalin.randomuser.data.usecase.interactors.UserInteractor

class MainViewModel(private val userInteractor: UserInteractor) : ViewModel() {
    suspend fun getUsers() = userInteractor.getUsers()
}