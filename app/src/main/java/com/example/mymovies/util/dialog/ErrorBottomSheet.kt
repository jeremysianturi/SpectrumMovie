package com.example.mymovies.util.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import androidx.room.Room
import com.example.core.data.source.local.room.MovieDatabase
import com.example.core.utils.LovedEntity
import com.example.mymovies.databinding.ActivityErrorBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ErrorBottomSheet : BottomSheetDialogFragment() {

    // ViewBinding Delegate
    private val binding: ActivityErrorBottomSheetBinding by viewBinding()

    private var codes: String? = ""

    // loved
    lateinit var db: MovieDatabase

    companion object {

        const val TAG = "ERROR_BOTTOMSHEET"
        const val EXTRA_CODE = "extra_code"
        const val EXTRA_MESSAGE = "extra_message"
//        lateinit var selectDataParse : LovedEntity

        fun instance(code: String, message: String, selectedData: LovedEntity): ErrorBottomSheet {
            // setup data code and message from activity
            val mBundle = Bundle()
            mBundle.putString(EXTRA_CODE, code)
            mBundle.putString(EXTRA_MESSAGE, message)
//            selectDataParse = selectedData


            //bind data to this bottomsheetFragment
            val fragment = ErrorBottomSheet()
            fragment.arguments = mBundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root //return root from binding delegation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Room.databaseBuilder(requireContext(), MovieDatabase::class.java, "loved-db").build()

        // init
        codes = arguments?.getString(EXTRA_CODE)

        // check error code
        if (codes == "Filter only work when button search on keyboard is pressed") {
            binding.lottieAnimationView.setAnimation("attention.json")

        }

        setupView()
        setupClick()
    }

    private fun setupClick() {
        binding.btnPositive.setOnClickListener {

//            GlobalScope.launch {
//                db.lovedDao().delete(selectDataParse)
//            }
            dismiss()

        }
    }

    private fun setupView() {
        binding.tvTitle.text = arguments?.getString(EXTRA_CODE) ?: ""
        binding.tvSubTitle.text = arguments?.getString(EXTRA_MESSAGE) ?: ""
    }
}