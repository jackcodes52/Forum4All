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
import com.example.forum4all_1.homebuttons.NewsPost;
import com.example.forum4all_1.homebuttons.NewsPostDetailActivity;

import java.util.List;

public class NewsPostAdapter extends RecyclerView.Adapter<NewsPostAdapter.MyViewHolder> {

    Context mContext;
    List<NewsPost> mData;

    public NewsPostAdapter(Context mContext, List<NewsPost> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View post = LayoutInflater.from(mContext).inflate(R.layout.layout_news_listpost,parent, false);
        return new MyViewHolder(post);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.newsTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.newsPost);

    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitle;
        ImageView newsPost;



        public MyViewHolder(View itemView) {
            super(itemView);

            newsTitle = itemView.findViewById(R.id.newslisttitle);
            newsPost = itemView.findViewById(R.id.newslistpostimg);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newsPostDetailActivity = new Intent(mContext, NewsPostDetailActivity.class);
                    int position = getAdapterPosition();

                    newsPostDetailActivity.putExtra("title",mData.get(position).getTitle());
                    newsPostDetailActivity.putExtra("postImage",mData.get(position).getPicture());
                    newsPostDetailActivity.putExtra("description",mData.get(position).getDescription());
                    newsPostDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    // will fix this later i forgot to add user name to post object
                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername);
                    long timestamp  = (long) mData.get(position).getTimeStamp();
                    newsPostDetailActivity.putExtra("postDate",timestamp) ;
                    newsPostDetailActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(newsPostDetailActivity);

                }
            });

        }
    }
}
