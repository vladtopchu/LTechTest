package com.ltech.test.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ltech.test.databinding.FragmentLoginBinding
import com.ltech.test.utils.toastShort
import com.vicmikhailau.maskededittext.MaskedFormatter
import com.vicmikhailau.maskededittext.MaskedWatcher
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonEnter.setOnClickListener {
            val clearPhone = "[^0-9]".toRegex().replace(binding.inputPhone.text.toString(), "")
            Log.d("CLEAR PHONE", clearPhone)
            viewModel.login(clearPhone, binding.inputPassword.text.toString())
        }

        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when {
                state.savedPhone != null && state.savedPassword != null -> {
                    binding.inputPhone.setText(state.savedPhone)
                    binding.inputPassword.setText(state.savedPassword)
                }

                state.isLoading -> {
                    binding.buttonEnter.isEnabled = false
                    toastShort("Загрузка")
                }

                state.error != null -> {
                    binding.buttonEnter.isEnabled = true
                    toastShort(state.error)
                }

                state.phoneMask != null -> {
                    binding.buttonEnter.isEnabled = true

                    val phoneMask = state.phoneMask.replace("Х", "#")
                    Log.d("TEST", phoneMask)

                    val formatter = MaskedFormatter(phoneMask)
                    binding.inputPhone.hint = phoneMask
                    binding.inputPhone.addTextChangedListener(MaskedWatcher(formatter, binding.inputPhone))
                }

                state.loginSuccess == true -> {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                }

                state.loginSuccess == false -> {
                    binding.buttonEnter.isEnabled = true
                    toastShort("Произошла ошибка")
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}