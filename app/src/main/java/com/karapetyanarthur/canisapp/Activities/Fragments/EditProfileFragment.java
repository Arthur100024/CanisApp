package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import android.widget.EditText;
import android.widget.ImageView;

import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.Data.Model.ProfileModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.EditProfileViewModel;
import com.karapetyanarthur.canisapp.databinding.FragmentEditPetBinding;
import com.karapetyanarthur.canisapp.databinding.FragmentEditProfileBinding;

import java.util.List;

public class EditProfileFragment extends Fragment {

    FragmentEditProfileBinding binding;

    EditProfileViewModel model;

    Uri uri_image;

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.phoneProfileEt.setText("+7");
        model = new ViewModelProvider(this).get(EditProfileViewModel.class);

        model.getAllProfile().observe(getViewLifecycleOwner(), new Observer<List<ProfileModel>>() {
            @Override
            public void onChanged(List<ProfileModel> profileModels) {
                if (profileModels.size() != 0){
                    binding.emailProfileEt.setText(profileModels.get(profileModels.size() - 1).getEmail());
                    binding.nameProfileEt.setText(profileModels.get(profileModels.size() - 1).getName());
                    binding.surnameProfileEt.setText(profileModels.get(profileModels.size() - 1).getSurname());
                    binding.phoneProfileEt.setText(profileModels.get(profileModels.size() - 1).getPhone());
                    binding.ageProfileEt.setText(profileModels.get(profileModels.size() - 1).getAge());
                    if (profileModels.get(profileModels.size() - 1).getImage() != null){
                        uri_image = Uri.parse(profileModels.get(profileModels.size() - 1).getImage());
                        binding.profileImageIv.setBackground(null);
                        binding.profileImageIv.setImageURI(Uri.parse(profileModels.get(profileModels.size() - 1).getImage()));
                    }
                }
            }
        });

        ActivityResultLauncher<String[]> getContent = getActivity().getActivityResultRegistry().register("key", new ActivityResultContracts.OpenDocument(), result -> {
            getActivity().getContentResolver().takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            binding.profileImageIv.setBackground(null);
            binding.profileImageIv.setImageURI(result);
            uri_image = result;
        });



        binding.changeProfileImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContent.launch(new String[]{"image/*"});
            }
        });


        binding.saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Saving to ROOM
                ProfileModel profile = new ProfileModel();
                profile.setId(0);
                profile.setEmail(binding.emailProfileEt.getText().toString());
                profile.setName(binding.nameProfileEt.getText().toString());
                profile.setSurname(binding.surnameProfileEt.getText().toString());
                profile.setPhone(binding.phoneProfileEt.getText().toString());
                profile.setAge(binding.ageProfileEt.getText().toString());
                if (uri_image != null){
                    profile.setImage(uri_image.toString());
                }
                model.insert(profile);
                replaceFragment(new ProfileFragment());
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