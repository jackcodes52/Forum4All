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
import com.example.forum4all_1.homebuttons.TechPost;
import com.example.forum4all_1.homebuttons.TechPostDetailActivity;

import java.util.List;

public class TechPostAdapter extends RecyclerView.Adapter<TechPostAdapter.MyViewHolder> {

    Context mContext;
    List<TechPost> mData;

    public TechPostAdapter(Context mContext, List<TechPost> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View post = LayoutInflater.from(mContext).inflate(R.layout.layout_tech_listpost,parent, false);
        return new MyViewHolder(post);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.techTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.techPost);

    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView techTitle;
        ImageView techPost;



        public MyViewHolder(View itemView) {
            super(itemView);

            techTitle = itemView.findViewById(R.id.techlisttitle);
            techPost = itemView.findViewById(R.id.techlistpostimg);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent techPostDetailActivity = new Intent(mContext, TechPostDetailActivity.class);
                    int position = getAdapterPosition();

                    techPostDetailActivity.putExtra("title",mData.get(position).getTitle());
                    techPostDetailActivity.putExtra("postImage",mData.get(position).getPicture());
                    techPostDetailActivity.putExtra("description",mData.get(position).getDescription());
                    techPostDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    // will fix this later i forgot to add user name to post object
                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername);
                    long timestamp  = (long) mData.get(position).getTimeStamp();
                    techPostDetailActivity.putExtra("postDate",timestamp) ;
                    techPostDetailActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(techPostDetailActivity);

                }
            });

        }
    }
}
