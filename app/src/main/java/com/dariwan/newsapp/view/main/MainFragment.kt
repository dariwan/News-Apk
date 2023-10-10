package com.dariwan.newsapp.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dariwan.newsapp.R
import com.dariwan.newsapp.adapter.NewsHorizontalAdapter
import com.dariwan.newsapp.adapter.NewsVerticalAdapter
import com.dariwan.newsapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding:  FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModel.MainFragmentFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionHorizontal()
        setupActionVertical()

    }

    private fun setupActionHorizontal() {
        val adapter = NewsHorizontalAdapter()
        binding.horizontalRecyclerView.adapter = adapter
        binding.horizontalRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.news.observe(requireActivity()){
            adapter.submitData(lifecycle, it)
        }
    }

    private fun setupActionVertical() {
        val adapter = NewsVerticalAdapter()
        binding.verticalRecyclerView.adapter = adapter
        binding.verticalRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.news.observe(requireActivity()){
            adapter.submitData(lifecycle, it)
        }

    }

}