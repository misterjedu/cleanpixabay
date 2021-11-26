package com.jedun.cleanpixabay.presentation.ui.searchimages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jedun.cleanpixabay.databinding.FragmentFirstBinding
import com.jedun.cleanpixabay.presentation.ui.searchimages.ImagePreviewListFragmentDirections.actionFirstFragmentToSecondFragment
import com.jedun.cleanpixabay.presentation.model.HitPreview
import com.jedun.cleanpixabay.presentation.ui.searchimages.adapters.HitPreviewListAdapter
import com.jedun.cleanpixabay.presentation.util.showDialog
import com.jedun.cleanpixabay.presentation.widgets.EndlessRecyclerViewScrollListener
import com.jedun.cleanpixabay.utils.PER_PAGE
import com.jedun.cleanpixabay.utils.fragmentSlideInLeftAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagePreviewListFragment : Fragment() {

    private val viewModel: SearchImageViewModel by activityViewModels()
    private lateinit var hitPreviewListAdapter: HitPreviewListAdapter
    private lateinit var hitPreviewRecyclerView: RecyclerView
    private var _binding: FragmentFirstBinding? = null
    private var data: List<HitPreview> = listOf()

    var canFetch = true


    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        initVariables()

        viewModel.imagesStateObservable.observe(viewLifecycleOwner, { data ->
            this.data = data.images
            canFetch = data.images.size >= PER_PAGE
            hitPreviewListAdapter.submitList(this.data)

//            Toast.makeText(requireContext(), data.viewState.name, Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(), data.error, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (!text.isNullOrBlank()) {
                    viewModel.query = text
                    viewModel.page = 1
                    viewModel.getResult(viewModel.page)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                }
                return false
            }
        })


        val scrollListener = object :
            EndlessRecyclerViewScrollListener(binding.hitPreviewRecyclerView.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (canFetch) {
                    Toast.makeText(requireContext(), viewModel.page.toString(), Toast.LENGTH_SHORT)
                        .show()
                    viewModel.searchNextPage()
                }
            }
        }

        binding.hitPreviewRecyclerView.addOnScrollListener(scrollListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initVariables() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        hitPreviewListAdapter = HitPreviewListAdapter { navigateToImageDetail(it) }
        hitPreviewRecyclerView = binding.hitPreviewRecyclerView
        hitPreviewRecyclerView.adapter = hitPreviewListAdapter
    }


    private fun navigateToImageDetail(id: Int) {
        showDialog {
            val action =
                viewModel.imagePreviewDetailList.value?.find { it.id == id }?.let {
                    actionFirstFragmentToSecondFragment(it)
                }

            action?.let {
                findNavController().navigate(it, fragmentSlideInLeftAnimation().build())
            }
        }
    }
}