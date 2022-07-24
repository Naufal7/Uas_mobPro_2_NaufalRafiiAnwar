package com.example.pets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pets.Hewan;
import com.example.pets.R;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.CompanyViewHolder> {

    private ArrayList<Hewan> animalList;
    private Context context;

    public AnimalAdapter(ArrayList<Hewan> companyList, Context context) {
        this.animalList = companyList;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimalAdapter.CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animals, parent, false);
        return new CompanyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        if (!animalList.get(position).getGambar().equals("-")) {
            Glide.with(context).load(animalList.get(position).getGambar()).into(holder.iv_pic);
        }

        holder.tv_name.setText(animalList.get(position).getNama());
        holder.tv_hewan.setText(animalList.get(position).getJenis());
        holder.tv_tanggal.setText(animalList.get(position).getTanggal());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name, tv_hewan, tv_tanggal;
        private ImageView iv_pic;

        public CompanyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_hewan = itemView.findViewById(R.id.tv_hewan);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
            iv_pic = itemView.findViewById(R.id.iv_pic);

        }
    }

}
