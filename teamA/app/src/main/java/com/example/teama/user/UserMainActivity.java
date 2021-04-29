package com.example.teama.user;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.teama.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class UserMainActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = UserMainActivity.class.getSimpleName();
    SupportMapFragment mapFragment;
    GoogleMap map;
    Toolbar toolbar;
    ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5;

    Double latitude = Double.valueOf(0);
    Double longitude = Double.valueOf(0);

    LatLng myLoc, shopLoc1, shopLoc2, shopLoc3, shopLoc4, shopLoc5, shopLoc6, shopLoc7;

    private final LatLng Ent1 = new LatLng(35.1551, 126.8880);
    private final LatLng Ent2 = new LatLng(35.1543, 126.8862);
    private final LatLng Ent3 = new LatLng(35.1519, 126.8865);
    private final LatLng Ent4 = new LatLng(35.1516, 126.8881);
    private final LatLng Ent5 = new LatLng(35.1526, 126.8897);
    private final LatLng Ent6 = new LatLng(35.1545, 126.8898);
    private final LatLng Ent7 = new LatLng(35.1531, 126.8871);

    private Marker markerEnt1;
    private Marker markerEnt2;
    private Marker markerEnt3;
    private Marker markerEnt4;
    private Marker markerEnt5;
    private Marker markerEnt6;
    private Marker markerEnt7;

    LatLng testLatLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton5 = findViewById(R.id.imageButton5);


        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "점포리스트로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ShopListActivity.class);
                startActivity(intent);
            }
        });
        
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "점포리스트로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ShopListActivity.class);
                startActivity(intent);
            }
        });
        
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "점포리스트로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ShopListActivity.class);
                startActivity(intent);
            }
        });
        
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "점포리스트로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ShopListActivity.class);
                startActivity(intent);
            }
        });
        
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "점포리스트로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ShopListActivity.class);
                startActivity(intent);
            }
        });
        
        // 반드시 androidx... 로 선택
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 네비게이션 드로어 생성해서 리스너 붙이기 : res-string 에 정의할것
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        // onNavigationItemSelected 를 사용하기 위해서는 아래와 같이 정의해야함
        navigationView.setNavigationItemSelectedListener(this);

        // 헤드 드로어에 로그인정보 표시하기
        int userLevel = 1;  // 0:일반유저  1:관리자
        String loginID = "admin";   // BTS:일반유저   admin:관리자
        View headerView = navigationView.getHeaderView(0);

        TextView navLoginID = headerView.findViewById(R.id.loginID);
        navLoginID.setText(loginID + "님! 안녕하세요");
        ImageView loginImage = headerView.findViewById(R.id.loginImage);
        Glide.with(this).load("https://i.pinimg.com/originals/e3/da/d3/e3dad378a5adfbf3ee69fb1636ed6898.gif")
                .circleCrop().into(loginImage);

        checkDangerousPermissions();



        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "onMapReady: Google Map is Ready");



                Double distance1 = getDistance1(testLatLng, shopLoc1);
                Log.d(TAG, "onMarkerClick: distance => " + distance1);
                shopLoc1 = Ent1;
                Double distance2 = getDistance2(testLatLng, shopLoc2);
                Log.d(TAG, "onMarkerClick: distance => " + distance2);
                shopLoc2 = Ent2;
                Double distance3 = getDistance3(testLatLng, shopLoc3);
                Log.d(TAG, "onMarkerClick: distance => " + distance3);
                shopLoc3 = Ent3;
                Double distance4 = getDistance4(testLatLng, shopLoc4);
                Log.d(TAG, "onMarkerClick: distance => " + distance4);
                shopLoc4 = Ent4;
                Double distance5 = getDistance5(testLatLng, shopLoc5);
                Log.d(TAG, "onMarkerClick: distance => " + distance5);
                shopLoc5 = Ent5;
                Double distance6 = getDistance6(testLatLng, shopLoc6);
                Log.d(TAG, "onMarkerClick: distance => " + distance6);
                shopLoc6 = Ent6;
                Double distance7 = getDistance7(testLatLng, shopLoc7);
                Log.d(TAG, "onMarkerClick: distance => " + distance7);
                shopLoc7 = Ent7;


                map = googleMap;

                try {
                    map.setMyLocationEnabled(true);
                }catch (SecurityException e){
                    Log.d(TAG, "onMapReady: " + e.getMessage());
                }

                markerEnt1 = map.addMarker(new MarkerOptions()
                        .position(Ent1)
                        .title("가게이름")
                        .snippet("010-0000-0000\n" + "나와의 거리: " + String.valueOf(getDistance1(testLatLng, shopLoc1))
                                .substring(0,String.valueOf(getDistance1(testLatLng, shopLoc1)).indexOf("."))+"m")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ent_location)));
                markerEnt1.setTag(Ent1);

                markerEnt2 = map.addMarker(new MarkerOptions()
                        .position(Ent2)
                        .title("가게이름")
                        .snippet("010-0000-0000\n" + "나와의 거리: " + String.valueOf(getDistance2(testLatLng, shopLoc2))
                                .substring(0,String.valueOf(getDistance2(testLatLng, shopLoc2)).indexOf("."))+"m")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ent_location)));
                markerEnt2.setTag(Ent2);

                markerEnt3 = map.addMarker(new MarkerOptions()
                        .position(Ent3)
                        .title("가게이름")
                        .snippet("010-0000-0000\n" + "나와의 거리: " + String.valueOf(getDistance3(testLatLng, shopLoc3))
                                .substring(0,String.valueOf(getDistance3(testLatLng, shopLoc3)).indexOf("."))+"m")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ent_location)));
                markerEnt3.setTag(Ent3);

                markerEnt4 = map.addMarker(new MarkerOptions()
                        .position(Ent4)
                        .title("가게이름")
                        .snippet("010-0000-0000\n" + "나와의 거리: " + String.valueOf(getDistance4(testLatLng, shopLoc4))
                                .substring(0,String.valueOf(getDistance4(testLatLng, shopLoc4)).indexOf("."))+"m")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ent_location)));
                markerEnt4.setTag(Ent4);

                markerEnt5 = map.addMarker(new MarkerOptions()
                        .position(Ent5)
                        .title("가게이름")
                        .snippet("010-0000-0000\n" + "나와의 거리: " + String.valueOf(getDistance5(testLatLng, shopLoc5))
                                .substring(0,String.valueOf(getDistance5(testLatLng, shopLoc5)).indexOf("."))+"m")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ent_location)));
                markerEnt5.setTag(Ent5);

                markerEnt6 = map.addMarker(new MarkerOptions()
                        .position(Ent6)
                        .title("가게이름")
                        .snippet("010-0000-0000\n" + "나와의 거리: " + String.valueOf(getDistance6(testLatLng, shopLoc6))
                                .substring(0,String.valueOf(getDistance6(testLatLng, shopLoc6)).indexOf("."))+"m")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ent_location)));
                markerEnt6.setTag(Ent6);

                markerEnt7 = map.addMarker(new MarkerOptions()
                        .position(Ent7)
                        .title("가게이름")
                        .snippet("010-0000-0000\n" + "나와의 거리: " + String.valueOf(getDistance7(testLatLng, shopLoc7))
                                .substring(0,String.valueOf(getDistance7(testLatLng, shopLoc7)).indexOf("."))+"m")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ent_location)));
                markerEnt7.setTag(Ent7);



                map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LinearLayout info = new LinearLayout(getApplicationContext());
                        info.setOrientation(LinearLayout.VERTICAL);

                        TextView title = new TextView(getApplicationContext());
                        title.setTextColor(Color.BLACK);
                        title.setGravity(Gravity.CENTER);
                        title.setTypeface(null, Typeface.BOLD);
                        title.setText(marker.getTitle());

                        TextView snippet = new TextView(getApplicationContext());
                        snippet.setTextColor(Color.GRAY);
                        snippet.setGravity(Gravity.LEFT);
                        snippet.setText(marker.getSnippet());

                        info.addView(title);
                        info.addView(snippet);
                        return info;
                    }
                });


                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Toast.makeText(getApplicationContext(), "해당 가게로 이동합니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ShopDetailActivity.class);
                        startActivity(intent);
                    }
                });




                //반경 거리 표시
                /*CircleOptions circle100m = new CircleOptions().center(new LatLng(latitude, longitude))
                        .radius(100).strokeWidth(0f)
                        .fillColor(Color.parseColor("#000000"));

                this.map.addCircle(circle100m);*/

            }
        });
        MapsInitializer.initialize(this);

        requestMyLocation();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.my_page){
            /*if () {

            }else{

            }*/
            Toast.makeText(this, "정보를 수정합니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),UserMyPageActivity.class);
            startActivity(intent);
        }else if(id == R.id.my_list){
            Toast.makeText(this, "찜 목록으로 이동합니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),UserMyListActivity.class);
            startActivity(intent);
        }else if(id == R.id.User_review){
            Toast.makeText(this, "리뷰페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),UserReviewActivity.class);
            startActivity(intent);
        }
        //메뉴 선택후 화면 바꾼 후 드로우 사라지게 하기
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void requestMyLocation() {
        LocationManager manager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try{
            long minTime = 40000;
            float minDistance = 0;

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    location -> showCurrentLocation(location)

            );

            Location lastLocation =
                    manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation != null){
                latitude = lastLocation.getLatitude();
                longitude = lastLocation.getLongitude();
                testLatLng =
                        new LatLng(latitude, longitude);
                String msg = "\nLatitude : " + latitude
                        + "\n Longitude : " + longitude;
                Log.d(TAG, "requestMyLocation: " + msg);

            }

        }catch (SecurityException e){
            Log.d(TAG, "requestMyLocation: " + e.getMessage());
        }
    }

    //내 위치 구하기
    private void showCurrentLocation(Location location) {
        LatLng curLatLng =
                new LatLng(location.getLatitude(), location.getLongitude());

        myLoc = curLatLng;

        String msg = "\n Latitude : " + curLatLng.latitude
                + "\n Longitude : " + curLatLng.longitude;
        Log.d(TAG, "showCurrentLocation: " + msg);

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curLatLng, 17));
    }

    // 두지점간 거리 구하기
    public double getDistance1(LatLng testLatLng, LatLng LatLng2) { // 내위치, 가게위치
        double distance = 0;
        try {
            Location locationA = new Location("A");
            locationA.setLatitude(testLatLng.latitude);
            locationA.setLongitude(testLatLng.longitude);
            Location locationB = new Location("B");
            locationB.setLatitude(LatLng2.latitude);
            locationB.setLongitude(LatLng2.longitude);
            distance = locationA.distanceTo(locationB);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return distance;
    }
    public double getDistance2(LatLng testLatLng, LatLng LatLng2) { // 내위치, 가게위치
        double distance = 0;
        try {
            Location locationA = new Location("A");
            locationA.setLatitude(testLatLng.latitude);
            locationA.setLongitude(testLatLng.longitude);
            Location locationB = new Location("B");
            locationB.setLatitude(LatLng2.latitude);
            locationB.setLongitude(LatLng2.longitude);
            distance = locationA.distanceTo(locationB);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return distance;
    }
    public double getDistance3(LatLng testLatLng, LatLng LatLng2) { // 내위치, 가게위치
        double distance = 0;
        try {
            Location locationA = new Location("A");
            locationA.setLatitude(testLatLng.latitude);
            locationA.setLongitude(testLatLng.longitude);
            Location locationB = new Location("B");
            locationB.setLatitude(LatLng2.latitude);
            locationB.setLongitude(LatLng2.longitude);
            distance = locationA.distanceTo(locationB);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return distance;
    }

    public double getDistance4(LatLng testLatLng, LatLng LatLng2) { // 내위치, 가게위치
        double distance = 0;
        try {
            Location locationA = new Location("A");
            locationA.setLatitude(testLatLng.latitude);
            locationA.setLongitude(testLatLng.longitude);
            Location locationB = new Location("B");
            locationB.setLatitude(LatLng2.latitude);
            locationB.setLongitude(LatLng2.longitude);
            distance = locationA.distanceTo(locationB);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return distance;
    }

    public double getDistance5(LatLng testLatLng, LatLng LatLng2) { // 내위치, 가게위치
        double distance = 0;
        try {
            Location locationA = new Location("A");
            locationA.setLatitude(testLatLng.latitude);
            locationA.setLongitude(testLatLng.longitude);
            Location locationB = new Location("B");
            locationB.setLatitude(LatLng2.latitude);
            locationB.setLongitude(LatLng2.longitude);
            distance = locationA.distanceTo(locationB);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return distance;
    }

    public double getDistance6(LatLng testLatLng, LatLng LatLng2) { // 내위치, 가게위치
        double distance = 0;
        try {
            Location locationA = new Location("A");
            locationA.setLatitude(testLatLng.latitude);
            locationA.setLongitude(testLatLng.longitude);
            Location locationB = new Location("B");
            locationB.setLatitude(LatLng2.latitude);
            locationB.setLongitude(LatLng2.longitude);
            distance = locationA.distanceTo(locationB);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return distance;
    }

    public double getDistance7(LatLng testLatLng, LatLng LatLng2) { // 내위치, 가게위치
        double distance = 0;
        try {
            Location locationA = new Location("A");
            locationA.setLatitude(testLatLng.latitude);
            locationA.setLongitude(testLatLng.longitude);
            Location locationB = new Location("B");
            locationB.setLatitude(LatLng2.latitude);
            locationB.setLongitude(LatLng2.longitude);
            distance = locationA.distanceTo(locationB);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return distance;
    }

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "님 어서오세요", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}