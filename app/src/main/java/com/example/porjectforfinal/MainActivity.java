package com.example.porjectforfinal;

import android.app.FragmentManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    /*private FragmentManager fragmentManager;
    private MapFragment mapFragment;*/

    private BottomNavigationView bottomNavigationView;
    private androidx.fragment.app.FragmentManager fm;
    private FragmentTransaction ft;
    private frag1 frag1;
    private frag2 frag2;
    private frag3 frag3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*   fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
*/

        bottomNavigationView =findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_run:
                        setFrag(1);
                        break;
                    case R.id.action_map:
                        setFrag(0);
                        break;
                    case R.id.action_info:
                        setFrag(2);
                        break;
                }
                return false;
            }
        });
        frag1 = new frag1();
        frag2 = new frag2();
        frag3 = new frag3();
        setFrag(0);
    }

//
//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        LatLng location = new LatLng(37.62750839348369, 126.93566219279107); //우리집
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.title("우리집");
//        markerOptions.snippet("세부사항입력가능이군");
//        markerOptions.position(location);
//        googleMap.addMarker(markerOptions);
//
//
//        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,16)); //크게 보려면 큰숫자를
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16)); //카메라가 애니처럼움직이며 줌
//
//        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(@NonNull Marker marker) {
//                Intent intent = new Intent(Intent.ACTION_VIEW); // 암시적 인텐트
//                intent.setData(Uri.parse("https://www.diningcode.com/list.php?query=%EC%9D%80%ED%8F%89%EB%89%B4%ED%83%80%EC%9A%B4%20%EB%8D%B0%EC%9D%B4%ED%8A%B8%EC%BD%94%EC%8A%A4"));
//                startActivity(intent);
//            }
//        }); //이걸로 근처? 갈곳으로 이어주는 그런거 구현 하면 좋을듯 위도 경도 중심으로 맛집 찾는거도 있나
//        //idea url 분석해서 위경도 주소로 바꿔주는 geocoder 이라는 클래스를 이용해보자
//    }


    //프레그먼트 교체가 일어나는 실행문
    private  void setFrag(int n){
        fm = getSupportFragmentManager(); //fragment는 바로 접근이 불가하여 이함수가 필요하다.
        ft = fm.beginTransaction();
        switch (n){
            case  0 :
                ft.replace(R.id.main_frame, frag1);
                ft.commit(); //저장을 의미
                break;
            case  1 :
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;
            case  2 :
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;
        }
    }
}