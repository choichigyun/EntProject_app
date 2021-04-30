package com.example.teama.ent;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teama.ATask.EntMenuListSelect;

import com.example.teama.Adapter.EntMenuAdapter;
import com.example.teama.DTO.EntMenuDTO;
import com.example.teama.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.teama.Common.CommonMethod.isNetworkConnected;

public class EntMenuListActivity extends AppCompatActivity {
    public static EntMenuDTO selItem = null;

    EntMenuListSelect entMenuListSelect;

    RecyclerView recyclerView;
    EntMenuAdapter adapter;
    ArrayList<EntMenuDTO> EntMenuList;

    ImageButton menuUpdate, menuDelete;

    ProgressDialog progressDialog;

    FloatingActionButton menuInsert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_menu_list);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new EntMenuAdapter(this, EntMenuList);

        menuInsert = findViewById(R.id.menuInsert);

        menuUpdate = findViewById(R.id.menuUpdate);
        menuDelete = findViewById(R.id.menuDelete);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        // 데이터베이스와 연동
        if(isNetworkConnected(this) == true){
            entMenuListSelect = new EntMenuListSelect(EntMenuList, adapter, progressDialog);
            entMenuListSelect.execute();
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // 이미 화면이 있을때 받는곳
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

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
            entMenuListSelect = new EntMenuListSelect(EntMenuList, adapter, progressDialog);
            entMenuListSelect.execute();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void entMenuUpdateClicked(View view){
        Intent intent = new Intent(getApplicationContext(), EntMenuAjustActivity.class);
        startActivity(intent);
    }

    public void entMenuDeleteClicked(View view){
        AlertDialog.Builder build = new AlertDialog.Builder(EntMenuListActivity.this);
        build.setIcon(R.mipmap.ic_launcher);
        build.setMessage("삭제하시겠습니까?");

        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(EntMenuListActivity.this, "삭제되었습니다.",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        build.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(EntMenuListActivity.this, "취소되었습니다.",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    public void menuInsertClicked(View view){
        Intent intent = new Intent(getApplicationContext(), EntMenuInsertActivity.class);
        startActivity(intent);
    }

}