package com.example.teama.ent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teama.R;

public class EntMainActivity extends AppCompatActivity {

    String ent_social = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_main);

        ImageButton btnMyPage = findViewById(R.id.btnMyPage);
        ImageButton btnLocation = findViewById(R.id.btnLocation);
        ImageButton btnManager = findViewById(R.id.btnManager);

        btnMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( ent_social == "kakao" || ent_social == "naver" ){
                    Intent intent = new Intent(getApplicationContext(), EntSocialMyPageActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), EntMyPageActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EntLocationActivity.class);
                startActivity(intent);
            }
        });

        btnManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EntManagerActivity.class);
                startActivity(intent);
            }
        });


    }
}