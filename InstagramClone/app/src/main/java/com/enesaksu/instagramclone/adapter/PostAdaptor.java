package com.enesaksu.instagramclone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enesaksu.instagramclone.databinding.RecyclerRowBinding;
import com.enesaksu.instagramclone.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdaptor extends RecyclerView.Adapter<PostAdaptor.PostHolder> {

    private ArrayList<Post> postArrayList;

    public PostAdaptor(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.recyclerRowBinding.rcyemailTextview.setText(postArrayList.get(position).email);
        holder.recyclerRowBinding.rcyCommentTextview.setText(postArrayList.get(position).comment);
        Picasso.get().load(postArrayList.get(position).dowloandUrl).into(holder.recyclerRowBinding.rcyIMageview);

    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{

        RecyclerRowBinding recyclerRowBinding;

        public PostHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }
}
