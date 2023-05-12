package com.example.mymovies.ui.fragment.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.core.data.source.local.room.MovieDatabase
import com.example.core.utils.LovedEntity
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentAwardBinding
import com.example.mymovies.databinding.FragmentFavoriteBinding
import com.example.mymovies.util.dialog.ErrorBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter : LovedAdapter

    // loved
    lateinit var db: MovieDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Room.databaseBuilder(requireContext(), MovieDatabase::class.java, "loved-db").build()

//        GlobalScope.launch {
//            getLovedMovies()
//
//        }
        buildList()

        // search
//        GlobalScope.launch {
//            binding.layoutToolbar.edtSearch.doOnTextChanged { text, start, before, count ->
//                db.lovedDao().getSearchLoved(text.toString())
//            }
//        }
//
//        getLovedMovies()
//        buildList()
    }

    private fun getLovedMovies(){
        val lovedAtEntity  = db.lovedDao().getAllLoved()
        Timber.d("check value lovedAtEntity: $lovedAtEntity")
        setupObserver(lovedAtEntity)
    }

    private fun setupObserver(list : List<LovedEntity>) {

        adapter.setData(list)
    }

    private fun buildList() {

        adapter = LovedAdapter()
        binding.rvFavorite.setHasFixedSize(true)
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvFavorite.adapter = adapter

        binding.rvFavorite.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.HORIZONTAL
            )
        )

        adapter.onItemClick = { selectData ->
//            ErrorBottomSheet.instance("data.message.toString()", "data.message",selectData)
//                .show(parentFragmentManager, ErrorBottomSheet.TAG)

            Timber.d("delete all : $selectData")

            GlobalScope.launch {
                db.lovedDao().delete(selectData)
                getLovedMovies()
            }
            buildList()

        }
    }

}