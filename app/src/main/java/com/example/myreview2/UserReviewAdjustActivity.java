package com.example.myreview2;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myreview2.Adapter.ReviewAdapter;
import com.example.myreview2.Dto.ReviewDTO;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserReviewAdjustActivity extends AppCompatActivity {

    private File tempFile;
    private EditText review;

    ArrayList<ReviewDTO> list;


    Point size;
    UserReviewAdjustAdapter adapter;
    ReviewAdapter adapter2;

    Button delete, submit;

    private static final String TAG = "Review";

    private Boolean isPermission = true;

    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;


//수정기능




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review_adjust);


        //디바이스 사이즈 구하기
        Point size = getDeviceSize();




        tedPermission();

        findViewById(R.id.btnGallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 권한 허용에 동의하지 않았을 경우 토스트
                if (isPermission) goToAlbum();
                else
                    Toast.makeText(view.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
            }
        });


        review = (EditText) findViewById(R.id.Review);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            review.setText(bundle.getString("review"));

        }


        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*AlertDialog.Builder builder = new AlertDialog.Builder(UserReviewAdjustActivity.this);
                builder.setMessage("수정하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(UserReviewAdjustActivity.this, MainActivity.class);
                                addDTO(dto);
                                // 화면 갱신하기
                                notifyDataSetChanged();

                                context.startActivity(intent);// 실행할 코드
                                Toast.makeText(context, "수정되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
*/
            }
        });

     /*   findViewById(R.id.btnCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 권한 허용에 동의하지 않았을 경우 토스트
                if(isPermission)  takePhoto();
                else Toast.makeText(view.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
            }
        });*/

        //디바이스 사이즈 구하기
        size = getDeviceSize();


        //adapter = new UserReviewAdjustAdapter(UserReviewAdjustActivity.this,list,size);


/*        adapter.addDTO(new ReviewDTO(ContextCompat.getDrawable(this,R.drawable.user2),"춘식이네 푸드트럭","맛있어요."));
        adapter.addDTO(new ReviewDTO(ContextCompat.getDrawable(this,R.drawable.user2),"춘식이네 푸드트럭","진짜 맛있어요."));
        adapter.addDTO(new ReviewDTO(ContextCompat.getDrawable(this,R.drawable.user2),"춘삼이네 푸드트럭","맛있어요."));
        adapter.addDTO(new ReviewDTO(ContextCompat.getDrawable(this,R.drawable.user2),"춘삼이네 푸드트럭","맛있어요."));*/



 /*    findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(adapter.context2);
                builder.setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.delDTO(position);
                                adapter.notifyDataSetChanged(); // 실행할 코드
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(adapter.context2, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();


            }
        });
*/
/*
        //리스트뷰에 어댑터를 붙여준다
        listview.setAdapter(adapter);

        //리스트뷰의 아이템 클릭했을때 이벤트 추가
        //AdapterView<?> parent : 클릭이 발생한 어댑터뷰
        //View view : 어댑터뷰 내부의, 클릭이 된 바로 그 뷰
        //int position : 어댑터 내부의 그 뷰의 위치(position)
        //long id : 클릭된 아이템의 row id
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReviewDTO dto = (ReviewDTO) adapter.getItem(position);

            }
        });
*/



    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Point getDeviceSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);

        //현재 프로젝트에서는 쓰지 않지만 가로와 세로 길이를 이렇게 빼서 사용한다
        int width = size.x;
        int height = size.y;

        return size;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e(TAG, tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }

            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();
            Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + photoUri);

            Cursor cursor = null;

            try {

                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

                Log.d(TAG, "tempFile Uri : " + Uri.fromFile(tempFile));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        } else if (requestCode == PICK_FROM_CAMERA) {

            setImage();

        }
    }

   //앨범에서 이미지 가져오기
    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    /* *//**
     *  카메라에서 이미지 가져오기
     *//*
    private void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            tempFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (tempFile != null) {

            Uri photoUri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }
*/

    //폴더 및 파일 만들기
    private File createImageFile() throws IOException {


        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "Review_" + timeStamp + "_";


        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Review/");
        if (!storageDir.exists()) storageDir.mkdirs();

        // 파일 생성
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        Log.d(TAG, "createImageFile : " + image.getAbsolutePath());

        return image;
    }


    //tempFile 을 bitmap 으로 변환 후 ImageView 에 설정한다.
    private void setImage() {

        ImageView imageView = findViewById(R.id.imageView);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());

        imageView.setImageBitmap(originalBm);


        tempFile = null;

    }

   //권한 설정
    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
                isPermission = true;

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
                isPermission = false;

            }
        };




        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();


/*
        setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(UserReviewAdjustActivity.this, "수정이 완료되었습니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),UserReviewAdjustActivity.class);
                startActivity(intent);

                dialog.dismiss();
            }
        });*/


    }

}