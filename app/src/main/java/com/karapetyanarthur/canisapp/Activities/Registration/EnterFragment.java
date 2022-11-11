package com.karapetyanarthur.canisapp.Activities.Registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.databinding.FragmentEnterBinding;

public class EnterFragment extends Fragment {

    FragmentEnterBinding binding;

    public static EnterFragment newInstance(){
        return new EnterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEnterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               replaceFragment(new LogInFragment());
            }
        });

        binding.regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new RegNameSurnameFragment());
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