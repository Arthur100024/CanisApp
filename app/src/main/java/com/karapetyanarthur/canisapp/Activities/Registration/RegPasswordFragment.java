package com.karapetyanarthur.canisapp.Activities.Registration;

import static com.yandex.runtime.Runtime.getApplicationContext;

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
import android.widget.Toast;

import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.databinding.FragmentRegLoginBinding;
import com.karapetyanarthur.canisapp.databinding.FragmentRegPasswordBinding;

public class RegPasswordFragment extends Fragment {

    FragmentRegPasswordBinding binding;

    public static RegPasswordFragment newInstance() {
        return new RegPasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegPasswordBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.hidePassBtn.setVisibility(View.GONE);
        binding.hideRepPassBtn.setVisibility(View.GONE);
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
        binding.showRepPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.repPassEt.setTransformationMethod(null);
                binding.showRepPassBtn.setVisibility(View.GONE);
                binding.hideRepPassBtn.setVisibility(View.VISIBLE);
            }
        });
        binding.hideRepPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.repPassEt.setTransformationMethod(new PasswordTransformationMethod());
                binding.showRepPassBtn.setVisibility(View.VISIBLE);
                binding.hideRepPassBtn.setVisibility(View.GONE);
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.passEt.getText().toString().equals("") && !binding.repPassEt.getText().toString().equals("")){
                    if (binding.passEt.getText().toString().equals(binding.repPassEt.getText().toString())){
// ДОБАВИТЬ СОХРАНЕНИЕ ДАННЫХ
                        replaceFragment(new EnterFragment());
                        showToast("Вы успешно зарегистрировались!");
                    } else {
                        showToast("Повторите пароль правильно!");
                    }
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