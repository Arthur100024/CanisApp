package com.karapetyanarthur.canisapp.Activities.Registration;

import static com.yandex.runtime.Runtime.getApplicationContext;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.databinding.FragmentRegLoginBinding;
import com.karapetyanarthur.canisapp.databinding.FragmentRegPhoneBinding;

public class RegLoginFragment extends Fragment {

    FragmentRegLoginBinding binding;

    public static RegLoginFragment newInstance() {
        return new RegLoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.loginEt.getText().toString().equals("")){


                    replaceFragment(new RegPasswordFragment());
                } else {
                    showToast("Введите все поля!");
                }
            }
        });
    }

    public void showToast(String toast_text){
        Toast showMyToast = Toast.makeText(getActivity(),toast_text,Toast.LENGTH_SHORT);
        showMyToast.show();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }
}