package com.example.teama.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class ShopDetailActivity extends AppCompatActivity {
    public static UserReviewDTO userReviewDTO = null;
    public static Activity activity = null;
    private static final String TAG = "main:entListDetail";

    ImageView ent_image,ent_image2,myListImage, mainMenu1, mainMenu2, review;
    TextView ent_name;
    AlertDialog dialog;
    ViewGroup view;
    TextView ent_category, ent_hour, ent_tel, ent_location;


    UserReviewSelect userReviewSelect;
    ArrayList<EntListDTO> menuList;
    com.example.teama.ATask.entMenuSelect entMenuSelect;
    ReviewListAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<UserReviewDTO> reviewList;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        activity = ShopDetailActivity.this;
        Intent intent = getIntent();
        EntListDTO selItem = (EntListDTO) intent.getSerializableExtra("selItem");
        int position = intent.getIntExtra("position", 0);
        ent_category = findViewById(R.id.ent_category);
        ent_hour = findViewById(R.id.ent_hour);
        ent_tel = findViewById(R.id.ent_tel);
        ent_location = findViewById(R.id.ent_location);

        menuList = new ArrayList<EntListDTO>();
        ent_image = findViewById(R.id.ent_image);
        ent_image2 = findViewById(R.id.ent_image2);
        ent_name = findViewById(R.id.ent_name);
        myListImage = findViewById(R.id.myListImage);
        mainMenu1 = findViewById(R.id.mainMenu1);
        mainMenu2 = findViewById(R.id.mainMenu2);
        review = findViewById(R.id.imageview);
        review.setImageResource(R.drawable.heart1);

        //리사이클뷰 연결
        reviewList = new ArrayList<UserReviewDTO>();
        adapter = new ReviewListAdapter(reviewList, this);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        if(isNetworkConnected(this) == true){
            userReviewSelect = new UserReviewSelect(reviewList, adapter, progressDialog, selItem.getEnt_nick());
            userReviewSelect.execute();
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

        //리뷰작성하러 가기
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserReviewInsertActivity.class);
                intent.putExtra("selItem", selItem);
                intent.putExtra("ent_nick", selItem.getEnt_nick());
                startActivity(intent);

            }
        });
        adapter.notifyDataSetChanged();




        /*entMenuSelect = new entMenuSelect(selItem.getEnt_id(), menuList);
        try {
            entMenuSelect.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        if(isNetworkConnected(this) == true){
            entMenuSelect = new entMenuSelect(selItem.getEnt_id(), menuList);
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



        ent_category.setText(selItem.getEnt_category());
        ent_tel.setText(selItem.getEnt_tel());
        ent_hour.setText(selItem.getOpen_time() + "~" + selItem.getClose_time());
        //ent_proof 일단 사진 경로
        Log.d(TAG, "onCreate: selItem : " + selItem.getEnt_proof());
        Glide.with(getBaseContext()).load(selItem.getEnt_pic()).into(ent_image2);
        Glide.with(getBaseContext()).load(selItem.getEnt_pic()).into(ent_image);
        if(menuList.size() > 0){
            Glide.with(getBaseContext()).load(menuList.get(0).getEnt_menu_picture()).into(mainMenu1);
            Glide.with(getBaseContext()).load(menuList.get(1).getEnt_menu_picture()).into(mainMenu2);
        }

        ent_name.setText(selItem.getEnt_name());
        if(position%2 == 0){
            myListImage.setImageResource(R.drawable.heart2);
        }else{
            myListImage.setImageResource(R.drawable.heart1);
        }

        mainMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = popupImgXml(menuList.get(0));
            }
        });

        mainMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = popupImgXml(menuList.get(1));
            }
        });



    }

    private ViewGroup popupImgXml(EntListDTO dto) {
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
        Log.d(TAG, "popupImgXml: 가격 : " + dto.getEnt_menu_price());
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
