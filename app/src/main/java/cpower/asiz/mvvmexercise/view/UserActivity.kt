package cpower.asiz.mvvmexercise.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cpower.asiz.mvvmexercise.R
import cpower.asiz.mvvmexercise.di.Injection
import cpower.asiz.mvvmexercise.model.User
import cpower.asiz.mvvmexercise.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class UserActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loadUsers(0, 100)
    }

    private fun setupViewModel() {
        userViewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        ).get(UserViewModel::class.java)
        userViewModel.users.observe(this, renderUsers)

        userViewModel.isViewLoading.observe(this, isViewLoadingObserver)
        userViewModel.onMessageError.observe(this, onMessageErrorObserver)
        userViewModel.isEmptyList.observe(this, emptyListObserver)
    }

    private fun setupUI() {
        userAdapter = UserAdapter(userViewModel.users.value ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter
    }

    private val renderUsers = Observer<List<User>> {
        Log.v("Asiz", "data updated $it")
        userAdapter.update(it)
    }

    private val isViewLoadingObserver= Observer<Boolean> {
        val visibility=if(it) View.VISIBLE else View.GONE
        progressBar.visibility= visibility
    }

    private val onMessageErrorObserver= Observer<Any> {
        Log.v("Asiz", "Error $it")
        Toast.makeText(this, "Error $it", Toast.LENGTH_SHORT).show()
    }

    private val emptyListObserver= Observer<Boolean> {
        Log.v("Asiz", "Empty List!")
        Toast.makeText(this, "Empty List!", Toast.LENGTH_LONG).show()
    }
}
