package com.example.artbook;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artbook.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class artAdaptor extends RecyclerView.Adapter<artAdaptor.artHolder> {

    ArrayList<Art> artArrayList;

    public artAdaptor(ArrayList<Art> artArrayList){
        this.artArrayList = artArrayList;
    }

    @NonNull
    @Override
    public artHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new artHolder(recyclerRowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull artHolder holder, int position) {
        holder.binding.recyclerViewTextView.setText(artArrayList.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),ArtActivity.class);
                intent.putExtra("info","old");
                intent.putExtra("artId",artArrayList.get(position).id);
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return artArrayList.size();
    }

    public class artHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;

        public artHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
