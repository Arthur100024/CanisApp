package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karapetyanarthur.canisapp.Adapters.AboutDogsAdapter;
import com.karapetyanarthur.canisapp.Data.Model.AboutDogsModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.AboutDogsViewModel;
import com.karapetyanarthur.canisapp.databinding.FragmentAboutDogsBinding;
import com.karapetyanarthur.canisapp.databinding.FragmentEditPetBinding;

import java.util.List;

public class AboutDogsFragment extends Fragment {

    FragmentAboutDogsBinding binding;
    AboutDogsAdapter aboutDogsAdapter;
    private AboutDogsViewModel aboutDogsViewModel;

    public static AboutDogsFragment newInstance() {
        return new AboutDogsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAboutDogsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.recyclerAboutDogs.setLayoutManager(new LinearLayoutManager(getContext()));
        aboutDogsAdapter = new AboutDogsAdapter(getContext());
        binding.recyclerAboutDogs.setAdapter(aboutDogsAdapter);

        aboutDogsViewModel = new ViewModelProvider(this.getActivity()).get(AboutDogsViewModel.class);

        /*Bundle bundle = this.getArguments();
        String breedDog = bundle.getString("breedDog");*/

        binding.searchBreedBtn.setOnClickListener(
                view1 -> aboutDogsViewModel.getInfo(binding.searchBreedEt.getText().toString()).observe(getViewLifecycleOwner(), aboutDogsModels -> {
            System.out.println(aboutDogsModels.size());
            aboutDogsAdapter.setInfoAboutDogs(aboutDogsModels);
        }));

    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }*/
}