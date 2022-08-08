package ru.gbpractice.gitapptest.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gbpractice.gitapptest.App.Companion.BUNDLE_KEY
import ru.gbpractice.gitapptest.R
import ru.gbpractice.gitapptest.app
import ru.gbpractice.gitapptest.data.retrofit.entitiesDTO.UserEntityDTO
import ru.gbpractice.gitapptest.databinding.ActivityMainBinding
import ru.gbpractice.gitapptest.domain.entities.UserEntity
import ru.gbpractice.gitapptest.ui.details.UserDetailsActivity

class MainActivity : AppCompatActivity(), UsersContract.View {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        presenter = retainPresenter()
        presenter.attach(this)

    }

    private fun retainPresenter(): UsersContract.Presenter {
        return lastCustomNonConfigurationInstance as? UsersContract.Presenter
            ?: UsersPresenter(app.userListRepo)
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun initViews() {
        showLoading(false)
        initRefreshButton()
        initRecyclerView()
    }

    private fun initRefreshButton() {
        binding.buttonRefreshUserList.setOnClickListener {
            presenter.onRefreshUserList()
        }
    }

    private fun initRecyclerView() {
        binding.usersListRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setOnUserClickListener(object : UsersAdapter.OnUserClickListener {
            override fun onUserItemClick(userEntity: UserEntity) {
                presenter.onSelectUser(userEntity)
            }
        })
        binding.usersListRecyclerView.adapter = adapter
    }


    override fun showUsersList(users: List<UserEntity>) {
        adapter.setData(users)
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.usersListRecyclerView.isVisible = !isLoading
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }

    override fun showUserDetails(userEntity: UserEntity) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(BUNDLE_KEY, UserEntityDTO.fromUserEntity(userEntity))
        startActivity(intent)
    }
}