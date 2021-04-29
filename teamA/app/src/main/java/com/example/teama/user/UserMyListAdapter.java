package com.example.teama.user;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.teama.R;
import com.example.teama.UserMyListDto;

import java.util.ArrayList;

public class UserMyListAdapter extends BaseAdapter {
    private static final String TAG = "main:UserMyListAdapter";

    Context context;
    ArrayList<UserMyListDto> dtos;
    Point size;
    LayoutInflater inflater;

    public UserMyListAdapter(Context context, ArrayList<UserMyListDto> dtos, Point size) {
        this.context = context;
        this.dtos = dtos;
        this.size = size;

        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addDto(UserMyListDto dto){
        dtos.add(dto);
    }
    // ArrayList<SingerDto> 에서 position에 있는 dto 제거
    public void delDto(int position){
        dtos.remove(position);
    }


    @Override
    public int getCount() {
        return dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: " + position);

        // 2. singerview가 선언된 객체 생성
        user_list_view view;

        // 화면을 다 만들지 않고 몇개의 화면만 생성해서 데이터만 갱신한다
        if(convertView == null){
            // 3. convertView 객체에 xml 파일을 붙여서 화면 생성
            convertView = inflater.inflate(R.layout.user_list_view,
                    parent, false);
            view = new user_list_view();
            // 4. convertView 와 2번에 선언된 객체와 연결
            view.name = convertView.findViewById(R.id.ent_name);
            view.position = convertView.findViewById(R.id.ent_position);
            view.time = convertView.findViewById(R.id.ent_time);
            view.ent_photo = convertView.findViewById(R.id.ent_photo);
            view.ent_trash = convertView.findViewById(R.id.ent_Trash);


            convertView.setTag(view);
        }else {
            view = (user_list_view) convertView.getTag();
        }

        // 각 화면에 데이터 가져옴
        UserMyListDto dto = dtos.get(position);
        String name = dto.getEnt_name();
        String position1 = dto.getEnt_position();
        String time = dto.getEnt_time();
        int ent_photo = dto.getEnt_photo();
        int ent_trash = dto.getEnt_trash();

        // 데이터를 셋팅
        view.name.setText(name);
        view.position.setText(position1);
        view.time.setText(time);
        view.ent_photo.setImageResource(ent_photo);
        view.ent_trash.setImageResource(ent_trash);

        //이미지뷰 클릭 시
        view.ent_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this, "가게 상세정보로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(),ShopDetailActivity.class);
                context.startActivity(intent);
            }
        });
        
        //삭제버튼클릭시
        view.ent_trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setIcon(R.mipmap.ic_launcher);
                ad.setMessage("삭제하시겠습니까?");

                ad.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


                ad.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });

        return convertView;
    }



    // 1. singerview에 선언된 모든것을 정의함
    public class user_list_view {
        public ImageView ent_photo, ent_trash;
        public TextView name, position, time;
    }

}


