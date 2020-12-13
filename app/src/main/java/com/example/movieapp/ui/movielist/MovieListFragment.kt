package com.example.movieapp.ui.movielist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.api.model.genres.Genre
import com.example.movieapp.api.model.movie.Results
import com.example.movieapp.data.Resource
import com.example.movieapp.data.Status
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.listener.IListener
import com.example.movieapp.ui.movielist.adapter.MovieListAdapter
import com.example.movieapp.ui.movielist.viewmodel.MovieListViewModel
import com.example.movieapp.util.extensions.OnSwipeTouchListener
import com.example.movieapp.util.extensions.navigateSafe
import com.example.movieapp.util.keys.BundleKeys
import com.github.ajalt.timberkt.d
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * A simple [Fragment] subclass.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ClickableViewAccessibility")
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieListFragment : Fragment(), IListener {

    var tabPosition: Int = -1


    private val viewModel: MovieListViewModel by viewModels()

    lateinit var binding: FragmentMovieListBinding

    var movieList: List<Results> = listOf()
    var genreList: List<Genre> = listOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.fetchGenres().observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                genreList = it.data!!.list

                for (item in it.data.list) {
                    binding.tablayout.addTab(binding.tablayout.newTab().setText(item.name))
                }
                viewModel.fetchMovieList(genreList[0].id.toString())
                    .observe(viewLifecycleOwner, Observer {
                        if (it.status == Status.SUCCESS) {
                            movieList = it.data!!.results
                            binding.rvMovies.adapter = MovieListAdapter(movieList, this)
                        }
                    })
            }
        })


        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabPosition = tab!!.position
                binding.tvGenre.text = genreList[tabPosition].name
                onClick(genreList[tab.position])

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


        binding.rvMovies.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
            override fun onSwipeRight() {
                if (tabPosition != 0) {
                    tabPosition -= 1
                    binding.tablayout.getTabAt(tabPosition)!!.select()
                }
            }

            override fun onSwipeLeft() {
                if (tabPosition != binding.tablayout.tabCount-1) {
                    tabPosition += 1
                    binding.tablayout.getTabAt(tabPosition)!!.select()
                }

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        binding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_movie_list)

        return view


    }


    override fun onClick(item: Results) {
        val bundle = Bundle().apply {
            putParcelable(BundleKeys.ARG_MOVIES, item)
        }
        navigateSafe(R.id.action_movieListFragment_to_movieDetailFragment, bundle)
    }

    override fun onClick(item: Genre) {
        viewModel.fetchMovieList(item.id.toString())
            .observe(viewLifecycleOwner, Observer {
                viewModel.movieList.postValue(Resource.loading(null))
                movieList = listOf()
                if (it.status == Status.SUCCESS) {
                    movieList = it.data!!.results
                    binding.rvMovies.adapter = MovieListAdapter(movieList, this)
                }
            })
    }


}

