//점포리스트 select 하는 부분
package com.example.teama.ATask;


import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;


import com.example.teama.Adapter.EntListAdapter;
import com.example.teama.DTO.EntListDTO;

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
public class entSelect extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main:entSelect";
    // 생성자
    ArrayList<EntListDTO> myItemArrayList;
    EntListAdapter adapter;
    ProgressDialog progressDialog;

    public entSelect(ArrayList<EntListDTO> myItemArrayList, EntListAdapter adapter, ProgressDialog progressDialog) {
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
        String postURL = ipConfig + "/ent/entList";

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

    public EntListDTO readMessage(JsonReader reader) throws IOException {
        String ent_id="", ent_name = "", ent_location = "", ent_open = "", ent_close = "", ent_proof = "", ent_nick="";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if(readStr.equals("ent_id")){
                ent_id = reader.nextString();
            }else if (readStr.equals("ent_name")) {
                ent_name = reader.nextString();
            } else if (readStr.equals("ent_location")) {
                ent_location = reader.nextString();
            } else if (readStr.equals("ent_open")) {
                ent_open = reader.nextString();
            } else if (readStr.equals("ent_close")) {
                ent_close = reader.nextString();
            }else if (readStr.equals("ent_proof")) {
                ent_proof = reader.nextString();
            }else if(readStr.equals("ent_nick")){
                ent_nick = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("listselect:myitem", ent_name + "," + ent_location + "," + ent_open + "," + ent_close);
        //return new entListDTO(ent_name, ent_open, ent_close, ent_location);
        EntListDTO dto = new EntListDTO(ent_name, ent_open, ent_close, ent_location, ent_proof);
        dto.setEnt_id(ent_id);
        dto.setEnt_nick(ent_nick);
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
