package com.syntax.hemmerich.batch008_houndsoflove.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.syntax.hemmerich.batch008_houndsoflove.adapter.ItemAdapter
import com.syntax.hemmerich.batch008_houndsoflove.databinding.FragmentHomeBinding
import com.syntax.hemmerich.batch008_houndsoflove.ui.viewmodel.ApiStatus
import com.syntax.hemmerich.batch008_houndsoflove.ui.viewmodel.MainViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObserver()

    }

    private fun addObserver(){
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            binding.rvImages.adapter = ItemAdapter(it)
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            when (it){
                ApiStatus.LOADING -> binding.progressBar.visibility = View.VISIBLE
                ApiStatus.DONE -> binding.progressBar.visibility = View.GONE
                ApiStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorImage.visibility = View.VISIBLE
                }
            }
        })
    }


}