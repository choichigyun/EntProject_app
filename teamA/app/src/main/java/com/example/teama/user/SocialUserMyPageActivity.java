package com.example.teama.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teama.R;

public class SocialUserMyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_my_page);


        EditText userName = findViewById(R.id.user_Name);
        EditText userNick = findViewById(R.id.user_Nick);
        EditText userPhone = findViewById(R.id.user_Tel);
        EditText userEmail = findViewById(R.id.user_email);


        Button btnChange = findViewById(R.id.btnChange);
        Button btnCancel = findViewById(R.id.btnCancel);


        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(SocialUserMyPageActivity.this);
                ad.setIcon(R.mipmap.ic_launcher);
                ad.setMessage("수정하시겠습니까?");

                ad.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SocialUserMyPageActivity.this, "취소되었습니다", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


                ad.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(SocialUserMyPageActivity.this, "수정이 완료되었습니다", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),UserMainActivity.class);
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });
                ad.show();



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(SocialUserMyPageActivity.this);
                ad.setIcon(R.mipmap.ic_launcher);
                ad.setMessage("취소하시겠습니까?");

                ad.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


                ad.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(SocialUserMyPageActivity.this, "취소되었습니다. 메인페이지로 이동합니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),UserMainActivity.class);
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });

    }
}