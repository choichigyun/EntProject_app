package com.example.teama;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginFragment extends AppCompatActivity {

    //버튼, 프래그먼트 찾기
    LoginActivityEnt loginActivityEnt;
    LoginActivityUser loginActivityUser;
    ImageButton user_login, ent_login;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fragment);

        //프래그먼트 객체
        loginActivityUser = new LoginActivityUser();
        loginActivityEnt = new LoginActivityEnt();

        //프래그먼트 초기화
        user_login = findViewById(R.id.user_login);
        ent_login = findViewById(R.id.ent_login);


        getSupportFragmentManager().beginTransaction().replace(R.id.login, loginActivityUser).commit();


        ent_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.page_login, loginActivityEnt).commit();
            }
        });
        //사업자 로그인을 눌렀을 때


        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.page_login, loginActivityUser).commit();
            }
        });
        //일반인 로그인을 눌렀을 때
    }
}
