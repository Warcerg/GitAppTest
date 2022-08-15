package ru.gbpractice.gitapptest

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import ru.gbpractice.gitapptest.domain.entities.UserEntity
import ru.gbpractice.gitapptest.domain.repo.UserListRepo
import ru.gbpractice.gitapptest.ui.main.UsersContract
import ru.gbpractice.gitapptest.ui.main.UsersPresenter

class UsersPresenterTest {
    private lateinit var presenter: UsersPresenter

    @Mock
    private lateinit var repository: UserListRepo

    @Mock
    private lateinit var view: UsersContract.View

    @Mock
    private lateinit var userEntity: UserEntity

    @Mock
    private lateinit var usersList: List<UserEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UsersPresenter(repository)
        presenter.initPresenterData(view,usersList)
    }

    @Test
    fun attachIsLoading_test(){
        presenter.attach(view)
        verify(view).showLoading(false)
    }

    @Test
    fun attachShowUserList_test(){
        presenter.attach(view)
        verify(usersList).let { view.showUsersList(it) }
    }

    @Test
    fun detachView_test(){
        presenter.detach()
        assertEquals(null, presenter.getPView() )
    }

    @Test
    fun onSelectUser_test(){
        presenter.onSelectUser(userEntity)
        verify(view)?.showUserDetails(userEntity)
    }

    @Test
    fun onRefreshUserListIsLoading_test(){
        presenter.onRefreshUserList()
        verify(view,times(1))?.showLoading(true)
    }

    @Test
    fun onRefreshUserListOnSuccess_test(){
        presenter.onRefreshUserList()
        verify(repository).getUserList(
            onSuccess = {
                view.showLoading(false)
                usersList = it
                view.showUsersList(it)
            },
            onError = {
                view.showLoading(false)
                view.showError(it)
            }
        )
    }
}