package com.example.teama.user;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teama.R;
import com.example.teama.UserMyListDto;

import java.util.ArrayList;

public class UserMyListActivity extends AppCompatActivity {
    ListView listView;

    UserMyListAdapter adapter;
    ArrayList<UserMyListDto> dtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_my_list);

        // 디바이스 사이즈 구하기
        Point size = getDeviceSize();

        listView = findViewById(R.id.userListView);

        // dtos 객체생성
        dtos = new ArrayList<>();
        // 어댑터 객체 생성
        adapter = new UserMyListAdapter(UserMyListActivity.this, dtos, size);
        adapter.addDto(new UserMyListDto("공화춘", "서구 치평동", "09:00~18:00", R.drawable.jjajang, R.drawable.trash));
        adapter.addDto(new UserMyListDto("맛집포차", "서구 치평동", "09:00~18:00", R.drawable.jjajang, R.drawable.trash));
        adapter.addDto(new UserMyListDto("봉구스밥버거", "서구 치평동", "09:00~18:00", R.drawable.jjajang, R.drawable.trash));

        listView.setAdapter(adapter);

    }

    // 디바이스 가로 세로 사이즈 구하기
    // getRealSize()는 status bar 등 system insets을
    // 포함한 스크린사이즈를 가져오는 방법이고,
    // getSize()는 status bar 등 system insets을
    // 제외한 부분에 대한 사이즈를 가져오는 함수입니다.
    // 단위는 픽셀
    private Point getDeviceSize() {
        // 액티비티에서 가져올때
        Display display = getWindowManager().getDefaultDisplay();
        // 프래그먼트에서 가져올때
        //Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size); // or getSize(size) : API 17 이상부터
        int width = size.x; // 디바이스 넓이
        int height = size.y; // 디바이스 높이

        return size;
    }
}