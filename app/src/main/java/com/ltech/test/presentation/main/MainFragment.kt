package com.ltech.test.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ltech.test.databinding.FragmentMainBinding
import com.ltech.test.utils.toastShort
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUpdate.setOnClickListener {
            viewModel.getPosts()
        }

        viewModel.postsState.observe(viewLifecycleOwner) { state ->
            when {
                state.isLoading -> {
                    toastShort("Загрузка постов")
                    hideUpdateButton()
                    showProgressBar()
                }
                state.error != null-> {
                    toastShort("Произошла ошибка: ${state.error}")
                    showUpdateButton()
                    hideProgressBar()
                }
                state.posts != null -> {
                    hideProgressBar()
                    hideUpdateButton()
                    val adapter = PostsAdapter(requireContext(), state.posts) { postId ->
                        findNavController().navigate(MainFragmentDirections.actionMainFragmentToPostFragment().setPostId(postId))
                    }
                    binding.recyclerPosts.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerPosts.adapter = adapter
                }
            }
        }

    }

    private fun showUpdateButton() {
        binding.buttonUpdate.visibility = View.VISIBLE
    }

    private fun hideUpdateButton() {
        binding.buttonUpdate.visibility = View.GONE
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