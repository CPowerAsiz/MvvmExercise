package cpower.asiz.mvvmexercise.model

import cpower.asiz.mvvmexercise.data.OperationCallback

interface UserDataSource {
    fun retrieveUser(since: Int, perPage: Int, callback: OperationCallback<User>)
    fun cancel()
}