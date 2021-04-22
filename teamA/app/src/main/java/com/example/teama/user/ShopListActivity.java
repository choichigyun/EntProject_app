package com.example.teama.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.teama.ATask.entSelect;
import com.example.teama.Adapter.EntListAdapter;
import com.example.teama.DTO.EntListDTO;
import com.example.teama.R;

import java.util.ArrayList;

import static com.example.teama.Common.CommonMethod.isNetworkConnected;

public class ShopListActivity extends AppCompatActivity {
    public static EntListDTO selItem = null;

    entSelect listSelect;

    ArrayList<EntListDTO> myItemArrayList;

    RecyclerView recyclerView;
    EntListAdapter adapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        checkDangerousPermissions();

        // 리사이클러 뷰 시작
        myItemArrayList = new ArrayList();
        adapter = new EntListAdapter(this, myItemArrayList);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        if(isNetworkConnected(this) == true){
            listSelect = new entSelect(myItemArrayList, adapter, progressDialog);
            listSelect.execute();
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

    }

/*       //추가
    public void btn1Clicked(View v){
        if(isNetworkConnected(this) == true){
            Intent intent = new Intent(getApplicationContext(), Sub1Insert.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }


    }
    // 수정
    public void btn2Clicked(View v){
        if(isNetworkConnected(this) == true){

            if(selItem != null){
                Log.d("sub1:update1", selItem.getId());

                //Intent intent = new Intent(getApplicationContext(), Sub1Update.class);
                intent.putExtra("selItem", selItem);
                startActivity(intent);

            }else {
                Toast.makeText(getApplicationContext(), "항목 선택을 해 주세요",
                        Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    // 삭제
    public void btn3Clicked(View v){
        if(isNetworkConnected(this) == true){
            if(selItem != null){
                Log.d("Sub1 : selImg => ", selItem.getImage_path());

               *//* ListDelete listDelete = new ListDelete(selItem.getId(), selItem.getImage_path());
                listDelete.execute();*//*

                // 화면갱신
                Intent refresh = new Intent(this, com.example.myproject.Sub1.class);
                startActivity(refresh);
                this.finish(); // 화면끝내기

                adapter.notifyDataSetChanged(); // adapter 갱신
            }else {
                Toast.makeText(getApplicationContext(), "항목 선택을 해 주세요(항목선택)",
                        Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show(); // 테스트 111
        }

    }

    // 돌아가기
    public void btn4Clicked(View v){
        finish();
    }*/

    // 이미 화면이 있을때 받는곳
    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("Sub1", "onNewIntent() 호출됨");

        // 새로고침하면서 이미지가 겹치는 현상 없애기 위해...
        adapter.removeAllItem();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("데이터 업로딩");
        progressDialog.setMessage("데이터 업로딩 중입니다\n" + "잠시만 기다려주세요 ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        processIntent(intent);

    }

    private void processIntent(Intent intent){
        if(intent != null){
            listSelect = new entSelect(myItemArrayList, adapter, progressDialog);
            listSelect.execute();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}