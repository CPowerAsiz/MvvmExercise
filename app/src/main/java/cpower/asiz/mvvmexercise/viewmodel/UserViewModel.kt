package cpower.asiz.mvvmexercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cpower.asiz.mvvmexercise.data.OperationCallback
import cpower.asiz.mvvmexercise.model.User
import cpower.asiz.mvvmexercise.model.UserDataSource

class UserViewModel(private val repository: UserDataSource): ViewModel() {
    private val _users = MutableLiveData<List<User>>().apply { value = emptyList() }
    val users: LiveData<List<User>> = _users

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun loadUsers(since: Int, perPage: Int) {
        _isViewLoading.postValue(true)
        repository.retrieveUser(since, perPage, object : OperationCallback<User> {
            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error)
            }

            override fun onSuccess(data: List<User>?) {
                _isViewLoading.postValue(false)

                if (data != null) {
                    if (data.isEmpty()) {
                        _isEmptyList.postValue(true)
                    } else {
                        _users.value = data
                    }
                }
            }
        })
    }
}