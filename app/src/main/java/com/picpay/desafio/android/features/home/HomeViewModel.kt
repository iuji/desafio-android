package com.picpay.desafio.android.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.model.domain.usecases.HomeUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {

    private val _usersLiveData = MutableLiveData<HomeState>()
    private val disposables = CompositeDisposable()

    val usersLiveData: LiveData<HomeState> get() = _usersLiveData

    fun fetchUsers(forceUpdate: Boolean): Disposable =
        useCase.getUsers(forceUpdate)
            .subscribe (
                {
                    if (it.isNotEmpty()) _usersLiveData.value = HomeState.GetUsersSuccess(it) else _usersLiveData.value = HomeState.GetUsersEmpty
                },
                {
                    _usersLiveData.value = HomeState.GetUsersError
                }
            )
            .also { disposables.add(it) }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}