package com.example.teama.ent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teama.R;
import com.example.teama.SignUpActivity;

public class EntReviewActivity extends AppCompatActivity {
    ImageView review_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_review_2);
        review_enter = findViewById(R.id.review_enter);
        review_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        EntSubReviewActivity.class);
                startActivity(intent);
                //댓글달기(sub_review로 넘어가기)
                }
            });
    }
}
