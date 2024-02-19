package com.sawitpro.weightbridge.ui.feature.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sawitpro.weightbridge.data.model.entity.TruckDataEntity
import com.sawitpro.weightbridge.databinding.FragmentWeighingListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeighingListFragment : Fragment() {

    private val viewModel by viewModel<WeighingListViewModel>()

    private var _binding: FragmentWeighingListBinding? = null
    private val binding get() = _binding!!

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

        /*val map: Map<String, TruckDataEntity> = mapOf(
            "-Nr1DQZKrjc-gfcJbQCE" to TruckDataEntity(
                date = "asd",
                driverName = "asdasd",
                id = "1",
                licenseNumber = "123"
            )
        )*/
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}