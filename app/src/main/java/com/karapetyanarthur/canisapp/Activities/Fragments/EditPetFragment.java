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
import com.karapetyanarthur.canisapp.Activities.Registration.MapSetLocFragment;
import com.karapetyanarthur.canisapp.Data.DBPet;
import com.karapetyanarthur.canisapp.Data.Model.PetModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.EditPetViewModel;
import com.karapetyanarthur.canisapp.ViewModel.PetViewModel;
import com.karapetyanarthur.canisapp.ViewModel.ProfileViewModel;
import com.karapetyanarthur.canisapp.databinding.FragmentEditPetBinding;
import com.karapetyanarthur.canisapp.databinding.FragmentMapSetLocBinding;

import java.util.List;

public class EditPetFragment extends Fragment {

    FragmentEditPetBinding binding;

    EditPetViewModel model;

    Uri uri_image;

    public static EditPetFragment newInstance() {
        return new EditPetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditPetBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(EditPetViewModel.class);

        model.getAllPet().observe(getViewLifecycleOwner(), new Observer<List<PetModel>>() {
            @Override
            public void onChanged(List<PetModel> petModels) {
                if (petModels.size() != 0){
                    binding.nicknamePetEt.setText(petModels.get(petModels.size() - 1).getNickname());
                    binding.breedPetEt.setText(petModels.get(petModels.size() - 1).getBreed());
                    binding.agePetEt.setText(petModels.get(petModels.size() - 1).getAge());
                    if (petModels.get(petModels.size() - 1).getImage() != null){
                        uri_image = Uri.parse(petModels.get(petModels.size() - 1).getImage());
                        binding.petImageIv.setBackground(null);
                        binding.petImageIv.setImageURI(Uri.parse(petModels.get(petModels.size() - 1).getImage()));
                    }
                }
            }
        });

        ActivityResultLauncher<String[]> getContent = getActivity().getActivityResultRegistry().register("key", new ActivityResultContracts.OpenDocument(), result -> {
            getActivity().getContentResolver().takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION);

            binding.petImageIv.setBackground(null);
            binding.petImageIv.setImageURI(result);
            uri_image = result;
        });

        binding.changePetImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContent.launch(new String[]{"image/*"});
            }
        });


        binding.saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetModel pet = new PetModel();
                pet.setId(0);
                pet.setNickname(binding.nicknamePetEt.getText().toString());
                pet.setBreed(binding.breedPetEt.getText().toString());
                pet.setAge(binding.agePetEt.getText().toString());
                if (uri_image != null) {
                    pet.setImage(uri_image.toString());
                }
                model.insert(pet);
                replaceFragment(new PetFragment());
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