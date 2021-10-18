package com.example.spacex_api.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.spacex_api.databinding.FragmentLaunchBinding
import com.squareup.picasso.Picasso


class LaunchFragment : Fragment() {

    private lateinit var binding: FragmentLaunchBinding
    private val args: LaunchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentLaunchBinding.inflate(layoutInflater)
        val launch = args.launch
        binding.fragmentLaunchDate.text = launch.date
        binding.fragmentLaunchName.text = launch.name
        binding.fragmentLaunchDetails.text = launch.details
        Picasso.get()
            .load(launch.url_img)
            .into(binding.fragmentLaunchImg)
        return binding.root
    }

}