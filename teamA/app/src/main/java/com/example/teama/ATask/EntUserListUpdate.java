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


public class EntUserListUpdate extends AsyncTask<Void, Void, Void> {

    String entId, entPw, entName, entNick, entPhone, imageDbPathA, imageRealPathA;

    public EntUserListUpdate(String entId, String entPw, String entName, String entNick, String entPhone, String imageDbPathA, String imageRealPathA) {
        this.entId = entId;
        this.entPw = entPw;
        this.entName = entName;
        this.entNick = entNick;
        this.entPhone = entPhone;
        this.imageDbPathA = imageDbPathA;
        this.imageRealPathA = imageRealPathA;
    }

    public EntUserListUpdate(String entName, String entNick, String entPhone, String imageDbPathA, String imageRealPathA) {
        this.entName = entName;
        this.entNick = entNick;
        this.entPhone = entPhone;
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

            builder.addTextBody("entId", entId, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("entPw", entPw, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("entName", entName, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("entNick", entNick, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("entPhone", entPhone, ContentType.create("Multipart/related", "UTF-8"));

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
