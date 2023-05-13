package com.catalin.randomuser.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.catalin.randomuser.R
import com.catalin.randomuser.data.repository.utils.Result
import com.catalin.randomuser.databinding.ActivityMainBinding
import com.catalin.randomuser.ui.base.ViewModelFactory
import com.catalin.randomuser.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { ViewModelFactory() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        loadServerData()
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

    private fun loadServerData() {
        lifecycleScope.launch {
            viewModel.getUsers()
                .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                .collect {
                    when (it) {
                        Result.Loading -> {
                            Log.e(MainActivity::class.simpleName, "Loading...")
                        }

                        is Result.Success -> {
                            Log.e(MainActivity::class.simpleName, "Success: $it")
                        }

                        is Result.Error -> {
                            Log.e(
                                MainActivity::class.simpleName,
                                "Error: ${it.error?.message ?: ""}"
                            )
                        }
                    }
                }
        }
    }
}