package com.sayem.android.movieapp.views.moviedetails

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sayem.android.movieapp.base.Result
import com.sayem.android.movieapp.databinding.FragmentMovieDetailsBinding
import com.sayem.android.movieapp.domain.Movie
import com.sayem.android.movieapp.utils.format
import com.sayem.android.movieapp.utils.getComaSeparatedString
import com.sayem.android.movieapp.views.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Sayem on 5/23/2022
 */
@AndroidEntryPoint
class MovieDetailsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = requireArguments().getParcelable(DATA)!!
        viewModel.getMovieDetailsInformation(movie.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            dismiss()
        }
        updateView(movie)
        viewModel.movieDetailsResult.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Error -> {
                    Toast.makeText(requireContext(), it.ex.toString(), Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    updateView(it.value)
                }
            }
        }
    }

    private fun updateView(movie: Movie) {
        binding.titleTextView.text = movie.title
        binding.descriptionTextView.text = movie.overview
        binding.viewCountTextView.text = movie.viewCount.toString()
        binding.ratingTextView.text = movie.rating.toString()
        binding.homePageGroup.visibility =
            if (movie.homePage.isNullOrBlank()) View.GONE else View.VISIBLE
        binding.homePageTextView.text = movie.homePage
        binding.homePageTextView.setOnClickListener {
            movie.homePage?.let {
                openUrl(it)
            }
        }

        binding.releaseDateGroup.visibility = if (movie.releasedDate == null) View.GONE else View.VISIBLE
        binding.releaseDatetextView.text = movie.releasedDate.format(DISPLAY_DATE_FORMAT)

        binding.genresGroup.visibility = if (movie.genres.isNullOrEmpty()) View.GONE else View.VISIBLE
        binding.genresTextView.text = movie.genres.getComaSeparatedString()

        Glide.with(binding.imageView).load(movie.thumbnailUrl)
            .transition(DrawableTransitionOptions.withCrossFade()).into(binding.imageView)
    }

    private fun openUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    companion object {
        const val TAG = "MovieDetailsFragment"
        const val DISPLAY_DATE_FORMAT = "dd MMM yyyy"
        private const val DATA = "Data"
        fun newInstance(movie: Movie): MovieDetailsFragment {
            return MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DATA, movie)
                }
            }
        }
    }
}