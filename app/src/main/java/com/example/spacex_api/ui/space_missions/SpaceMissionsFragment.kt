package com.example.spacex_api.ui.space_missions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spacex_api.ConnectivityListener
import com.example.spacex_api.R
import com.example.spacex_api.databinding.FragmentSpaceMissionsBinding

class SpaceMissionsFragment : Fragment() {

    private lateinit var viewModel: SpaceMissionsViewModel
    private var _binding: FragmentSpaceMissionsBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: LaunchesAdapter

    private var isConnected: Boolean = false
    private lateinit var connectivityListener: ConnectivityListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        _binding = FragmentSpaceMissionsBinding.inflate(inflater, container, false)
        initAdapter()
        initSpinner()
        initConnectivityListener()
        return binding.root
    }

    private fun initConnectivityListener() {
        connectivityListener = ConnectivityListener(requireContext()) {
            isConnected = it
            if (isConnected)
                viewModel.init()
            else
                Toast.makeText(requireContext(),
                    R.string.toast_network_error,
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAdapter() {
        adapter = LaunchesAdapter(layoutInflater) {
            if(isConnected)
                findNavController().navigate(
                    SpaceMissionsFragmentDirections.actionNavigationSpaceMissionsToLaunchFragment(it)
                )
            else
                Toast.makeText(requireContext(),  getString(R.string.toast_network_error),
                    Toast.LENGTH_SHORT).show()
        }
        binding.fragmentSpaceMissionsListView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SpaceMissionsViewModel::class.java)
        viewModel.successLaunches.observe(viewLifecycleOwner, {
            adapter.upDate(it)
        })
    }

    private fun initSpinner() {
        val itemsSpinner = arrayOf(getString(R.string.spinner_item_old),
            getString(R.string.spinner_item_new))
        val spinnerAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item,
            itemsSpinner)
        binding.fragmentSpaceMissionsSpinner.adapter = spinnerAdapter
        binding.fragmentSpaceMissionsSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0)
                    adapter.sortOldFirst()
                else
                    adapter.sortNewFirst()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}