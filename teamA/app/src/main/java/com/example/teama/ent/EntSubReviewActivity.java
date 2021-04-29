package com.example.teama.ent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teama.R;

public class EntSubReviewActivity extends AppCompatActivity {

    ImageButton sub_review_enter,sub_review_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_sub_review);
        sub_review_enter=findViewById(R.id.sub_review_enter);
        sub_review_cancel = findViewById(R.id.sub_review_cancel);


        sub_review_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //review 내용 작성

                Intent intent = new Intent(getApplicationContext(),EntReviewActivity.class);
                startActivity(intent);
                Toast.makeText(EntSubReviewActivity.this, "리뷰 등록 완료!", Toast.LENGTH_SHORT).show();
                //ent review 페이지로 복귀
            }
        });
        //db로 댓글 등록


        sub_review_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //review 내용 작성

                Intent intent = new Intent(getApplicationContext(),EntReviewActivity.class);
                startActivity(intent);

                //ent review 페이지로 복귀
            }
        });
        //댓글달기취소

    }}