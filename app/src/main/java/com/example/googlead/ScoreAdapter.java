package com.example.googlead;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    List<Category> imageLists;
    Context context;

    public ScoreAdapter(List<Category> imageLists, Context context) {
        this.imageLists = imageLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragmenttwo_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category imageList=imageLists.get(position);
        holder.tvname.setText(imageList.getName());
        holder.tvscore.setText(imageList.getScore()+" skill IQ score");
        holder.tvcountry.setText(imageList.getCountry());

        Picasso.with(context)
                .load(imageList.getImage())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvname;
        private TextView tvscore;
        private TextView tvcountry;
        private ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tvname=(TextView)itemView.findViewById(R.id.nameTV);
            tvscore=(TextView)itemView.findViewById(R.id.scoreTV);
            tvcountry=(TextView)itemView.findViewById(R.id.countryTV);
            img=(ImageView)itemView.findViewById(R.id.image_view);
        }
    }
}