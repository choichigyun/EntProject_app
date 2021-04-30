package com.example.teama;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


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

