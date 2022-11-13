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
import com.karapetyanarthur.canisapp.Data.DBPet;
import com.karapetyanarthur.canisapp.Data.Model.PetModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.PetViewModel;
import com.karapetyanarthur.canisapp.ViewModel.ProfileViewModel;
import com.karapetyanarthur.canisapp.databinding.FragmentEditPetBinding;
import com.karapetyanarthur.canisapp.databinding.FragmentPetBinding;

import java.util.List;

public class PetFragment extends Fragment {

    FragmentPetBinding binding;

    PetViewModel model;

    public static PetFragment newInstance() {
        return new PetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPetBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(PetViewModel.class);

        model.getAllPet().observe(getViewLifecycleOwner(), new Observer<List<PetModel>>() {
            @Override
            public void onChanged(List<PetModel> petModels) {
                if (petModels.size() != 0){
                    binding.nicknamePet.setText(petModels.get(petModels.size() - 1).getNickname());
                    binding.breedPet.setText(petModels.get(petModels.size() - 1).getBreed());
                    binding.agePet.setText(petModels.get(petModels.size() - 1).getAge());
                    if (petModels.get(petModels.size() - 1).getImage() != null){
                        binding.petImageIv.setBackground(null);
                        binding.petImageIv.setImageURI(Uri.parse(petModels.get(petModels.size() - 1).getImage()));
                    }
                }
            }
        });

        binding.editPetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new EditPetFragment());
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