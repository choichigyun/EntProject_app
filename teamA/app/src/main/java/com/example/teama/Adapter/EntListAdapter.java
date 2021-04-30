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

import com.bumptech.glide.Glide;
import com.example.teama.DTO.EntListDTO;
import com.example.teama.R;
import com.example.teama.user.ShopDetailActivity;

import java.util.ArrayList;

import static com.example.teama.user.ShopListActivity.selItem;

public class EntListAdapter extends RecyclerView.Adapter<EntListAdapter.ItemViewHolder>{
    private static final String TAG = "main:entListAdapter";
    Context mContext;
    ArrayList<EntListDTO> arrayList;

    public EntListAdapter(Context mContext, ArrayList<EntListDTO> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.entlistview, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d("main:adater", "" + position);

        EntListDTO item = arrayList.get(position);
        holder.setItem(item);

        if (position%2 == 0){
            holder.myListImage1.setImageResource(R.drawable.heart1);
        }else{
            holder.myListImage1.setImageResource(R.drawable.heart2);
        }


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position);

                selItem = arrayList.get(position);
                Intent intent = new Intent(mContext, ShopDetailActivity.class);
                intent.putExtra("selItem", selItem);
                intent.putExtra("position", position);
                mContext.startActivity(intent);


            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    // 어댑터에 매소드 만들기

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        arrayList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public EntListDTO getItem(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, EntListDTO item){
        arrayList.set(position, item);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<EntListDTO> arrayList){
        this.arrayList = arrayList;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout parentLayout;
        public TextView ent_name, ent_distance, ent_location, ent_time;
        public ImageView ent_image, myListImage1;


        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            ent_name = itemView.findViewById(R.id.ent_name);
            ent_distance = itemView.findViewById(R.id.ent_distance);
            ent_location = itemView.findViewById(R.id.ent_location);
            ent_time = itemView.findViewById(R.id.ent_time);
            myListImage1 = itemView.findViewById(R.id.myListImage1);
            ent_image = itemView.findViewById(R.id.ent_image);

        }

        public void setItem(EntListDTO item){
            ent_name.setText(item.getEnt_name());
            ent_time.setText(item.getOpen_time()+item.getClose_time());
            ent_distance.setText("거리");
            ent_location.setText(item.getEnt_id());
            Glide.with(itemView).load(item.getEnt_pic()).into(ent_image);
        }
    }

}



