package com.asmat.rolando.popularmovies.repositories

import com.asmat.rolando.popularmovies.TestObjectsFactory
import com.asmat.rolando.popularmovies.database.DatabaseManager
import com.asmat.rolando.popularmovies.networking.the.movie.db.TheMovieDBClient
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MoviesRepositoryTest {

    @Mock
    lateinit var mockDatabaseManager: DatabaseManager
    @Mock
    lateinit var mockTheMovieDBClient: TheMovieDBClient
    private val computationScheduler = Schedulers.trampoline()
    private val mainThreadScheduler = Schedulers.trampoline()

    lateinit var repository: MoviesRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = MoviesRepository(mockDatabaseManager, mockTheMovieDBClient, computationScheduler, mainThreadScheduler)
    }

    @Test
    fun setAndGetMovieDetailsData() {
        // Arrange
        val data = TestObjectsFactory.movie()

        // Act
        repository.setMovieDetailsData(data)

        // Assert
        val actual = repository.getMovieDetailsData()
        Assert.assertEquals(data, actual)
    }



}