package com.example.movieapp.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.api.model.detail.MovieDetail
import com.example.movieapp.api.model.movie.Results
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.ui.moviedetail.viewmodel.MovieDetailViewModel
import com.example.movieapp.util.keys.BundleKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieDetailFragment : Fragment() {


    private val viewModel: MovieDetailViewModel by viewModels()

    lateinit  var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_movie_detail)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

     val movieItem = arguments?.get(BundleKeys.ARG_MOVIES) as Results

        binding.item = movieItem


    }
}