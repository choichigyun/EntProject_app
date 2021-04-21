package com.example.teama.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.project.entListDTO;
import com.example.project.entListDetail;

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
import java.util.ArrayList;

import static com.example.project.Common.CommonMethod.ipConfig;

public class entMenuSelect extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main:entMenuSelect";
    String ent_id;
    ArrayList<entListDTO> menuList;
    entListDetail entListDetail;



    public entMenuSelect(String ent_id, ArrayList<entListDTO> menuList) {
        this.ent_id = ent_id;
        this.menuList = menuList;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;


    @Override
    protected Void doInBackground(Void... voids) {

        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addTextBody("ent_id", ent_id, ContentType.create("Multipart/related", "UTF-8"));

            String postURL = ipConfig + "/ent/entMenu";

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
        }catch (Exception e){
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
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                menuList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            Log.d(TAG, "readJsonStream: menuList : " + menuList.get(0).getEnt_menu_picture());

            reader.close();
        }
    }

    public entListDTO readMessage(JsonReader reader) throws IOException {

        String ent_id = "", ent_menu = "", ent_menu_picture = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("ent_id")) {
                ent_id = reader.nextString();
            } else if (readStr.equals("ent_menu")) {
                ent_menu = reader.nextString();
            } else if (readStr.equals("ent_menu_picture")){
                ent_menu_picture = reader.nextString();
            }else{
                reader.skipValue();
            }
        }
        reader.endObject();
        entListDTO dto = new entListDTO();
        dto.setEnt_id(ent_id);
        dto.setEnt_menu(ent_menu);
        dto.setEnt_menu_picture((ent_menu_picture));
        return dto;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
