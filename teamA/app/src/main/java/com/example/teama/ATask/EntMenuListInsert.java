package com.example.teama.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.nio.charset.Charset;

import static com.example.teama.Common.CommonMethod.ipConfig;

public class EntMenuListInsert extends AsyncTask<Void, Void, Void> {

        String menu_name, menu_detail, imageDbPathA, imageRealPathA;
        int  menu_price;

        // id, name, date, 업로드타입(이미지,비디오), 디비에 입력할 이미지경로, 실제업로드할 이미지경로,디비에 입력할 비디오경로, 실제업로드할 비디오경로
        public EntMenuListInsert(String menu_name, String menu_detail, String menu_price, String imageDbPathA, String imageRealPathA){
            this.menu_name = menu_name;
            this.menu_detail = menu_detail;
            this.menu_price = Integer.parseInt(menu_price);
            this.imageDbPathA = imageDbPathA;
            this.imageRealPathA = imageRealPathA;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                // MultipartEntityBuild 생성
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                builder.setCharset(Charset.forName("UTF-8"));

                // 문자열 및 데이터 추가

                builder.addTextBody("menu_name", menu_name, ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("menu_detail", menu_detail, ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("menu_price", String.valueOf(menu_price), ContentType.create("Multipart/related", "UTF-8"));

                builder.addTextBody("dbImgPath", imageDbPathA, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("image", new FileBody(new File(imageRealPathA)));

                String postURL = ipConfig + "/app/anInsertMulti";

                HttpClient httpClient = AndroidHttpClient.newInstance("Android");
                HttpPost httpPost = new HttpPost(postURL);
                httpPost.setEntity(builder.build());
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

    }
