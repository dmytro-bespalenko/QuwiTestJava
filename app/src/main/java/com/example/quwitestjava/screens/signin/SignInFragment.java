package com.example.quwitestjava.screens.signin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.quwitestjava.R;
import com.example.quwitestjava.databinding.FragmentSignInBinding;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignInFragment extends Fragment {

    private FragmentSignInBinding binding;
    private SignInViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SignInViewModel.class);
        viewModel.isResponseSuccess.observe(getViewLifecycleOwner(), state -> {
            if (state.handled) return;
            switch (state.getValue()) {
                case LoginSuccessfulState:
                    if (Objects.requireNonNull(Navigation.findNavController(view)
                            .getCurrentDestination()).getId() == R.id.signInFragment
                    ) {
                        Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_usersListFragment);
                    }
                    break;
                case LoginErrorState:
                    Toast.makeText(requireContext(), R.string.email_wrong, Toast.LENGTH_SHORT).show();
                    break;

            }

        });


        binding.signInButton.setOnClickListener(v -> login());
        binding.signUpButton.setOnClickListener(v -> Toast.makeText(requireContext(), R.string.email_wrong, Toast.LENGTH_SHORT).show());

    }


    private void login() {
        String email = Objects.requireNonNull(binding.emailEditText.getText()).toString().trim();
        String password = Objects.requireNonNull(binding.passwordEditText.getText()).toString().trim();
        viewModel.login(email, password);
    }


}