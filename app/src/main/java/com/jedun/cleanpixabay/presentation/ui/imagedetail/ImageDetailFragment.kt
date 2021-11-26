package com.jedun.cleanpixabay.presentation.ui.imagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jedun.cleanpixabay.databinding.FragmentImageDetailBinding
import com.jedun.cleanpixabay.presentation.model.HitDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailFragment : Fragment() {

    private var _binding: FragmentImageDetailBinding? = null
    private val imageDetail by navArgs<ImageDetailFragmentArgs>()
    private lateinit var hitDetail: HitDetail
    private lateinit var chipGroup: ChipGroup
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentImageDetailBinding.inflate(inflater, container, false)

        hitDetail = imageDetail.hitDetail
        chipGroup = binding.imageDetailChipGroup

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hitDetail = hitDetail
        hitDetail.tagList.split(",").forEach { addChip(it.trim()) }
    }

    private fun addChip(text: String) {
        val chip = Chip(context)
        chip.text = text
        chipGroup.addView(chip)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}