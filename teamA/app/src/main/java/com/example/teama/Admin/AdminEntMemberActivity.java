package com.example.teama.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teama.ATask.Manager_entSelect;
import com.example.teama.Adapter.Manager_entListAdapter;
import com.example.teama.DTO.EntListDTO;
import com.example.teama.R;

import java.util.ArrayList;

import static com.example.teama.Common.CommonMethod.isNetworkConnected;

public class AdminEntMemberActivity extends AppCompatActivity {
    public static EntListDTO manager_entItem = null;

    Manager_entSelect listSelect;

    ArrayList<EntListDTO> myItemArrayList;

    RecyclerView recyclerView;
    Manager_entListAdapter adapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ent_member);

        // 리사이클러 뷰 시작
        myItemArrayList = new ArrayList();
        adapter = new Manager_entListAdapter(this, myItemArrayList);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


        if(isNetworkConnected(this) == true){
            listSelect = new Manager_entSelect(myItemArrayList, adapter, progressDialog);
            listSelect.execute();
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

    }

}