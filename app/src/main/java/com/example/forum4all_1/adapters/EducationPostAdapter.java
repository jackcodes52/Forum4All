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

import java.util.List;

public class EducationPostAdapter extends RecyclerView.Adapter<EducationPostAdapter.MyViewHolder> {

    Context mContext;
    List<EduPost> mData;

    public EducationPostAdapter(Context mContext, List<EduPost> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View post = LayoutInflater.from(mContext).inflate(R.layout.layout_education_listpost,parent, false);
        return new MyViewHolder(post);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.eduTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.eduPost);

    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eduTitle;
        ImageView eduPost;



        public MyViewHolder(View itemView) {
            super(itemView);

            eduTitle = itemView.findViewById(R.id.edulisttitle);
            eduPost = itemView.findViewById(R.id.edulistpostimg);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent eduPostDetailActivity = new Intent(mContext, EduPostDetailActivity.class);
                    int position = getAdapterPosition();

                    eduPostDetailActivity.putExtra("title",mData.get(position).getTitle());
                    eduPostDetailActivity.putExtra("postImage",mData.get(position).getPicture());
                    eduPostDetailActivity.putExtra("description",mData.get(position).getDescription());
                    eduPostDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    // will fix this later i forgot to add user name to post object
                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername);
                    long timestamp  = (long) mData.get(position).getTimeStamp();
                    eduPostDetailActivity.putExtra("postDate",timestamp) ;
                    eduPostDetailActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(eduPostDetailActivity);

                }
            });

        }
    }
}
