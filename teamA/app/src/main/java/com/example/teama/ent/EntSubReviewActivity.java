package com.example.teama.ent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teama.R;

public class EntSubReviewActivity extends AppCompatActivity {

    ImageButton sub_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_sub_review);



        sub_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //review 내용 작성

                Intent intent = new Intent(getApplicationContext(),EntReviewActivity.class);
                startActivity(intent);

                //ent review 페이지로 복귀
            }
        });
        //db로 댓글 등록록

    }}