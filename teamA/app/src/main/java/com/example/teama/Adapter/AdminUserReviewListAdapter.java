package com.example.teama.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teama.ATask.ReviewDelete;
import com.example.teama.Admin.AdminUserDetailActivity;
import com.example.teama.DTO.UserMemberDTO;
import com.example.teama.DTO.UserReviewDTO;
import com.example.teama.R;

import java.util.ArrayList;

public class AdminUserReviewListAdapter extends RecyclerView.Adapter<AdminUserReviewListAdapter.ItemViewHolder> {
    private static final String TAG = "main:ReviewListAdapter";
    ArrayList<UserReviewDTO> arrayList;
    Context context;
    ImageView reviewImage;
    Dialog dialog;
    UserMemberDTO member_detail;
    Activity activity;

    public AdminUserReviewListAdapter(ArrayList<UserReviewDTO> arrayList, Context context, UserMemberDTO member_detail, Activity activity) {
        this.arrayList = arrayList;
        this.context = context;
        this.member_detail = member_detail;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.admin_user_reviewlist, parent, false);
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
        holder.review_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupImgXml(dto, member_detail);
            }
        });

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
        public ImageView review_delete;



        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            user_review = itemView.findViewById(R.id.user_review);
            user_picture = itemView.findViewById(R.id.user_picture);
            user_nick = itemView.findViewById(R.id.user_nick);
            user_star_score = itemView.findViewById(R.id.user_star_score);
            reviewImage = itemView.findViewById(R.id.reviewImage);
            review_delete = itemView.findViewById(R.id.review_delete);

        }

        public void setItem(UserReviewDTO item){

            user_nick.setText(item.getUsers_nick());
            user_review.setText(item.getReview());
            user_picture.setImageResource(R.drawable.user);
            Glide.with(itemView).load(item.getRvpicture_path()).into(reviewImage);

        }
    }

    private ViewGroup popupImgXml(UserReviewDTO dto, UserMemberDTO member_detail) {
        // xml 파일 생성
        // 화면을 inflate 시켜 setView 한다
        LayoutInflater inflater = LayoutInflater.from(this.context);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.deletepopup, null);
        view.findViewById(R.id.YES).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewDelete reviewDelete = new ReviewDelete(dto.getReview_no());
                reviewDelete.execute();
                activity.finish();
                Intent intent = new Intent(context, AdminUserDetailActivity.class);
                intent.putExtra("selItem", member_detail);
                context.startActivity(intent);
                Toast.makeText(AdminUserReviewListAdapter.this.context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        view.findViewById(R.id.NO).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog != null){
                    dialog.dismiss();

                }
            }
        });

        // 알람창 띄우기
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setView(view);

        dialog = builder.create();
        dialog.show();
        notifyDataSetChanged();
        return view;

    }



}

