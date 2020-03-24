package cpower.asiz.mvvmexercise.model

import android.util.Log
import cpower.asiz.mvvmexercise.data.ApiClient
import cpower.asiz.mvvmexercise.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository : UserDataSource {
    private var call: Call<List<User>>? = null

    override fun retrieveUser(since: Int, perPage: Int, callback: OperationCallback<User>) {
        call = ApiClient.build()?.users(0, 100)
        call?.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                response?.body()?.let {
                    if (response.isSuccessful) {
                        Log.v("Asiz", "data ${it}")
                        callback.onSuccess(it)
                    } else {
                        Log.v("Asiz", "error : ${response?.body()}")
                        callback.onError("error : ${response?.body()}")
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}