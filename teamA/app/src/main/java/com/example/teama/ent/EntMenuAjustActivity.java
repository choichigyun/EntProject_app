package com.example.teama.ent;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.teama.Common.CommonMethod;
import com.example.teama.DTO.EntMenuDTO;
import com.example.teama.ATask.EntMenuListUpdate;
import com.example.teama.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.teama.Common.CommonMethod.ipConfig;
import static com.example.teama.Common.CommonMethod.isNetworkConnected;

public class EntMenuAjustActivity extends AppCompatActivity {


    EditText menu_Name, menu_Detail, menu_Price;
    String mName, mDetail;
    int mPrice;

    ImageButton photoBtn, photoLoad;

    ImageView imageView;

    public String imagePath;
    public String pImgDbPathU;
    public String imageRealPathU = "", imageDbPathU = "";

    final int CAMERA_REQUEST = 1010;
    final int LOAD_IMAGE = 1011;

    File file = null;
    long fileSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_menu_ajust);

        menu_Name = findViewById(R.id.menu_UName);
        menu_Detail = findViewById(R.id.menu_UDetail);
        menu_Price = findViewById(R.id.menu_UPrice);
        photoBtn = findViewById(R.id.btnPhoto);
        photoLoad = findViewById(R.id.btnLoad);

        imageView = findViewById(R.id.imageView);

        // ????????? ??? ??????
        Intent intent = getIntent();
        EntMenuDTO emdto = (EntMenuDTO) intent.getSerializableExtra("emdto");

        mName = emdto.getMenu_name();
        mDetail = emdto.getMenu_detail();
        mPrice = emdto.getMenu_price();
        // ????????? ??? ??? ??????
        menu_Name.setText(mName);
        menu_Detail.setText(mDetail);
        menu_Price.setText(mPrice);

        imagePath = emdto.getFdpicture_path();
        pImgDbPathU = imagePath;
        imageDbPathU = imagePath;

        imageView.setVisibility(View.VISIBLE);
        // ????????? ????????? ????????????
        Glide.with(this).load(imagePath).into(imageView);

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    try{
                        file = createFile();
                        Log.d("EntMenuAjust:FilePath ", file.getAbsolutePath());
                    }catch(Exception e){
                        Log.d("EntMenuAjust:error1", "Something Wrong", e);
                    }

                    // imageView.setVisibility(View.VISIBLE);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 ?????? ??????
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                FileProvider.getUriForFile(getApplicationContext(),
                                        getApplicationContext().getPackageName() + ".fileprovider", file));
                        Log.d("sub1:appId", getApplicationContext().getPackageName());
                    }else {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    }

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, CAMERA_REQUEST);
                    }

                }catch(Exception e){
                    Log.d("Sub1Update:error2", "Something Wrong", e);
                }

            }
        });

        photoLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);
            }
        });


    }

    private File createFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "My" + timestamp +".jpg";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File curFile = null;
        try {
            curFile = File.createTempFile(imageFileName,".jpg", storageDir);
        } catch (IOException e) {
            //  Log.d(TAG, "createFile: " + e.getMessage());;
        }

        imagePath = curFile.getAbsolutePath();
        Log.d("main:paths", imagePath);

        return  curFile;
   /*     java.text.SimpleDateFormat tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");

        String imageFileName = "My" + tmpDateFormat.format(new Date()) + ".jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir, imageFileName);

        return curFile;*/
//        java.text.SimpleDateFormat tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");
//
//        String imageFileName = "My" + tmpDateFormat.format(new Date()) + ".jpg";
//        File storageDir = Environment.getExternalStorageDirectory();
//        File curFile = new File(storageDir, imageFileName);
//
//        return curFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            galleryAddPic();
            setPic();
       /*     try {
                // ????????? ????????? ??? ????????????
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                if(newBitmap != null){
                    imageView.setImageBitmap(newBitmap);
                }else{
                    Toast.makeText(this, "???????????? null ?????????...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathU = file.getAbsolutePath();
                String uploadFileName = imageRealPathU.split("/")[imageRealPathU.split("/").length - 1];
                imageDbPathU = ipConfig + "/app/resources/" + uploadFileName;

                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));

                Log.d("Sub1Update:picPath", file.getAbsolutePath());

            } catch (Exception e){
                e.printStackTrace();
            }*/
        }else if (requestCode == LOAD_IMAGE && resultCode == RESULT_OK) {

            try {
                String path = "";
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    // Get the path from the Uri
                    path = getPathFromURI(selectedImageUri);
                }

                // ????????? ????????? ??? ????????????
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                if(newBitmap != null){
                    imageView.setImageBitmap(newBitmap);
                }else{
                    Toast.makeText(this, "???????????? null ?????????...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathU = path;
                String uploadFileName = imageRealPathU.split("/")[imageRealPathU.split("/").length - 1];
                imageDbPathU = ipConfig + "/app/resources/" + uploadFileName;

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    // ????????? ????????? ????????? ?????????
    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
        imageView.setImageBitmap(bitmap);
        imageRealPathU = file.getAbsolutePath();
        String uploadFileName = imageRealPathU.split("/")[imageRealPathU.split("/").length - 1];
        imageDbPathU = ipConfig + "/app/resources/" + uploadFileName;
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public void entMenuUpdateClicked(View view){
        android.app.AlertDialog.Builder builder =
                new android.app.AlertDialog.Builder(this);
        builder.setMessage("?????????????????????????");

        builder.setPositiveButton("???", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { tryInsert(); }
        });

        builder.setPositiveButton("?????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { finish(); }
        });

    }

    private void tryInsert() {
        if(isNetworkConnected(this) == true){
            if(fileSize <= 30000000) {  // ??????????????? 30?????? ?????? ????????? ????????? ?????? ??????
                mName = menu_Name.getText().toString();
                mDetail = menu_Detail.getText().toString();
                mPrice = Integer.parseInt(menu_Price.getText().toString());

                EntMenuListUpdate entMenuListUpdate = new EntMenuListUpdate(mName, mDetail, mPrice, pImgDbPathU, imageDbPathU, imageRealPathU);
                entMenuListUpdate.execute();

                //Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();

                Intent showIntent = new Intent(getApplicationContext(), EntMenuListActivity.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // ??? ???????????? ???????????? ???????????? ??????????????? ???????????? ?????? ????????? ???????????? ???????????? ??? ??????????????? ??????????????? ???????????? ?????????. ???, ????????? ???????????? ?????????????????? ??????????????? ??????????????? ????????? affinity(??????, ??????)??? ????????? ?????? ???????????? ????????? ???????????? ??? ??????????????? ?????????????????????.
                        Intent.FLAG_ACTIVITY_SINGLE_TOP | // ??????????????? ????????? ?????? ????????? ??????????????? ?????? ???????????? ???????????? ???????????? ???????????? ????????? ??????????????? ???????????? ????????????. ?????? ?????? ABC??? ???????????? ????????? ???????????? ???????????? C??? ?????????????????? ????????? ABC??? ???????????? ?????????.
                        Intent.FLAG_ACTIVITY_CLEAR_TOP); // ????????? ????????????????????? ??????????????? ??????????????? ??????????????? ?????? ???????????? ?????? ????????? ????????? ??????????????? ???????????? ??? ????????? ???????????? ?????? ??????????????? ?????????????????? ???????????????. ????????? ????????????????????? ????????? ?????????????????? ?????????????????? ????????? ????????????????????? ?????? ??????????????? ???????????????.
                startActivity(showIntent);

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

    public void entCancelClicked(View view){
        finish();
    }

}
