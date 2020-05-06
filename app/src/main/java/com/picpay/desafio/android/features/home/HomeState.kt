package com.picpay.desafio.android.features.home

import com.picpay.desafio.android.model.domain.entities.User

sealed class HomeState {
    data class GetUsersSuccess(val users: List<User>): HomeState()
    object GetUsersError: HomeState()
    object GetUsersEmpty: HomeState()
}