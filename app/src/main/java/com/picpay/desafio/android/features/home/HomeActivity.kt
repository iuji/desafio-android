package com.picpay.desafio.android.features.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.extensions.nonNullObserve
import com.picpay.desafio.android.model.domain.entities.User
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private val mAdapter = UserListAdapter()
    private var mUsers: List<User> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        checkState()
    }

    private fun initViews() {
        setupRecyclerView()
        observeUsers()
        setRefreshButtonListener()
    }

    private fun checkState() {
        if(viewModel.usersLiveData.value == null) {
            getCachedUsers()
        }
    }

    private fun getCachedUsers() {
        showLoading()
        viewModel.fetchUsers(NO_UPDATE)
    }

    private fun setRefreshButtonListener() {
        refreshButton.setOnClickListener {
            showLoading()
            viewModel.fetchUsers(FORCE_UPDATE)
        }
    }

    private fun setupRecyclerView() {
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = mAdapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
    }

    private fun observeUsers() {
        viewModel.usersLiveData.nonNullObserve(this) { state ->
            when (state) {
                is HomeState.GetUsersSuccess -> onGetUsersSuccess(state.users)
                is HomeState.GetUsersError -> onGetUsersError()
                is HomeState.GetUsersEmpty -> onGetUsersEmpty()
            }
        }
    }

    private fun onGetUsersEmpty() {
        hideLoading()
        val message = getString(R.string.empty_error)
        Toast.makeText(this@HomeActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun onGetUsersError() {
        hideLoading()
        val message = getString(R.string.error)
        Toast.makeText(this@HomeActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun onGetUsersSuccess(users: List<User>) {
        mUsers = users
        hideLoading()
        mAdapter.setUsers(users)
    }

    private fun showLoading() {
        pbUserList?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pbUserList?.visibility = View.GONE
    }

    companion object{
        private const val NO_UPDATE = false
        private const val FORCE_UPDATE = true
    }
}