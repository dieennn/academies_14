package com.dicoding.intifada.submission2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.intifada.submission2.R;
import com.dicoding.intifada.submission2.model.TvShow;

public class DetailTvShowActivity extends AppCompatActivity {

    ImageView imgPoster, imgBackground;
    TextView txtTitle, txtYear, txtOverview;

    public static final String EXTRA_TV = "EXTRA_TV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        imgBackground = findViewById(R.id.img_imageBGTv);
        imgPoster = findViewById(R.id.img_photoPosterTv);
        txtTitle = findViewById(R.id.tv_titleTv);
        txtYear = findViewById(R.id.tv_yearTv);
        txtOverview = findViewById(R.id.tv_overview_Tv);

        TvShow moveTv = this.getIntent().getParcelableExtra(EXTRA_TV);

        assert moveTv != null;
        Glide.with(this)
                .load(moveTv.getPhotoTv())
                .into(imgBackground);

        Glide.with(this)
                .load(moveTv.getPhotoPosterTv())
                .into(imgPoster);

        String title = moveTv.getNameTv();
        txtTitle.setText(title);
        String year = moveTv.getYearTv();
        txtYear.setText(year);
        String overview = moveTv.getOverviewTv();
        txtOverview.setText(overview);

    }
}
