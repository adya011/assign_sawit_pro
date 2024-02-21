package com.sawitpro.weightbridge.ui.feature.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sawitpro.weightbridge.databinding.FragmentWeighingListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeighingListFragment : Fragment() {

    private val viewModel by viewModel<WeighingListViewModel>()

    private var _binding: FragmentWeighingListBinding? = null
    private val binding get() = _binding!!

    private var weighingAdapter: WeighingListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeighingListBinding.inflate(inflater, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchWeighingList()
        setupObserver()
        setupAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupAdapter() {
        weighingAdapter = WeighingListAdapter(
            onClick = {},
            onEditClick = {
                navigateToEdit()
            }
        )
        binding.rvWeighing.adapter = weighingAdapter
    }

    private fun navigateToEdit() {
        findNavController().navigate(
            WeighingListFragmentDirections.actionOpenCreateEdit()
        )
    }

    private fun setupObserver() {
        viewModel.weighingListLiveData.observe(viewLifecycleOwner) {
            weighingAdapter?.submitList(it)
        }
    }
}