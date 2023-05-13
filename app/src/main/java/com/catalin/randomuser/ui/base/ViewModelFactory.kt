package com.catalin.randomuser.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.catalin.randomuser.data.network.service.RetrofitBuilder
import com.catalin.randomuser.data.repository.repos.UserRepository
import com.catalin.randomuser.data.usecase.interactors.UserInteractor
import com.catalin.randomuser.ui.main.viewmodel.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(UserInteractor(UserRepository(RetrofitBuilder.apiService))) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

