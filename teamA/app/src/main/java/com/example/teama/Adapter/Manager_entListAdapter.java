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

import com.example.teama.Admin.AdminEntDetailActivity;
import com.example.teama.DTO.EntListDTO;
import com.example.teama.R;

import java.util.ArrayList;

import static com.example.teama.Admin.AdminEntMemberActivity.manager_entItem;

public class Manager_entListAdapter extends RecyclerView.Adapter<Manager_entListAdapter.ItemViewHolder> {
    private static final String TAG = "main:Manager_entListAdapter";
    Context mContext;
    ArrayList<EntListDTO> arrayList;

    public Manager_entListAdapter(Context mContext, ArrayList<EntListDTO> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.manager_entlist, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d("main:adater", "" + position);

        EntListDTO dto = arrayList.get(position);
        holder.setItem(dto);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manager_entItem = arrayList.get(position);
                Intent intent = new Intent(mContext, AdminEntDetailActivity.class);
                intent.putExtra("selItem", manager_entItem);
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
        private static final String TAG = "main:ItemViewHolder";
        public LinearLayout parentLayout;
        public TextView ent_name;
        public TextView ent_id;
        public ImageView ent_image;



        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            ent_id = itemView.findViewById(R.id.ent_id);
            ent_name = itemView.findViewById(R.id.ent_name);
            ent_image = itemView.findViewById(R.id.ent_image);


        }

        public void setItem(EntListDTO item){
            ent_id.setText(item.getEnt_id());
            ent_name.setText(item.getEnt_name());

        }
    }
}
