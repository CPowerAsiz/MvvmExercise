package cpower.asiz.mvvmexercise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cpower.asiz.mvvmexercise.model.UserDataSource

class ViewModelFactory(private val repository: UserDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}