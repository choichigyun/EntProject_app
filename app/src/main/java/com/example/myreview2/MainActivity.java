package com.example.myreview2;

import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myreview2.ATask.ListSelect;
import com.example.myreview2.Adapter.ReviewAdapter;
import com.example.myreview2.Dto.ReviewDTO;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //객체 선언

    ListView listview;
    ArrayList<ReviewDTO> list;
    ReviewAdapter adapter;

    ProgressDialog progressDialog;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //디바이스 사이즈 구하기
        Point size = getDeviceSize();

        //객체 초기화
        listview = findViewById(R.id.listview1);

        list = new ArrayList<>();

        adapter = new ReviewAdapter(MainActivity.this, list, size);


        /*adapter.addDTO(new ReviewDTO(ContextCompat.getDrawable(this,R.drawable.user2),"춘식이네 푸드트럭","맛있어요."));
        adapter.addDTO(new ReviewDTO(ContextCompat.getDrawable(this,R.drawable.user2),"춘식이네 푸드트럭","진짜 맛있어요."));
        adapter.addDTO(new ReviewDTO(ContextCompat.getDrawable(this,R.drawable.user2),"춘삼이네 푸드트럭","맛있어요."));
        adapter.addDTO(new ReviewDTO(ContextCompat.getDrawable(this,R.drawable.user2),"춘삼이네 푸드트럭","맛있어요."));*/


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


        ListSelect reviewSelect = new ListSelect(list, adapter, progressDialog);
        try {
            reviewSelect.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    // 디바이스 가로 세로 사이즈 구하기
    // getRealSize()는 status bar 등 system insets을
    // 포함한 스크린 사이즈를 가져오는 방법이고,
    // getSize()는 status bar 등 insets를
    // 제외한 부분에 대한 사이즈만 가져오는 함수이다.
    // 단위는 픽셀
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

    // 데이터베이스에서 데이터 받아 리스트 갱신


}