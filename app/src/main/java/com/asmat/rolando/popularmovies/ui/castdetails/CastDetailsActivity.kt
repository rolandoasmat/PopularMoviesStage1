package com.asmat.rolando.popularmovies.ui.castdetails

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import androidx.activity.viewModels
import com.asmat.rolando.popularmovies.MovieNightApplication
import com.asmat.rolando.popularmovies.R
import com.asmat.rolando.popularmovies.extensions.gone
import com.asmat.rolando.popularmovies.extensions.visible
import com.asmat.rolando.popularmovies.ui.castdetails.personmoviecredits.PersonMovieCreditsFragment
import com.asmat.rolando.popularmovies.viewmodels.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cast_detail.*
import java.lang.IllegalStateException
import javax.inject.Inject

class CastDetailsActivity : AppCompatActivity(), PersonMovieCreditsFragment.Listener {

    companion object {
        const val EXTRA_PERSON_ID = "PERSON_ID_KEY"

        fun createIntent(context: Context, castID: Int): Intent {
            val destinationClass = CastDetailsActivity::class.java
            val intent = Intent(context, destinationClass)
            intent.putExtra(EXTRA_PERSON_ID, castID)
            return intent
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel: CastDetailsViewModel by viewModels{ viewModelFactory }

    private val tabName = listOf("Info", "Movie credits")

    private val personID: Int
            get() = intent?.getIntExtra(EXTRA_PERSON_ID, -1) ?: throw IllegalStateException("No person ID found.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MovieNightApplication).component().inject(this)
        setContentView(R.layout.activity_cast_detail)
        viewModel.init(personID)
        setup()
    }


    //region setup

    private fun setup() {
        setupToolbar()
        observeViewModel()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun observeViewModel() {
        viewModel.name.observe(this, Observer {
            collapsingToolbar.title = it
        })
        viewModel.uiModel.observe(this, Observer { uiModel ->
            uiModel?.let { setupViewPager(uiModel) }
        })
        viewModel.loading.observe(this, Observer { loading ->
            updateLoading(loading == true)
        })
    }

    //endregion

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupViewPager(uiModel: CastDetailsUiModel) {
        val adapter = CastDetailsPagerAdapter(uiModel, this)
        castDetailsViewPager?.adapter = adapter
        TabLayoutMediator(tabLayout, castDetailsViewPager) { tab, position ->
            tab.text = tabName[position]
        }.attach()
    }

    //region Callbacks
    override fun setBackdropURL(url: String?) {
        url?.let {
            Picasso.get().load(it).into(toolbarImage)
        }
    }
    //endregion

    private fun updateLoading(loading: Boolean) {
        if (loading) {
            loadingView?.visible()
        } else {
            loadingView?.gone()
        }
    }
}
