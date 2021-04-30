package com.example.teama.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teama.R;

public class AdminMainActivity extends AppCompatActivity {

    ImageView member_manage, ent_manage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        //회원 관리
        findViewById(R.id.member_manage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminUserMemberActivity.class);
                startActivity(intent);
            }
        });


        //사업자 관리
        findViewById(R.id.ent_manage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminEntMemberActivity.class);
                startActivity(intent);
            }
        });
    }
}