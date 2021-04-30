package com.example.teama.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teama.DTO.UserReviewDTO;
import com.example.teama.R;

import java.util.ArrayList;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ItemViewHolder> {
    private static final String TAG = "main:ReviewListAdapter";
    ArrayList<UserReviewDTO> arrayList;
    Context context;
    ImageView reviewImage;
    Dialog dialog;

    public ReviewListAdapter(ArrayList<UserReviewDTO> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.reviewlist, parent, false);
        reviewImage = itemView.findViewById(R.id.reviewImage);

        reviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d("main:adater", "" + position);

        UserReviewDTO dto = arrayList.get(position);
        holder.setItem(dto);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        arrayList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public UserReviewDTO getItem(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, UserReviewDTO item){
        arrayList.set(position, item);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<UserReviewDTO> arrayList){
        this.arrayList = arrayList;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView user_review;
        public TextView user_nick;
        public TextView user_star_score;
        public ImageView user_picture, reviewImage;



        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            user_review = itemView.findViewById(R.id.user_review);
            user_picture = itemView.findViewById(R.id.user_picture);
            user_nick = itemView.findViewById(R.id.user_nick);
            user_star_score = itemView.findViewById(R.id.user_star_score);
            reviewImage = itemView.findViewById(R.id.reviewImage);


        }

        public void setItem(UserReviewDTO item){

            user_nick.setText(item.getUsers_nick());
            user_review.setText(item.getReview());
            user_picture.setImageResource(R.drawable.user);
            Glide.with(itemView).load(item.getRvpicture_path()).into(reviewImage);

        }
    }



}
