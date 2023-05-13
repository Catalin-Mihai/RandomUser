package com.catalin.randomuser.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.catalin.randomuser.data.usecase.interactors.UserInteractor

class MainViewModel(private val userInteractor: UserInteractor) : ViewModel() {
    fun getUsersAsPagingFlow() = userInteractor.getUsersAsPagingFlow()
}