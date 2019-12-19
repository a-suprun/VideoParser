package com.example.videoparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Video> videos = new ArrayList<>();
    private ProgressBar progressBar;
    private VideosAdapter videosAdapter;
    private RecyclerView recyclerView;

    private final String url = "https://www.udemy.com/course/learn-flutter-dart-to-build-ios-android-apps/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.activity_main_progressBar);
        recyclerView = findViewById(R.id.activity_main_rv_videos);
        videosAdapter = new VideosAdapter(videos, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(videosAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        new Content().execute();
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Elements titles, descriptions, durations;
                //Connect to the website
                Document document = Jsoup.connect(url).get();
                //Get title of the video
                titles = document.select("div.title");
                //Get description
                descriptions = document.select("div.description.collapse");
                //Get duration
                durations = document.select("span.content-summary");
                //Add all to list
                for (int i = 0; i < 13; i++) {
                    videos.add(new Video(titles.get(i).text(), descriptions.get(i).text(), durations.get(i).text()));
                }
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
            videosAdapter.notifyDataSetChanged();
        }
    }
}