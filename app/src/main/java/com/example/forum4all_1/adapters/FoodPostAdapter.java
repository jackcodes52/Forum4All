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
import com.example.forum4all_1.homebuttons.FoodPost;
import com.example.forum4all_1.homebuttons.FoodPostDetailActivity;

import java.util.List;

public class FoodPostAdapter extends RecyclerView.Adapter<FoodPostAdapter.MyViewHolder> {

    Context mContext;
    List<FoodPost> mData;

    public FoodPostAdapter(Context mContext, List<FoodPost> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View post = LayoutInflater.from(mContext).inflate(R.layout.layout_food_listpost,parent, false);
        return new MyViewHolder(post);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.foodTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.foodPost);

    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodTitle;
        ImageView foodPost;



        public MyViewHolder(View itemView) {
            super(itemView);

            foodTitle = itemView.findViewById(R.id.foodlisttitle);
            foodPost = itemView.findViewById(R.id.foodlistpostimg);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent foodPostDetailActivity = new Intent(mContext, FoodPostDetailActivity.class);
                    int position = getAdapterPosition();

                    foodPostDetailActivity.putExtra("title",mData.get(position).getTitle());
                    foodPostDetailActivity.putExtra("postImage",mData.get(position).getPicture());
                    foodPostDetailActivity.putExtra("description",mData.get(position).getDescription());
                    foodPostDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    // will fix this later i forgot to add user name to post object
                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername);
                    long timestamp  = (long) mData.get(position).getTimeStamp();
                    foodPostDetailActivity.putExtra("postDate",timestamp) ;
                    foodPostDetailActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(foodPostDetailActivity);

                }
            });

        }
    }
}
