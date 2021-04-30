package com.example.teama.ATask;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;


import com.example.teama.Adapter.EntMenuAdapter;
import com.example.teama.DTO.EntMenuDTO;

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

// doInBackground 파라미터 타입, onProgressUpdate파라미터 타입, onPostExecute 파라미터 타입 순서
// AsyncTask <Params, Progress, Result> 순서임
public class EntMenuListSelect extends AsyncTask<Void, Void, Void> {
    // 생성자
    ArrayList<EntMenuDTO> myItemArrayList;
    EntMenuAdapter adapter;
    ProgressDialog progressDialog;

    public EntMenuListSelect(ArrayList<EntMenuDTO> myItemArrayList, EntMenuAdapter adapter, ProgressDialog progressDialog) {
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
        myItemArrayList.clear();
        String result = "";
        String postURL = ipConfig + "/app/anSelectMulti";

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

            // 3. 배열로 오브젝트 받을 때
            readJsonStream(inputStream);

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
        }
    }

    public EntMenuDTO readMessage(JsonReader reader) throws IOException {
        String menu_name = "";
        String menu_detail = "";
        int menu_Price = 0;
        String fdpicture_path = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("menu_name")) {
                menu_name = reader.nextString();
            } else if (readStr.equals("menu_Price")) {
                menu_Price = reader.nextInt();
            } else if (readStr.equals("fdpicture_path")) {
                fdpicture_path = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("listselect:EntMenuDTO", menu_name + "," + menu_Price + "," + fdpicture_path);
        return new EntMenuDTO(menu_name, menu_detail, menu_Price, fdpicture_path);

    }

}
