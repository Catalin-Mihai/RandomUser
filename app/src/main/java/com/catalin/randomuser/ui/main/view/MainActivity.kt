package com.catalin.randomuser.ui.main.view

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.catalin.randomuser.R
import com.catalin.randomuser.databinding.ActivityMainBinding
import com.catalin.randomuser.ui.base.ViewModelFactory
import com.catalin.randomuser.ui.main.adapter.PagingUsersAdapter
import com.catalin.randomuser.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { ViewModelFactory() })
    private val adapter by lazy { PagingUsersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        setupAdapter()
        loadServerData()
        listenForLoadingStates()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_actions, menu);
        return true
    }

    private fun setupActionBar() {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    private fun setupAdapter() {
        binding.usersRecyclerView.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context, (layoutManager as LinearLayoutManager).orientation
                )
            )
        }
    }

    private fun loadServerData() {
        lifecycleScope.launch {
            viewModel.getUsersAsPagingFlow().flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                .collect {
                    adapter.submitData(it)
                }
        }
    }

    private fun listenForLoadingStates() {
        lifecycleScope.launch {
            adapter.loadStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED).collect {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        binding.loadingView.isVisible = false
                    }

                    is LoadState.Loading -> {
                        if (adapter.itemCount == 0) {
                            binding.loadingView.isVisible = true
                        }
                    }

                    is LoadState.Error -> {
                        Toast.makeText(
                            this@MainActivity,
                            getText(R.string.initial_loading_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                when (it.append) {
                    is LoadState.NotLoading -> {}
                    LoadState.Loading -> {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.loading_new_page),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is LoadState.Error -> {
                        Toast.makeText(
                            this@MainActivity,
                            ((it.append as LoadState.Error).error).message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}