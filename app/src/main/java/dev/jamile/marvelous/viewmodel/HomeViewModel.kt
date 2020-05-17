package dev.jamile.marvelous.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.jamile.marvelous.base.BaseViewModel
import dev.jamile.marvelous.base.DispatcherProvider
import dev.jamile.marvelous.base.StatusViewModel
import dev.jamile.marvelous.base.ViewState
import dev.jamile.marvelous.data.model.HeroResult
import dev.jamile.marvelous.network.Result
import dev.jamile.marvelous.repository.HomeRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val repository: HomeRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val heroLiveData = MutableLiveData<ViewState<List<HeroResult>, StatusViewModel>>()

    fun getHeroesList(): LiveData<ViewState<List<HeroResult>, StatusViewModel>> = heroLiveData

    fun getHeroes() {
        heroLiveData.postValue(ViewState(status = StatusViewModel.LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getHeroes()
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data.data.results.isNullOrEmpty()) {
                            heroLiveData.postValue(
                                ViewState(
                                    response.data.data.results,
                                    StatusViewModel.SUCCESS
                                )
                            )
                        } else {
                            heroLiveData.postValue(
                                ViewState(
                                    response.data.data.results,
                                    StatusViewModel.EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        heroLiveData.postValue(
                            ViewState(
                                null,
                                StatusViewModel.ERROR,
                                error = response.throwable
                            )
                        )
                    }
                }
            }
        }
    }
}