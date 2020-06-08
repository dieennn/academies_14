package com.dicoding.intifada.sm5.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.intifada.sm5.R;
import com.dicoding.intifada.sm5.addingmethod.CustomeOnItemClickListener;
import com.dicoding.intifada.sm5.myactivity.DetailsMovieActivity;
import com.dicoding.intifada.sm5.mymodel.FollowItemsParcelable;

import java.util.ArrayList;

public class FollowingItemsAdapter extends RecyclerView.Adapter<FollowingItemsAdapter.FollowingItemsViewHolder> {
    public static final String TAG = FollowingItemsAdapter.class.getSimpleName();
    private ArrayList<FollowItemsParcelable> followingData = new ArrayList<>();
    private Activity activity;

    public FollowingItemsAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setFollowersData(ArrayList<FollowItemsParcelable> list) {
        followingData.clear();
        followingData.addAll(list);
        notifyDataSetChanged();
    }

    private ArrayList<FollowItemsParcelable> getFollowingData() {
        return followingData;
    }

    @NonNull
    @Override
    public FollowingItemsAdapter.FollowingItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_list_movie, parent, false);
        return new FollowingItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingItemsAdapter.FollowingItemsViewHolder holder, int position) {
        holder.bind(getFollowingData().get(position));
        holder.cardViewDesc.setOnClickListener(new CustomeOnItemClickListener(position, new CustomeOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, DetailsMovieActivity.class);
                /*Uri uri = Uri.parse(CONTENT_URI + "/" + getFollowingData().get(position).getId());
                intent.setData(uri);*/
                intent.putExtra(DetailsMovieActivity.EXTRA_WHERE_FROM, TAG);
                intent.putExtra(DetailsMovieActivity.EXTRA_FOLLOWERS, getFollowingData().get(position));
                /*Timber.d("response Onclick: %s", new Gson().toJson(getFollowingData().get(position)));
                Toast.makeText(view.getContext(), new Gson().toJson(getFollowingData().get(position)), Toast.LENGTH_SHORT).show();*/
                activity.startActivityForResult(intent, DetailsMovieActivity.REQUEST_FAV);
                /*Intent intent = new Intent(activity, DetailsMovieActivity.class);
                intent.putExtra(DetailsMovieActivity.EXTRA_WHERE_FROM, TAG);
                intent.putExtra(DetailsMovieActivity.EXTRA_MOVIE, getMoviesData().get(position));
                intent.putExtra("EXTRA_MOVIE", getMoviesData().get(position));
                activity.startActivity(intent);*/
            }
        }));
    }

    @Override
    public int getItemCount() {
        return followingData.size();
    }

    class FollowingItemsViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewImg, cardViewDesc, cardViewRating;
        TextView tvTitle, tvRelease, tvRating, tvDesc;
        ImageView imgvPoster;

        FollowingItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvRelease = itemView.findViewById(R.id.tv_item_release);
            tvRating = itemView.findViewById(R.id.tv_item_rating);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);
            imgvPoster = itemView.findViewById(R.id.img_item_photo);
            cardViewImg = itemView.findViewById(R.id.card_img);
            cardViewDesc = itemView.findViewById(R.id.card_view_desc);
            cardViewRating = itemView.findViewById(R.id.card_view_rating);
        }

        void bind(FollowItemsParcelable FollowItemsParcelable) {
            String title = FollowItemsParcelable.getLogin();
            String avatar = FollowItemsParcelable.getAvatar_url();
            tvTitle.setText(title);
            if (avatar != null) {
                Glide.with(activity)
                        .load(avatar)
                        .into(imgvPoster);
            }
            /*String pathImg = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
            String title = FollowersItems.getTitle();
            String release = FollowersItems.getRelease_date();
            String voteValue = FollowersItems.getVote_average().toString();
            String overView = FollowersItems.getOverview();
            String imgUrl = FollowersItems.getPoster_path();

            AllOtherMethod allOtherMethod = new AllOtherMethod();
            if (release != null) {
                String myDate = allOtherMethod.changeFormatDate(release);
                tvRelease.setText(myDate);
            } else {
                tvRelease.setText(release);
            }

            tvTitle.setText(title);
            tvRating.setText(voteValue);
            tvDesc.setText(overView);
            if (imgUrl != null) {
                Glide.with(activity)
                        .load(pathImg + imgUrl)
                        .into(imgvPoster);
            }*/
        }
    }
}

