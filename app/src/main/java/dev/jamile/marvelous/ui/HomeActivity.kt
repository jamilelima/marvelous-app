package dev.jamile.marvelous.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.jamile.marvelous.R
import dev.jamile.marvelous.base.StatusViewModel
import dev.jamile.marvelous.base.ViewState
import dev.jamile.marvelous.data.model.HeroResult
import dev.jamile.marvelous.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getHeroes()
        initObservers()
    }

    private fun initObservers() {
        viewModel.getHeroesList().observe(this, Observer { viewState ->
            when (viewState.status) {
                StatusViewModel.LOADING -> setLoading()
                StatusViewModel.SUCCESS -> setRecyclerViewList(viewState)
                StatusViewModel.ERROR -> setError(viewState.error)
                StatusViewModel.EMPTY -> setEmptyList()
            }
        })
    }

    private fun setLoading() {
        progressBar.visibility = VISIBLE
    }

    private fun setRecyclerViewList(viewState: ViewState<List<HeroResult>, StatusViewModel>) {
        progressBar.visibility = GONE
        takeIf { !viewState.data.isNullOrEmpty() }.let {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = HeroesListAdapter(this@HomeActivity, heroes = viewState.data!!)
        }
    }

    private fun setError(error: Throwable?) {
        Toast.makeText(this, error?.message ?: "Erro desconhecido", Toast.LENGTH_SHORT).show()
        progressBar.visibility = GONE
    }

    private fun setEmptyList() {
        // setup empty list
    }
}