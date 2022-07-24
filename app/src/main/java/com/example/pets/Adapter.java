package com.example.pets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class Adapter extends PagerAdapter {
    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<Model>models, Context context){
        this.models = models;
        this.context = context;
    }


    public int getCount(){
        return models.size();
    }

    public  boolean isViewFromObject(@NonNull View view, @NonNull Object object){
        return view.equals(object);
    }

    public Object instantiateItem (@NonNull ViewGroup container, final int position){
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item,container,false);

        ImageView imageView;
        TextView title;
        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailView.class);
                intent.putExtra("title", models.get(position).getTitle());
                intent.putExtra("image", models.get(position).getImage());
                intent.putExtra("desc", models.get(position).getDesc());
                context.startActivity(intent);
            }
        });
        container.addView(view,0);
        return view;
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((View)object);
    }
}