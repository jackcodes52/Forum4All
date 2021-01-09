package com.example.forum4all_1.adapters;

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
import com.example.forum4all_1.R;
import com.example.forum4all_1.homebuttons.EduPost;
import com.example.forum4all_1.homebuttons.EduPostDetailActivity;
import com.example.forum4all_1.homebuttons.MusicPost;
import com.example.forum4all_1.homebuttons.MusicPostDetailActivity;

import java.util.List;

public class MusicPostAdapter extends RecyclerView.Adapter<MusicPostAdapter.MyViewHolder> {

    Context mContext;
    List<MusicPost> mData;

    public MusicPostAdapter(Context mContext, List<MusicPost> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View post = LayoutInflater.from(mContext).inflate(R.layout.layout_music_listpost,parent, false);
        return new MyViewHolder(post);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.musicTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.musicPost);

    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView musicTitle;
        ImageView musicPost;



        public MyViewHolder(View itemView) {
            super(itemView);

            musicTitle = itemView.findViewById(R.id.musiclisttitle);
            musicPost = itemView.findViewById(R.id.musiclistpostimg);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent musicPostDetailActivity = new Intent(mContext, MusicPostDetailActivity.class);
                    int position = getAdapterPosition();

                    musicPostDetailActivity.putExtra("title",mData.get(position).getTitle());
                    musicPostDetailActivity.putExtra("postImage",mData.get(position).getPicture());
                    musicPostDetailActivity.putExtra("description",mData.get(position).getDescription());
                    musicPostDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    // will fix this later i forgot to add user name to post object
                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername);
                    long timestamp  = (long) mData.get(position).getTimeStamp();
                    musicPostDetailActivity.putExtra("postDate",timestamp) ;
                    musicPostDetailActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(musicPostDetailActivity);

                }
            });

        }
    }
}
