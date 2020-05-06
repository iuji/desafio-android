package com.picpay.desafio.android.features.home

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.model.domain.entities.User
import com.picpay.desafio.android.model.domain.usecases.HomeUseCase
import io.reactivex.Single

class HomeViewModelArrange {
    fun mockUserListUseCaseResponseSuccess(useCase: HomeUseCase, response: List<User>) {
        whenever(useCase.getUsers(any()))
            .thenReturn(Single.just(response))
    }

    fun mockUserListUseCaseResponseEmpty(useCase: HomeUseCase) {
        whenever(useCase.getUsers(any()))
            .thenReturn(Single.just(emptyList()))
    }

    fun mockUserListUseCaseResponseError(useCase: HomeUseCase) {
        whenever(useCase.getUsers(any()))
            .thenReturn(Single.error(Throwable("Error response")))
    }
}

class HomeViewModelAct {
    fun fetchUsers(viewModel: HomeViewModel, receiver: Observer<HomeState>) {
        viewModel.apply {
            fetchUsers(true)
            usersLiveData.observeForever(receiver)
        }
    }
}

class HomeViewModelAssert {
    fun isSuccessState(viewModel: HomeViewModel) {
        viewModel.usersLiveData.value.assertInstanceOf(HomeState.GetUsersSuccess::class.java)
    }

    fun isErrorState(viewModel: HomeViewModel) {
        viewModel.usersLiveData.value.assertInstanceOf(HomeState.GetUsersError::class.java)
    }

    fun isEmptyState(viewModel: HomeViewModel) {
        viewModel.usersLiveData.value.assertInstanceOf(HomeState.GetUsersEmpty::class.java)
    }
}