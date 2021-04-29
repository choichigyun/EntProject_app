package com.example.myreview2.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myreview2.R;
import com.example.myreview2.Dto.ReviewDTO;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    //어댑터에 데이터를 받기위해 생성자 만든다.
    // 컨텍스트와 리스트는 받아오지만 인플레이터는 안받아온다.
    Context context;
    ArrayList<ReviewDTO> list;
    Point size;


    LayoutInflater inflater;
    AlertDialog dialog;

    public ReviewAdapter(Context context, ArrayList<ReviewDTO> list, Point size) {
        this.context = context;
        this.list = list;
        this.size = size;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //인플레이터는 시스템에서 가져온다.
    }

    //리스트(list)에 항목을 추가해 줄 메서드 작성
    public void addDTO(ReviewDTO dto) {
        list.add(dto);
    }
    public void delDTO(int position) {
        list.remove(position);
    }

    //getCount() : 리스트에서 항목을 몇개나 가져와서 몇개의 화면을 만들 것인지 정하는 메서드
    @Override
    public int getCount() {
        return list.size();
    }

    //getItem() : 리스트에서 해당하는 인덱스의 데이터(사진, 이름, 전번)를 모두 가져오는 메서드
    //Object를 알아서 캐스팅해서 사용하라는 의미로 반환 타입이 Object
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
        ReviewViewHolder viewHolder;

        //화면 구성
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.reviewview, parent, false);
            viewHolder = new ReviewViewHolder();
            viewHolder.ent_id = convertView.findViewById(R.id.Ent_id);
            viewHolder.review = convertView.findViewById(R.id.Review);
            viewHolder.loginImg = convertView.findViewById(R.id.imageView1);
            viewHolder.delete = convertView.findViewById(R.id.delete);
            viewHolder.adjust = convertView.findViewById(R.id.adjust);
            viewHolder.date = convertView.findViewById(R.id.date);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ReviewViewHolder) convertView.getTag();
        }


        // 선택된 dto 가져오기
        ReviewDTO dto = list.get(position);

        //XML의 화면에 찾은 데이터 표시
        viewHolder.ent_id.setText(dto.getEnt_id());
        //viewHolder.tvName.setText(dto.getName()); // 이렇게 써도 같음
        viewHolder.review.setText(dto.getReview());
        viewHolder.date.setText(dto.getReview_date());

        //viewHolder.imageView.setImageResource(resId); //이미지 가져올시

       //이미지만 클릭했을때 기능 추가
        viewHolder.loginImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //방법 ① : 이미지뷰 추가하여 직접 붙임
                //popUpImg(list.get(position).getResId());

                //방법 ② : 미리 만들어둔 XML과 팝업창을 연결해서 보여줌
                //popupImgXml(list.get(position).getEnt_id(), list.get(position).getReview());
            }
        });




        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                       builder.setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){
                                delDTO(position);
                                notifyDataSetChanged(); // 실행할 코드
                                Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(context, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();




            }
        });

        // 리스트 수정 하기
        viewHolder.adjust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(context,UserReviewAdjustActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/
            }
        });


        return convertView;
    }

    //따로 새 자바 파일을 만들지 않고 XML의 내용을 볼 수 있게끔 만든 클래스
    public static class ReviewViewHolder {
        public ImageView loginImg, adjust, delete;
        public TextView ent_id, review, date;

    }




}