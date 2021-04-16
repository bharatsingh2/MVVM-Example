package com.example.android.hackerearchpractice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.hackerearchpractice.R
import com.example.android.hackerearchpractice.databinding.MainFragmentBinding
import com.example.android.hackerearchpractice.di.Injectable
import com.example.android.hackerearchpractice.result.EventObserver
import com.example.android.hackerearchpractice.util.SpaceDecoration
import com.example.android.hackerearchpractice.util.viewModelProvider
import com.example.android.hackerearchpractice.vo.Weather
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: MainFragmentBinding
    private lateinit var listAdapter: Adapter


    private val viewModel: MainViewModel by lazy {
        viewModelProvider<MainViewModel>(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        listAdapter = Adapter()

        binding.list.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.list.addItemDecoration(
            SpaceDecoration(
                resources.getDimension(R.dimen.list_item_space_normal)
                    .toInt()
            )
        )

        viewModel.showError.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.weatherList.observe(viewLifecycleOwner,
            Observer<List<Weather>> {
                listAdapter.submitList(it)
            })
    }

}