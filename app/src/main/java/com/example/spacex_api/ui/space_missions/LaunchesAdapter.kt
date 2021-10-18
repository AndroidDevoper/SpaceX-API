package com.example.spacex_api.ui.space_missions

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.spacex_api.databinding.ItemSpaceMissionBinding
import com.example.spacex_api.ui.ItemLaunch
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import kotlin.collections.ArrayList

class LaunchesAdapter(private val layoutInflater: LayoutInflater,
                      private val callback: (ItemLaunch) -> Unit): BaseAdapter() {

    private var list = ArrayList<ItemLaunch>()

    fun upDate(list: ArrayList<ItemLaunch>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ItemSpaceMissionBinding.inflate(layoutInflater)
        val launch = list[p0]
        binding.itemSpaceMissionTvName.text = launch.name
        binding.itemSpaceMissionTvDate.text = launch.date
        binding.itemSpaceMissionImg.visibility = View.VISIBLE
        Picasso.get()
            .load(launch.url_img)
            .into(binding.itemSpaceMissionImg, object :Callback {
                override fun onSuccess() {
                    binding.itemSpaceMissionProgressBar.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {

                }

            })
        binding.root.setOnClickListener {
            callback.invoke(launch)
        }
        return binding.root
    }

    fun sortOldFirst() {
        list.sortBy { it.date_unix}
        notifyDataSetChanged()
    }

    fun sortNewFirst() {
        list.sortByDescending { it.date_unix }
        notifyDataSetChanged()
    }

}