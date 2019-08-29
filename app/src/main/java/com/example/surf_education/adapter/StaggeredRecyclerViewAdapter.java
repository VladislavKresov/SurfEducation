package com.example.surf_education.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.surf_education.R;

import java.util.ArrayList;

public class StaggeredRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "StaggeredRecyclerViewAd";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUtls = new ArrayList<>();
    private Context mContext;
    private ArrayList<Boolean> isFavorite = new ArrayList<>();

    public StaggeredRecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUtls, ArrayList<Boolean> isFavorite) {
        this.mNames = names;
        this.mImageUtls = imageUtls;
        this.mContext = context;
        this.isFavorite = isFavorite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meme_cell,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(mImageUtls.get(position))
                .apply(requestOptions)
                .into(holder.image);

        holder.text.setText(mNames.get(position));

        //FIXME Тут как-то не красиво

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFavorite.set(position,!isFavorite.get(position));

                if(isFavorite.get(position)){
                    holder.like.setBackgroundResource(R.drawable.favorite_fill);
                }else {
                    holder.like.setBackgroundResource(R.drawable.favorite);
                }

            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Сделать шаринг
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageUtls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView text;
        Button like;
        Button share;

        public ViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image_meme_widget);
            this.text = itemView.findViewById(R.id.tv_memeTitle_widget);
            this.like = itemView.findViewById(R.id.btn_favorite_widget);
            this.share = itemView.findViewById(R.id.btn_share_widget);
        }
    }

}
