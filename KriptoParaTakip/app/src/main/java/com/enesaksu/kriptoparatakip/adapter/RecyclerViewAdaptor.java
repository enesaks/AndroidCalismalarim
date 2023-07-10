package com.enesaksu.kriptoparatakip.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enesaksu.kriptoparatakip.R;
import com.enesaksu.kriptoparatakip.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.RowHolder> {

    private ArrayList<CryptoModel> cryptoModels;
    private String[] colors = {"#4287f5", "#6ab7ff" ,"#32a852", "#9effa3", "#8a43b1" , "#c87cff", "#ff7f18","#ffc766"};

    public RecyclerViewAdaptor(ArrayList<CryptoModel> cryptoModels) {
        this.cryptoModels = cryptoModels;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cryptoModels.get(position),colors,position);

    }

    @Override
    public int getItemCount() {
        return cryptoModels.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPrice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(CryptoModel cryptoModel, String[] colors, Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            textName = itemView.findViewById(R.id.text_name);
            textPrice = itemView.findViewById(R.id.text_price);
            textName.setText(cryptoModel.currency);
            textPrice.setText(cryptoModel.price);

        }

    }
}
