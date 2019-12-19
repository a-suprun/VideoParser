package com.example.videoparser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoHolder> {

    private List<Video> videoList;
    private Context context;

    VideosAdapter(List <Video> videos, Context context) {
        this.videoList = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public VideosAdapter.VideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item, viewGroup, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int i) {
        Video video = videoList.get(i);
        holder.bind(video);
//        holder.itemView.setTag(video);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    static final class VideoHolder extends RecyclerView.ViewHolder {

        private TextView titleView;
        private TextView descriptionView;
        private TextView durationView;


        VideoHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.video_item_title);
            descriptionView = itemView.findViewById(R.id.video_item_description);
            durationView = itemView.findViewById(R.id.video_item_duration);
        }

        void bind(Video video) {
            titleView.setText(video.getTitle());
            descriptionView.setText(video.getDescription());
            durationView.setText(video.getDuration());
        }
    }
}