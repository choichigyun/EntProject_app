package com.example.teama.Admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teama.ATask.EntDelete;
import com.example.teama.ATask.UserReviewSelect;
import com.example.teama.ATask.entMenuSelect;
import com.example.teama.Adapter.ReviewListAdapter;
import com.example.teama.DTO.EntListDTO;
import com.example.teama.DTO.UserReviewDTO;
import com.example.teama.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static com.example.teama.Common.CommonMethod.isNetworkConnected;

public class AdminEntDetailActivity extends AppCompatActivity {

    TextView admin_ent_id,admin_ent_nick,admin_ent_proof;
    TextView admin_ent_detail_name, admin_ent_detail_time;
    ImageView admin_ent_detail_mainmenu1, admin_ent_detail_mainmenu2;
    AlertDialog dialog;
    ImageView ent_delete;
    EntDelete entDelete;
    Activity activity;

    ArrayList<EntListDTO> menuList;
    com.example.teama.ATask.entMenuSelect entMenuSelect;

    UserReviewSelect userReviewSelect;
    ViewGroup view;
    ReviewListAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<UserReviewDTO> reviewList;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ent_detail);
        activity = AdminEntDetailActivity.this;

        menuList = new ArrayList<EntListDTO>();
        Intent intent = getIntent();
        EntListDTO ent_detail = (EntListDTO) intent.getSerializableExtra("selItem");
        int position = intent.getIntExtra("position", 0);

        ent_delete = findViewById(R.id.ent_delete);
        admin_ent_id = findViewById(R.id.admin_ent_id);
        admin_ent_nick = findViewById(R.id.admin_ent_nick);
        admin_ent_proof = findViewById(R.id.admin_ent_proof);
        admin_ent_detail_name = findViewById(R.id.admin_ent_detail_name);
        admin_ent_detail_time = findViewById(R.id.admin_ent_detail_time);
        admin_ent_detail_mainmenu1 = findViewById(R.id.admin_ent_detail_mainmenu1);
        admin_ent_detail_mainmenu2 = findViewById(R.id.admin_ent_detail_mainmenu2);

        admin_ent_detail_name.setText(ent_detail.getEnt_name());
        admin_ent_detail_time.setText(ent_detail.getOpen_time() + " ~ " + ent_detail.getClose_time());
        admin_ent_id.setText(ent_detail.getEnt_id());
        admin_ent_nick.setText(ent_detail.getEnt_nick());
        admin_ent_proof.setText(ent_detail.getEnt_proof());


        ent_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupImgXml(ent_detail, activity);
            }
        });

        reviewList = new ArrayList<UserReviewDTO>();
        adapter = new ReviewListAdapter(reviewList, this);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        if(isNetworkConnected(this) == true){
            userReviewSelect = new UserReviewSelect(reviewList, adapter, progressDialog, ent_detail.getEnt_nick());
            try {
                userReviewSelect.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

        if(isNetworkConnected(this) == true){
            entMenuSelect = new entMenuSelect(ent_detail.getEnt_id(), menuList);
            try {
                entMenuSelect.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

        if(menuList.size() > 0){
            Glide.with(getBaseContext()).load(menuList.get(0).getEnt_menu_picture()).into(admin_ent_detail_mainmenu1);
            Glide.with(getBaseContext()).load(menuList.get(1).getEnt_menu_picture()).into(admin_ent_detail_mainmenu2);
        }

        admin_ent_detail_mainmenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = MenuPopupImgXml(menuList.get(0));
            }
        });

        admin_ent_detail_mainmenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = MenuPopupImgXml(menuList.get(1));
            }
        });

    }


   private ViewGroup popupImgXml(EntListDTO dto, Activity activity) {
        // xml 파일 생성
        // 화면을 inflate 시켜 setView 한다
        LayoutInflater inflater = LayoutInflater.from(this);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.deletepopup, null);
        view.findViewById(R.id.YES).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entDelete = new EntDelete(dto.getEnt_id());
                entDelete.execute();
                activity.finish();
                Intent intent = new Intent(getApplicationContext(), AdminEntMemberActivity.class);
                startActivity(intent);
                Toast.makeText(AdminEntDetailActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
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

    private ViewGroup MenuPopupImgXml(EntListDTO dto) {
        // xml 파일 생성
        // 화면을 inflate 시켜 setView 한다
        LayoutInflater inflater = LayoutInflater.from(this);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.menudetail, null);
        TextView menuPrice = view.findViewById(R.id.menuPrice);
        TextView menuExplan = view.findViewById(R.id.menuExplan);
        ImageView menuView = view.findViewById(R.id.menuView);
        ImageView x = view.findViewById(R.id.x);
        x.setImageResource(R.drawable.heart1);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });
        Glide.with(getBaseContext()).load(dto.getEnt_menu_picture()).into(menuView);
        menuPrice.setText(NumberFormat.getCurrencyInstance(Locale.getDefault()).format(dto.getEnt_menu_price()));
        menuExplan.setText(dto.getEnt_menu_detail());
        // 알람창 띄우기
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dto.getEnt_menu()).setView(view);

        /*builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });*/
        dialog = builder.create();
        dialog.show();
        return view;
    }

}