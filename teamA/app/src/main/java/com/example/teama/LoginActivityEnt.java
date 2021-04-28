package com.example.teama;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.teama.ent.EntDTO;

public class LoginActivityEnt extends Fragment {
/*
    //loginDTO 받기
    public static EntDTO loginDTO = null;
    EditText logId, password;
    ImageButton btnJoin, btnLogin, btnSocial;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                btnJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), SignUpActivity.class);
                        startActivity(intent);
                        //SignUp으로 페이지 전환
                    }
                });
                //회원가입 버튼

    }
*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view =(ViewGroup) inflater.inflate(R.layout.activity_login_ent,container,false);
        return view;
    }
}