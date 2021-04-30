package com.example.teama.Admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teama.ATask.AdminUserReviewSelect;
import com.example.teama.ATask.UserDelete;
import com.example.teama.Adapter.AdminUserReviewListAdapter;
import com.example.teama.DTO.UserMemberDTO;
import com.example.teama.DTO.UserReviewDTO;
import com.example.teama.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.teama.Common.CommonMethod.isNetworkConnected;


public class AdminUserDetailActivity extends AppCompatActivity {

    TextView admin_member_id;
    TextView admin_member_nick;
    TextView view_more;
    Dialog dialog;
    UserDelete userDelete;
    Activity activity;
    RecyclerView recyclerView;
    ArrayList<UserReviewDTO> userReviewList;
    AdminUserReviewListAdapter adapter;
    AdminUserReviewSelect adminUserReviewSelect;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_detail);
        activity = AdminUserDetailActivity.this;


        admin_member_id = findViewById(R.id.admin_member_id);
        admin_member_nick = findViewById(R.id.admin_member_nick);
        view_more = findViewById(R.id.view_more);

        Intent intent = getIntent();
        UserMemberDTO member_detail = (UserMemberDTO) intent.getSerializableExtra("selItem");
        //int position = intent.getIntExtra("position", 0);

        admin_member_id.setText(member_detail.getUsers_id());
        admin_member_nick.setText(member_detail.getUsers_id());


        findViewById(R.id.user_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupImgXml(member_detail, activity);
            }
        });

        view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewIntent = new Intent(getApplicationContext(), AdminUserReviewActivity.class);
                startActivity(reviewIntent);
            }
        });

        userReviewList = new ArrayList<UserReviewDTO>();
        adapter = new AdminUserReviewListAdapter(userReviewList, this, member_detail , activity);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        if(isNetworkConnected(this) == true){
            adminUserReviewSelect = new AdminUserReviewSelect(userReviewList, adapter, progressDialog, member_detail.getUsers_nick());
            try {
                adminUserReviewSelect.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private ViewGroup popupImgXml(UserMemberDTO dto, Activity activity) {
        // xml 파일 생성
        // 화면을 inflate 시켜 setView 한다
        LayoutInflater inflater = LayoutInflater.from(this);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.deletepopup, null);
        view.findViewById(R.id.YES).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDelete = new UserDelete(dto.getUsers_id());
                userDelete.execute();
                activity.finish();
                Intent intent = new Intent(getApplicationContext(), AdminEntMemberActivity.class);
                startActivity(intent);
                Toast.makeText(AdminUserDetailActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.NO).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });
        // 알람창 띄우기
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        dialog = builder.create();
        dialog.show();
        return view;
    }
}