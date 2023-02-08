package com.ltech.test.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ltech.test.R
import com.ltech.test.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            viewModel.login("", "")
        }

        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when {
                state.isLoading -> {
                    Toast.makeText(
                        requireContext(),
                        "Загрузка",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                state.phoneMask != null -> {
                    val phoneMask = state.phoneMask
                    if(phoneMask.isEmpty())
                        Toast.makeText(
                            requireContext(),
                            "Маска пуста",
                            Toast.LENGTH_SHORT
                        ).show()
                    else
                        Toast.makeText(
                            requireContext(),
                            "Маска готова: $phoneMask",
                            Toast.LENGTH_SHORT
                        ).show()
                }

                state.success == true -> {
                    Toast.makeText(
                        requireContext(),
                        "Логин успешен",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                state.success == false -> {
                    Toast.makeText(
                        requireContext(),
                        "Логин неудачен",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                state.error != null -> {
                    Toast.makeText(
                        requireContext(),
                        state.error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}