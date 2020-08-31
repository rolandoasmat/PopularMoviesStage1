package com.asmat.rolando.nightwing.ui.watchlatermovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.asmat.rolando.nightwing.model.mappers.DataModelMapper
import com.asmat.rolando.nightwing.model.mappers.UiModelMapper
import com.asmat.rolando.nightwing.repositories.MoviesRepository
import com.asmat.rolando.nightwing.ui.moviegrid.BaseMovieGridViewModel
import com.asmat.rolando.nightwing.ui.moviegrid.MovieGridItemUiModel

class WatchLaterViewModel(moviesRepository: MoviesRepository,
                          uiModelMapper: UiModelMapper,
                          private val dataModelMapper: DataModelMapper) : BaseMovieGridViewModel(moviesRepository, uiModelMapper) {


    override val uiModels: LiveData<List<MovieGridItemUiModel>>
        get() = Transformations.map(moviesRepository.getAllWatchLaterMovies()) {
            dataModelMapper.mapWatchLaterMovies(it)
        }

    override val loading = MutableLiveData<Boolean>()

    override val error = MutableLiveData<Throwable>()

    override fun load() {
        // no-op
    }

}