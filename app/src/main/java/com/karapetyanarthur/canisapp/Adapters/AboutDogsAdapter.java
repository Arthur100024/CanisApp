package com.karapetyanarthur.canisapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.karapetyanarthur.canisapp.Data.Model.AboutDogsModel;
import com.karapetyanarthur.canisapp.databinding.ListAboutDogsBinding;

import java.util.ArrayList;
import java.util.List;

public class AboutDogsAdapter extends RecyclerView.Adapter<AboutDogsAdapter.AboutDogsViewHolder> {

    private List<AboutDogsModel> aboutDogsModels = new ArrayList<>();
    private Context context;

    public AboutDogsAdapter(Context context) {
        this.context = context;
    }



    public class AboutDogsViewHolder extends RecyclerView.ViewHolder {

        ListAboutDogsBinding binding;

        public AboutDogsViewHolder(ListAboutDogsBinding item) {
            super(item.getRoot());
            binding = item;
        }

        public void bindView(AboutDogsModel aboutDogsModel){
            binding.descriptionList.setText(aboutDogsModel.getDescription());
            binding.lifeSpanList.setText(aboutDogsModel.getLifeSpan());
            binding.heightAndWeightList.setText(aboutDogsModel.getHeightAndWeight());
        }
    }

    @NonNull
    @Override
    public AboutDogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListAboutDogsBinding binding = ListAboutDogsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AboutDogsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutDogsViewHolder holder, int position) {
        AboutDogsModel aboutDogsModel = aboutDogsModels.get(position);
        holder.bindView(aboutDogsModel);
    }

    @Override
    public int getItemCount() {
        return aboutDogsModels.size();
    }

    public void setInfoAboutDogs(List<AboutDogsModel> aboutDogsModels){
        this.aboutDogsModels = aboutDogsModels;
        notifyDataSetChanged();
    }
}
