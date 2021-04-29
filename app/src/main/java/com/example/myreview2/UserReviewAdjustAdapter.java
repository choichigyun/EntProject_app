package com.example.myreview2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myreview2.Dto.ReviewDTO;

import java.util.ArrayList;


public class UserReviewAdjustAdapter extends BaseAdapter {

        Context context;
        ArrayList<ReviewDTO> list;
        Point size;

        LayoutInflater inflater;
        AlertDialog dialog;

        public UserReviewAdjustAdapter(Context context,ArrayList<ReviewDTO> list, Point size) {
            this.context = context;
            this.list = list;
            this.size = size;

            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        public void addDTO(ReviewDTO dto) {
        list.add(dto);
    }

         //리스트의 항목을 삭제할 메서드 작성
        public void delDTO(int position) {
            list.remove(position);
        }

        //getCount() : 리스트에서 항목을 몇개나 가져와서 몇개의 화면을 만들 것인지 정하는 메서드
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            UserReviewAdjustAdapter.ReviewViewHolder viewHolder;

            //화면 구성
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_user_review_adjust, parent, false);
                viewHolder = new UserReviewAdjustAdapter.ReviewViewHolder();
                viewHolder.Ent_id = convertView.findViewById(R.id.Ent_id);
                viewHolder.Review = convertView.findViewById(R.id.Review);
                viewHolder.imageView = convertView.findViewById(R.id.imageView1);
                viewHolder.delete = convertView.findViewById(R.id.delete);
                viewHolder.submit = convertView.findViewById(R.id.submit);


                convertView.setTag(viewHolder);
            } else {
                viewHolder = (UserReviewAdjustAdapter.ReviewViewHolder) convertView.getTag();
            }


            ReviewDTO dto = list.get(position);
            String ent_id = dto.getEnt_id();
            String review = dto.getReview();



            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("삭제하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    delDTO(position);
                                    notifyDataSetChanged(); // 실행할 코드
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();


                }
            });


            viewHolder.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("수정하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(context, MainActivity.class);
                                    addDTO(dto);
                                    // 화면 갱신하기
                                    notifyDataSetChanged();

                                    context.startActivity(intent);// 실행할 코드
                                    Toast.makeText(context, "수정되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();

                }
            });


            return convertView;
        }

        //따로 새 자바 파일을 만들지 않고 XML의 내용을 볼 수 있게끔 만든 클래스
        public static class ReviewViewHolder {
            public ImageView imageView, submit, delete;
            public TextView Ent_id, Review;

        }


    }



