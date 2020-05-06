package com.picpay.desafio.android.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.model.domain.entities.User
import com.picpay.desafio.android.model.domain.usecases.HomeUseCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {
    private val useCase by lazy { mock<HomeUseCase>() }
    private val viewModel by lazy { HomeViewModel(useCase) }
    private val receiver by lazy { mock<Observer<HomeState>>() }

    private val userListResponse = listOf(
        User(
            id = 12,
            username = "@iujimatsumoto",
            img = "https://scontent.fcgh7-1.fna.fbcdn.net/v/t1.0-9/96413401_2854334131309474_3749670615942955008_o.jpg?_nc_cat=110&_nc_sid=730e14&_nc_ohc=fZgmmXlb-ZoAX-Xs56o&_nc_ht=scontent.fcgh7-1.fna&oh=a4f3eedb961f9c0c6bd7be3502ab4036&oe=5ED46348",
            name = "Iuji Matsumoto"
        )
    )

    @get:Rule
    val archRule = InstantTaskExecutorRule()

    @get:Rule
    val schedulerRule =  RxSchedulerRule()

    private fun arrange(func: HomeViewModelArrange.() -> Unit) =
        HomeViewModelArrange().apply { func() }

    private fun act(func: HomeViewModelAct.() -> Unit) =
        HomeViewModelAct().apply { func() }

    private fun assert(func: HomeViewModelAssert.() -> Unit) =
        HomeViewModelAssert().apply { func() }

    @Test
    fun `when fetch users should return success` () {
        arrange {
            mockUserListUseCaseResponseSuccess(useCase, userListResponse)
        }

        act {
            fetchUsers(viewModel, receiver)
        }

        assert {
            isSuccessState(viewModel)
        }
    }

    @Test
    fun `when fetch users should return error` () {
        arrange {
            mockUserListUseCaseResponseError(useCase)
        }

        act {
            fetchUsers(viewModel, receiver)
        }

        assert {
            isErrorState(viewModel)
        }
    }

    @Test
    fun `when fetch users should return empty` () {
        arrange {
            mockUserListUseCaseResponseEmpty(useCase)
        }

        act {
            fetchUsers(viewModel, receiver)
        }

        assert {
            isEmptyState(viewModel)
        }
    }
}