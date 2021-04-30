package com.example.teama.ATask;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.teama.Adapter.Manager_memberListAdapter;
import com.example.teama.DTO.UserMemberDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.teama.Common.CommonMethod.ipConfig;

public class Manager_memberSelect extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main:entSelect";
    // 생성자
    ArrayList<UserMemberDTO> myItemArrayList;
    Manager_memberListAdapter adapter;
    ProgressDialog progressDialog;

    public Manager_memberSelect(ArrayList<UserMemberDTO> myItemArrayList, Manager_memberListAdapter adapter, ProgressDialog progressDialog) {
        this.myItemArrayList = myItemArrayList;
        this.adapter = adapter;
        this.progressDialog = progressDialog;
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
        Log.d(TAG, "doInBackground: 1");

        myItemArrayList.clear();
        Log.d(TAG, "doInBackground: 2");
        String result = "";
        Log.d(TAG, "doInBackground: 3");
        String postURL = ipConfig + "/ent/userList";

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);

//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//            StringBuilder stringBuilder = new StringBuilder();
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null){
//                stringBuilder.append(line + "\n");
//            }
//            String jsonStr = stringBuilder.toString();

            inputStream.close();

        } catch (Exception e) {
            Log.d("Sub1", e.getMessage());
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

        Log.d("Sub1", "List Select Complete!!!");

        adapter.notifyDataSetChanged();
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                myItemArrayList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
            myItemArrayList.add(readMessage(reader));
        }
    }

    public UserMemberDTO readMessage(JsonReader reader) throws IOException {
        String users_name="", users_nick = "", users_id = "", users_pw = "", users_tel = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if(readStr.equals("users_name")){
                users_name = reader.nextString();
            }else if (readStr.equals("users_nick")) {
                users_nick = reader.nextString();
            } else if (readStr.equals("users_id")) {
                users_id = reader.nextString();
            } else if (readStr.equals("users_pw")) {
                users_pw = reader.nextString();
            } else if (readStr.equals("users_tel")) {
                users_tel = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        //return new entListDTO(ent_name, users_pw, users_tel, users_id);
        UserMemberDTO dto = new UserMemberDTO();
        dto.setUsers_name(users_name);
        dto.setUsers_nick(users_nick);
        dto.setUsers_id(users_id);
        dto.setUsers_pw(users_pw);
        dto.setUsers_tel(users_tel);
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

