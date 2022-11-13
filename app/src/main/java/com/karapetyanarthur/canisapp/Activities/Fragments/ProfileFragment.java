package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.Data.Model.ProfileModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.ProfileViewModel;
import com.karapetyanarthur.canisapp.databinding.FragmentEditProfileBinding;
import com.karapetyanarthur.canisapp.databinding.FragmentProfileBinding;

import java.util.List;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

    ProfileViewModel model;

    public static String look_for;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (look_for != null){
            changeColorLookFor();
        }

        model = new ViewModelProvider(this).get(ProfileViewModel.class);
        model.getAllProfile().observe(getViewLifecycleOwner(), new Observer<List<ProfileModel>>() {
            @Override
            public void onChanged(List<ProfileModel> profileModels) {
                if (profileModels.size() != 0){
                    binding.emailProfile.setText(profileModels.get(profileModels.size() - 1).getEmail());
                    binding.nameProfile.setText(profileModels.get(profileModels.size() - 1).getName());
                    binding.surnameProfile.setText(profileModels.get(profileModels.size() - 1).getSurname());
                    binding.phoneProfile.setText(profileModels.get(profileModels.size() - 1).getPhone());
                    binding.ageProfile.setText(profileModels.get(profileModels.size() - 1).getAge());
                    if (profileModels.get(profileModels.size() - 1).getImage() != null){
                        binding.profileImageIv.setBackground(null);
                        binding.profileImageIv.setImageURI(Uri.parse(profileModels.get(profileModels.size() - 1).getImage()));
                    }
                }

                Log.d("User_Data", String.valueOf(profileModels.size()));

            }
        });

        binding.editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new EditProfileFragment());
            }
        });

        binding.lookForClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.lookForClient.setBackgroundColor(binding.lookForClient.getContext().getResources().getColor(R.color.purple_200));
                binding.lookForCynologist.setBackgroundColor(binding.lookForCynologist.getContext().getResources().getColor(R.color.purple_500));
                look_for = "client";
            }
        });

        binding.lookForCynologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.lookForCynologist.setBackgroundColor(binding.lookForCynologist.getContext().getResources().getColor(R.color.purple_200));
                binding.lookForClient.setBackgroundColor(binding.lookForClient.getContext().getResources().getColor(R.color.purple_500));
                look_for = "cynologist";
            }
        });
    }

        public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }

    public void changeColorLookFor(){
        if (look_for.equals("client")){
            binding.lookForClient.setBackgroundColor(binding.lookForClient.getContext().getResources().getColor(R.color.purple_500));
            binding.lookForCynologist.setBackgroundColor(binding.lookForCynologist.getContext().getResources().getColor(R.color.purple_200));
        } else if (look_for.equals("cynologist")){
            binding.lookForCynologist.setBackgroundColor(binding.lookForCynologist.getContext().getResources().getColor(R.color.purple_500));
            binding.lookForClient.setBackgroundColor(binding.lookForClient.getContext().getResources().getColor(R.color.purple_200));
        }
    }

}