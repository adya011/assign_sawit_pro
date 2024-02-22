package com.sawitpro.weightbridge.ui.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sawitpro.weightbridge.databinding.FragmentWeighingListBinding
import com.sawitpro.weightbridge.ui.feature.base.BaseFragment
import com.sawitpro.weightbridge.ui.feature.detail.AbstractWeighingCreateEditFragment.Companion.NAV_RESULT_UPDATED
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeighingListFragment : BaseFragment() {

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
        setupView()
        setupObserver()
        setupAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupView() = with(binding) {
        srlRefresh.setOnRefreshListener {
            viewModel.fetchWeighingList(true)
            srlRefresh.isRefreshing = true
        }
        btnAddTicket.setOnClickListener {
            navigateToCreate()
        }
        // TODO: date not formatted
    }

    private fun setupAdapter() {
        weighingAdapter = WeighingListAdapter(
            onClick = {
                navigateToDetail(it)
            },
            onEditClick = {
                navigateToEdit(it)
            }
        )
        binding.rvWeighing.adapter = weighingAdapter
    }

    private fun navigateToDetail(uId: String) {
        findNavController().navigate(
            WeighingListFragmentDirections.actionOpenDetail(uId)
        )
    }

    private fun navigateToEdit(uId: String) {
        findNavController().navigate(
            WeighingListFragmentDirections.actionOpenEdit(uId)
        )
    }

    private fun navigateToCreate() {
        findNavController().navigate(
            WeighingListFragmentDirections.actionOpenCreate()
        )
    }

    private fun setupObserver() {
        viewModel.weighingListLiveData.observe(viewLifecycleOwner) {
            binding.srlRefresh.isRefreshing = false
            weighingAdapter?.submitList(it)
        }

        viewModel.displayStateLiveData.observe(viewLifecycleOwner) {
            binding.vfMain.displayedChild = it
        }

        viewModel.warningMessageLiveData.observe(viewLifecycleOwner) {
            binding.layoutWarning.tvWarningMessage.text = it
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
            NAV_RESULT_UPDATED
        )?.observe(viewLifecycleOwner) { isUpdated ->
            if (isUpdated) viewModel.fetchWeighingList(true)
        }
    }
}