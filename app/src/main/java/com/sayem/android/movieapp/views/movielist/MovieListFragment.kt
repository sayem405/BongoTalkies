package com.sayem.android.movieapp.views.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sayem.android.movieapp.base.EndlessRecyclerOnScrollListener
import com.sayem.android.movieapp.base.ItemClickListener
import com.sayem.android.movieapp.base.SpacesItemDecoration
import com.sayem.android.movieapp.databinding.FragmentMovieListBinding
import com.sayem.android.movieapp.data.Movie
import com.sayem.android.movieapp.views.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Sayem on 5/23/2022
 */
@AndroidEntryPoint
class MovieListFragment : Fragment(), ItemClickListener<Movie> {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MovieListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listView.adapter = adapter
        binding.listView.addItemDecoration(SpacesItemDecoration(4))
        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.setData(it)
            binding.swipeRefresh.isRefreshing = false
        }

        binding.listView.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener(binding.listView.layoutManager as GridLayoutManager) {
            override fun onLoadMore(currentPage: Int) {
                viewModel.retrieveMore()
            }
        })

        viewModel.movieListError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val TAG = "MovieListFragment"
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }
    }

    override fun onItemClick(data: Movie) {
        viewModel.movieItemClicked(data)
    }
}