package com.example.teama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.teama.ent.EntDTO;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    //loginDTO 받기
    public static EntDTO loginDTO = null;
    EditText logId, password;
    ImageButton btnJoin, btnLogin, btnSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logId = findViewById(R.id.logId);
        password = findViewById(R.id.password);
        btnJoin = findViewById(R.id.btnJoin);
        btnLogin = findViewById(R.id.btnLogin);
        btnSocial = findViewById(R.id.btnSocial);

/*        btnLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (logId.getText().toString().length() != 0 && password.getText().toString().length() != 0) {
                    String id = logId.getText().toString();
                    LoginSelect loginSelect = new LoginSelect(logId, password);
                    try {
                        LoginSelect.execute().get();
                        //login 가능자인지 판단
                    } catch (ExecutionException e) {
                        e.getMessage();
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "아이디와 암호를 모두 입력하세요", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", "아이디와 암호를 모두 입력하세요 !!!");
                    return;
                }

                if (EntDTO != null) {
                    Toast.makeText(LoginActivity.this, "로그인 되었습니다 !!!", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", EntDTO + "님 로그인 되었습니다 !!!");

                } else {
                    Toast.makeText(LoginActivity.this, "아이디나 비밀번호가 일치안함 !!!", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", "아이디나 비밀번호가 일치안함 !!!");
                    logId.setText("");
                    password.setText("");
                    logId.requestFocus();
                }*/

                //회원가입
                btnJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intent);
                        //SignUp으로 페이지 전환
                    }
                });
                //회원가입

/*                //소셜 로그인
                btnSocial.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View btnSocial) {

                    }
                });
                //소셜 로그인

            }//onclick
        });*/
       /* private void checkDangerousPermissions () {
            String[] permissions = {
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_WIFI_STATE
            };

            int permissionCheck = PackageManager.PERMISSION_GRANTED;
            for (int i = 0; i < permissions.length; i++) {
                permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
                if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                    break;
                }
            }

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                    Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
                } else {
                    ActivityCompat.requestPermissions(this, permissions, 1);
                }
            }
        }

*/
    }
}