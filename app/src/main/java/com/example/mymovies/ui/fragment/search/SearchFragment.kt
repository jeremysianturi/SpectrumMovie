package com.example.mymovies.ui.fragment.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.mymovies.databinding.FragmentSearchBinding
import com.example.mymovies.helper.Constant
import com.example.mymovies.ui.activity.moviedetail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapterSearch : SearchAdapter
    private val searchViewModel : SearchViewModel by viewModels()

    private var apiKey = Constant.API_KEY
    private var query = ""
    private var page = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onclick()
    }

    private fun setupObserverSearch(apiKey: String, query: String, page: String) {

        searchViewModel.getSearch(apiKey,query,page).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarSearch.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarSearch.visibility = View.GONE
                        adapterSearch.setData(data.data)
                        showResult()
                        Timber.tag(tag).d("observer_search_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarSearch.visibility = View.GONE
                        Timber.tag(tag).d("error_message search ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(parentFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }

    }

    fun showResult(){
        binding.btnSearch.visibility = View.GONE
        binding.rvSeachResult.visibility = View.VISIBLE
    }

    fun showButtonSearch(){
        binding.btnSearch.visibility = View.VISIBLE
        binding.rvSeachResult.visibility = View.GONE
    }

    private fun buildListSearch() {

        adapterSearch = SearchAdapter()
        binding.rvSeachResult.setHasFixedSize(true)
        binding.rvSeachResult.layoutManager = GridLayoutManager(activity,2)
//            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSeachResult.adapter = adapterSearch

        binding.rvSeachResult.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.HORIZONTAL
            )
        )

        adapterSearch.onItemClick = { selectData ->
            val mIntent = Intent(activity, DetailMovieActivity::class.java)
            mIntent.putExtra("id_choosen", selectData.id)
            startActivity(mIntent)
        }
    }

    private fun onclick() {
        binding.apply {
            btnSearch.setOnClickListener {
                hideKeyboard()
                query = layoutToolbar.edtSearch.text.toString()
                Toast.makeText(activity, query, Toast.LENGTH_SHORT).show()
                setupObserverSearch(apiKey, query, page)
                buildListSearch()
            }

            layoutToolbar.edtSearch.setOnClickListener {
                showButtonSearch()
            }
        }
    }

    fun hideKeyboard(){
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

}