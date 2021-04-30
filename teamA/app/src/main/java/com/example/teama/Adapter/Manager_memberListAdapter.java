package com.example.teama.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teama.Admin.AdminUserDetailActivity;
import com.example.teama.DTO.UserMemberDTO;
import com.example.teama.R;

import java.util.ArrayList;

import static com.example.teama.Admin.AdminUserMemberActivity.manager_memberItem;

public class Manager_memberListAdapter extends RecyclerView.Adapter<Manager_memberListAdapter.ItemViewHolder> {
    private static final String TAG = "main:Manager_entListAdapter";
    Context mContext;
    ArrayList<UserMemberDTO> arrayList;

    public Manager_memberListAdapter(Context mContext, ArrayList<UserMemberDTO> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.manager_memberlist, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d("main:adater", "" + position);

        UserMemberDTO dto = arrayList.get(position);
        holder.setItem(dto);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manager_memberItem = arrayList.get(position);
                Intent intent = new Intent(mContext, AdminUserDetailActivity.class);
                intent.putExtra("selItem", manager_memberItem);
                intent.putExtra("position", position);
                mContext.startActivity(intent);

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
    public UserMemberDTO getItem(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, UserMemberDTO item){
        arrayList.set(position, item);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<UserMemberDTO> arrayList){
        this.arrayList = arrayList;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "main:ItemViewHolder";
        public LinearLayout parentLayout;
        public TextView member_id;
        public TextView member_nick;
        public ImageView member_image;



        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            member_id = itemView.findViewById(R.id.member_id);
            member_nick = itemView.findViewById(R.id.member_nick);
            member_image = itemView.findViewById(R.id.member_image);


        }

        public void setItem(UserMemberDTO item){
            member_id.setText(item.getUsers_id());
            member_nick.setText(item.getUsers_nick());
        }
    }
}
