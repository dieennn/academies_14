package com.dicoding.intifada.submission2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.intifada.submission2.R;
import com.dicoding.intifada.submission2.activity.DetailTvShowActivity;
import com.dicoding.intifada.submission2.model.TvShow;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvViewAdapter> {
    private ArrayList<TvShow> listTvShow;
    private Context context;

    public TvShowAdapter(ArrayList<TvShow> list) {
        this.listTvShow = list;
    }

    @NonNull
    @Override
    public TvViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        this.context = viewGroup.getContext();
        return new TvViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewAdapter holder, int position) {
        final TvShow tvShow = listTvShow.get(position);

        Glide.with(holder.itemView.getContext())
                .load(tvShow.getPhotoTv())
                .into(holder.imgPhotoTv);

        holder.tvNameTv.setText(tvShow.getNameTv());
        holder.tvDateTv.setText(tvShow.getDateTv());

        holder.tvScoreTv.setText(tvShow.getScore());
        holder.tvLanguageTv.setText(tvShow.getLanguage());
        holder.tvRuntimeTv.setText(tvShow.getRuntime());
        holder.tvDescriptionTv.setText(tvShow.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TvShow tvShow1 = new TvShow();
                tvShow1.setPhotoTv(tvShow.getPhotoTv());
                tvShow1.setPhotoPosterTv(tvShow.getPhotoPosterTv());
                tvShow1.setNameTv(tvShow.getNameTv());
                tvShow1.setYearTv(tvShow.getYearTv());
                tvShow1.setOverviewTv(tvShow.getOverviewTv());

                Intent intent = new Intent(context, DetailTvShowActivity.class);
                intent.putExtra("EXTRA_TV", tvShow1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTvShow.size();
    }

    class TvViewAdapter extends RecyclerView.ViewHolder {
        ImageView imgPhotoTv;
        TextView tvNameTv, tvDateTv;

        TextView tvScoreTv;
        TextView tvLanguageTv;
        TextView tvRuntimeTv;
        TextView tvDescriptionTv;

        TvViewAdapter(@NonNull View itemView) {
            super(itemView);
            imgPhotoTv = itemView.findViewById(R.id.img_photo_movie);
            tvNameTv = itemView.findViewById(R.id.tv_name_movie);
            tvDateTv = itemView.findViewById(R.id.tv_date_movie);

            tvScoreTv = itemView.findViewById(R.id.tv_score_movie);
            tvLanguageTv = itemView.findViewById(R.id.tv_languange_movie);
            tvRuntimeTv = itemView.findViewById(R.id.tv_runtime_movie);
            tvDescriptionTv = itemView.findViewById(R.id.tv_description_movie);
        }
    }
}