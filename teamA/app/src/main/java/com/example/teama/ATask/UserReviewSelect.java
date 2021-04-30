package com.example.teama.ATask;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.teama.Adapter.ReviewListAdapter;
import com.example.teama.DTO.UserReviewDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.teama.Common.CommonMethod.ipConfig;

public class UserReviewSelect extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main:UserReviewSelect";

    ArrayList<UserReviewDTO> userReviewList;
    ReviewListAdapter adapter;
    ProgressDialog progressDialog;
    String ent_nick;

    public UserReviewSelect(ArrayList<UserReviewDTO> userReviewList, ReviewListAdapter adapter, ProgressDialog progressDialog, String ent_nick) {
        this.userReviewList = userReviewList;
        this.adapter = adapter;
        this.progressDialog = progressDialog;
        this.ent_nick = ent_nick;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        userReviewList.clear();
        String result = "";
        String postURL = ipConfig + "/ent/userReviewSelect";

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));
            String user_nick = "홍홍홍";//현재로그인중인 회원 닉네임정보
            builder.addTextBody("user_nick", user_nick, ContentType.create("Multipart/related", "UTF-8"));//현재로그인중인 회원 닉네임정보
            if (!ent_nick.isEmpty())
                builder.addTextBody("ent_nick", ent_nick, ContentType.create("Multipart/related", "UTF-8"));//현재 보는중인 가게 아이디정보

            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            String jsonStr = stringBuilder.toString();

            inputStream.close();

        } catch (Exception e) {
            Log.d("", e.getMessage());
            e.printStackTrace();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }

        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(progressDialog != null){
            progressDialog.dismiss();
        }

        Log.d("entListDetail", "List Select Complete!!!");

        adapter.notifyDataSetChanged();
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                userReviewList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public UserReviewDTO readMessage(JsonReader reader) throws IOException {
        String user_review = "", rvpicture_path = "", user_nick = "", ent_nick = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("users_nick")) {
                user_nick = reader.nextString();
            } else if (readStr.equals("ent_nick")) {
                ent_nick = reader.nextString();
            } else if (readStr.equals("user_review")) {
                user_review = reader.nextString();
            } else if (readStr.equals("rvpicture_path")) {
                rvpicture_path = reader.nextString();
            }   else {
                reader.skipValue();
            }
        }
        reader.endObject();
        UserReviewDTO dto = new UserReviewDTO();
        dto.setUsers_nick(user_nick);
        dto.setEnt_nick(ent_nick);
        dto.setReview(user_review);
        dto.setRvpicture_path(rvpicture_path);
        return dto;

    }

    /*public List<Double> readDoublesArray(JsonReader reader) throws IOException {
        List<Double> doubles = new ArrayList<Double>();

        reader.beginArray();
        while (reader.hasNext()) {
            doubles.add(reader.nextDouble());
        }
        reader.endArray();
        return doubles;
    }

    public User readUser(JsonReader reader) throws IOException {
        String username = null;
        int followersCount = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                username = reader.nextString();
            } else if (name.equals("followers_count")) {
                followersCount = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new User(username, followersCount);
    }*/

}


