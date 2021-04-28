package com.example.teama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.teama.ent.EntDTO;

import java.util.ArrayList;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.teama.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SignUpActivity extends AppCompatActivity {

    //메뉴와 보여줄 화면
    ImageButton user, ent;
    SignUpEnt signUpEnt;
    SignUpUser signUpUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_fragment);

        // 프래그먼트를 사용할때는 항상 객체를 생성해준다.
        signUpEnt = new SignUpEnt();
        signUpUser = new SignUpUser();

        // 프레그먼트 초기화
        getSupportFragmentManager().beginTransaction().replace(R.id.page_login, signUpUser).commit();


        ent = findViewById(R.id.ent);
        user = findViewById(R.id.user);

        ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction().replace(R.id.page_login, signUpEnt).commit();
            }
        });

        user.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction().replace(R.id.page_login, signUpUser).commit();
            }
        });
    }


}

