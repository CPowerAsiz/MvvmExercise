package cpower.asiz.mvvmexercise.di

import androidx.lifecycle.ViewModelProvider
import cpower.asiz.mvvmexercise.model.UserDataSource
import cpower.asiz.mvvmexercise.model.UserRepository
import cpower.asiz.mvvmexercise.viewmodel.ViewModelFactory

object Injection {

    private val userDataSource: UserDataSource = UserRepository()
    private val userViewModelFactory = ViewModelFactory(userDataSource)

    fun providerRepository(): UserDataSource {
        return userDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return userViewModelFactory
    }
}