package com.karapetyanarthur.canisapp.Activities.Registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.databinding.FragmentEnterBinding;
import com.karapetyanarthur.canisapp.databinding.FragmentLogInBinding;

public class LogInFragment extends Fragment {

    FragmentLogInBinding binding;

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLogInBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.hidePassBtn.setVisibility(View.GONE);
        binding.showPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.passEt.setTransformationMethod(null);
                binding.showPassBtn.setVisibility(View.GONE);
                binding.hidePassBtn.setVisibility(View.VISIBLE);
            }
        });
        binding.hidePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.passEt.setTransformationMethod(new PasswordTransformationMethod());
                binding.showPassBtn.setVisibility(View.VISIBLE);
                binding.hidePassBtn.setVisibility(View.GONE);
            }
        });
        binding.logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.logInBtn.getText().toString().equals("") && !binding.passEt.getText().toString().equals("")) {
//ДОБАВИТЬ ПРОВЕРКУ ЛОГИНА И ПАРОЛЯ
                    //replaceFragment(new PermissionsFragment);
                }
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }
}