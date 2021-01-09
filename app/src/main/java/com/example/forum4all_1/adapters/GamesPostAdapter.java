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
import com.example.forum4all_1.homebuttons.GamesPost;
import com.example.forum4all_1.homebuttons.GamesPostDetailActivity;

import java.util.List;

public class GamesPostAdapter extends RecyclerView.Adapter<GamesPostAdapter.MyViewHolder> {

    Context mContext;
    List<GamesPost> mData;

    public GamesPostAdapter(Context mContext, List<GamesPost> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View post = LayoutInflater.from(mContext).inflate(R.layout.layout_games_listpost,parent, false);
        return new MyViewHolder(post);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.gamesTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.gamesPost);

    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView gamesTitle;
        ImageView gamesPost;



        public MyViewHolder(View itemView) {
            super(itemView);

            gamesTitle = itemView.findViewById(R.id.gameslisttitle);
            gamesPost = itemView.findViewById(R.id.gameslistpostimg);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent gamesPostDetailActivity = new Intent(mContext, GamesPostDetailActivity.class);
                    int position = getAdapterPosition();

                    gamesPostDetailActivity.putExtra("title",mData.get(position).getTitle());
                    gamesPostDetailActivity.putExtra("postImage",mData.get(position).getPicture());
                    gamesPostDetailActivity.putExtra("description",mData.get(position).getDescription());
                    gamesPostDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    // will fix this later i forgot to add user name to post object
                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername);
                    long timestamp  = (long) mData.get(position).getTimeStamp();
                    gamesPostDetailActivity.putExtra("postDate",timestamp) ;
                    gamesPostDetailActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(gamesPostDetailActivity);

                }
            });

        }
    }
}
