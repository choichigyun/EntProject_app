package com.example.teama.ent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.teama.ATask.EntUserListUpdate;
import com.example.teama.R;

import static com.example.teama.Common.CommonMethod.isNetworkConnected;

public class EntSocialMyPageActivity extends AppCompatActivity {

    EditText entName, entNick, entPhone;
    String ent_name = "", ent_nick = "", ent_phone = "";

    public String imageRealPathA, imageDbPathA;

    final int LOAD_IMAGE = 1001;
    final int LOAD_IMAGE2 = 1001;
    long fileSize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_social_my_page);

        entName = findViewById(R.id.ent_Name);
        entNick = findViewById(R.id.ent_Nick);
        entPhone = findViewById(R.id.ent_Tel);
        ImageButton entNum = findViewById(R.id.ent_Num);
        ImageButton entPic = findViewById(R.id.ent_Pic);
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);

        ImageButton btnUpdate = findViewById(R.id.btnUpdate);
        ImageButton btnCancel = findViewById(R.id.btnCancel);


        entNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);
            }
        });

        entPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView2.setVisibility(View.VISIBLE);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE2);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

    }
    public void btnUpdateClicked() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setMessage("?????????????????????????");

        builder.setNegativeButton("?????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setPositiveButton("???", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tryUpdate();
            }
        });

        builder.show();
    }

    private void tryUpdate() {
        if(isNetworkConnected(this) == true){

            if(fileSize <= 30000000){  // ??????????????? 30?????? ?????? ????????? ????????? ?????? ??????
                ent_name = entName.getText().toString();
                ent_nick = entNick.getText().toString();
                ent_phone = entPhone.getText().toString();

                EntUserListUpdate listInsert = new EntUserListUpdate(ent_name, ent_nick, ent_phone, imageDbPathA, imageRealPathA);
                listInsert.execute();

                Intent intent = new Intent(getApplicationContext(), EntMainActivity.class);
                startActivity(intent);

                finish();
            }else{
                // ????????? ??????
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("??????");
                builder.setMessage("?????? ????????? 30MB???????????? ????????? ???????????? ???????????? ????????????.\n30MB?????? ????????? ????????? ????????????!!!");
                builder.setPositiveButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }

        }else {
            Toast.makeText(this, "???????????? ???????????? ?????? ????????????.",
                    Toast.LENGTH_SHORT).show();
        }
    }


}