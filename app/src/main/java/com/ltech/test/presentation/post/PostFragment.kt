package com.ltech.test.presentation.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.ltech.test.databinding.FragmentPostBinding
import com.ltech.test.presentation.main.MainViewModel
import com.ltech.test.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.post.observe(viewLifecycleOwner) { post ->
            if(post == null) {
                showProgressBar()
            } else {
                hideProgressBar()
                binding.textDate.text = post.date
                binding.textTitle.text = post.title
                Glide.with(requireContext()).load(Constants.BASE_URL + post.image).into(binding.image)
                binding.textContent.text = post.text
            }
        }
    }

    private fun showProgressBar() {
        binding.circularProgress.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.circularProgress.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}