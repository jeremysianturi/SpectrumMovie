package com.example.mymovies.ui.fragment.award

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.mymovies.databinding.FragmentAwardBinding
import com.example.mymovies.helper.Constant
import com.example.mymovies.ui.activity.moviedetail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.util.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AwardFragment : Fragment() {

    private lateinit var binding: FragmentAwardBinding
    private lateinit var adapterPopularMoviesGrid : AwardAdapterGrid
    private val popularMovieGridViewModel : AwardViewModel by viewModels()

    private var apiKey = Constant.API_KEY
    private var includeAdult = false
    private var includeVideo = false
    private var page = "1"
    private var year = Calendar.getInstance().get(Calendar.YEAR).toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAwardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserverPopularMoviesGrid(apiKey,page)
        buildListPopularMoviesGrid()

        // search
        binding.layoutToolbar.edtSearch.doOnTextChanged { text, start, before, count ->
            popularMovieGridViewModel.searchQuery.value = text.toString()
        }
    }

    private fun setupObserverPopularMoviesGrid(apiKey: String,page: String) {

        popularMovieGridViewModel.getPopularMoviesGrid(apiKey,page).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarPopular.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarPopular.visibility = View.GONE
                        adapterPopularMoviesGrid.setData(data.data)
                        Timber.tag(tag).d("observer_popular_movies_grid_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarPopular.visibility = View.GONE
                        Timber.tag(tag).d("error_message ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(parentFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }

        popularMovieGridViewModel.search.observe(viewLifecycleOwner) { data ->
            adapterPopularMoviesGrid.setData(data)

        }
    }

    private fun buildListPopularMoviesGrid() {

        adapterPopularMoviesGrid = AwardAdapterGrid()
        binding.rvGridview.setHasFixedSize(true)
        binding.rvGridview.layoutManager = GridLayoutManager(activity,2)
//            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvGridview.adapter = adapterPopularMoviesGrid

        binding.rvGridview.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.HORIZONTAL
            )
        )

//        adapterPopularMoviesGrid.onItemClick = { selectData ->
//            val mIntent = Intent(activity, DetailMovieActivity::class.java)
//            mIntent.putExtra(DetailMovieActivity.EXTRA_DATA, selectData)
//            startActivity(mIntent)
//        }

        adapterPopularMoviesGrid.onItemClick = { selectData ->
            val mIntent = Intent(activity, DetailMovieActivity::class.java)
            mIntent.putExtra("id_choosen", selectData.id)
            startActivity(mIntent)
        }
    }

}