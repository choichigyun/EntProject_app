package com.example.teama.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

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

public class UserReviewInsert extends AsyncTask<Void, Void, String> {
    private static final String TAG = "main:UserReviewInsert";
    String user_reviewTitle, user_review, imageDbPathA,imageRealPathA;

    public UserReviewInsert(String user_reviewTitle, String user_review, String imageDbPathA, String imageRealPathAw) {
        this.user_reviewTitle = user_reviewTitle;
        this.user_review = user_review;
        this.imageDbPathA = imageDbPathA;
        this.imageRealPathA = imageRealPathAw;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    //성공여부 확인 하는 값
    String state ="";

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));
            String users_nick = "aaa";
            String ent_id = "bbb";
            // 문자열 및 데이터 추가
            builder.addTextBody("user_review", user_review, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("users_nick", users_nick, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("ent_id", ent_id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("user_reviewTitle", user_reviewTitle, ContentType.create("Multipart/related", "UTF-8"));
            if(imageDbPathA != null)
                builder.addTextBody("dbImgPath", imageDbPathA, ContentType.create("Multipart/related", "UTF-8"));
            if(imageRealPathA != null)
                builder.addPart("image", new FileBody(new File(imageRealPathA)));

            String postURL = ipConfig + "/ent/userReviewInsert";
            // 전송
            //InputStream inputStream = null;
            HttpClient httpClient = AndroidHttpClient.newInstance("Android");
            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            //inputStream = httpEntity.getContent();

            // 응답
            /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            inputStream.close();*/

            // 응답결과
            /*String result = stringBuilder.toString();
            Log.d("response", result);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
    }
}
