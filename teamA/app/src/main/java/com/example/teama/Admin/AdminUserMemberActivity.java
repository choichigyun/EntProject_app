package com.example.teama.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teama.ATask.Manager_memberSelect;
import com.example.teama.Adapter.Manager_memberListAdapter;
import com.example.teama.DTO.UserMemberDTO;
import com.example.teama.R;

import java.util.ArrayList;

import static com.example.teama.Common.CommonMethod.isNetworkConnected;

public class AdminUserMemberActivity extends AppCompatActivity {
    public static UserMemberDTO manager_memberItem = null;

    Manager_memberSelect listSelect;

    ArrayList<UserMemberDTO> myItemArrayList;

    RecyclerView recyclerView;
    Manager_memberListAdapter adapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_member);

        // 리사이클러 뷰 시작
        myItemArrayList = new ArrayList();
        adapter = new Manager_memberListAdapter(this, myItemArrayList);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


        if(isNetworkConnected(this) == true){
            listSelect = new Manager_memberSelect(myItemArrayList, adapter, progressDialog);
            listSelect.execute();
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

    }
}