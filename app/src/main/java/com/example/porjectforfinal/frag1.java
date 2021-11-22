package com.example.porjectforfinal;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class frag1 extends Fragment implements OnMapReadyCallback {

    private ViewGroup view;
    private MapView mapView;

    private int mType = 1;

    //private FragmentManager fragmentManager;
    //private MapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (ViewGroup)inflater.inflate(R.layout.frag1,container,false);

       // fragmentManager = getSupportFragmentManager();
        mapView = (MapView) view.findViewById(R.id.googleMap);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        return view;
    }

    //옵션메뉴
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option_menu,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.map1:
                Toast.makeText(getActivity(), "페이지를 다시 방문시 "+item.getTitle()+"지도가 적용됩니다!!", Toast.LENGTH_SHORT).show();
                mType = 1;
                return true;
            case R.id.map2:
                Toast.makeText(getActivity(), "페이지를 다시 방문시 "+item.getTitle()+"지도가 적용됩니다!!", Toast.LENGTH_SHORT).show();
                mType = 2;
                return true;
            case R.id.map3:
                Toast.makeText(getActivity(), "페이지를 다시 방문시 "+item.getTitle()+"지도가 적용됩니다!!", Toast.LENGTH_SHORT).show();
                mType = 3;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //여기까지

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        googleMap.setMapType(mType);
        LatLng location = new LatLng(37.62750839348369, 126.93566219279107); //우리집
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("우리집");
        markerOptions.snippet("세부사항입력가능이군");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,16)); //크게 보려면 큰숫자를
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16)); //카메라가 애니처럼움직이며 줌

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW); // 암시적 인텐트
                intent.setData(Uri.parse("https://www.diningcode.com/list.php?query=%EC%9D%80%ED%8F%89%EB%89%B4%ED%83%80%EC%9A%B4%20%EB%8D%B0%EC%9D%B4%ED%8A%B8%EC%BD%94%EC%8A%A4"));
                startActivity(intent);
            }
        }); //이걸로 근처? 갈곳으로 이어주는 그런거 구현 하면 좋을듯 위도 경도 중심으로 맛집 찾는거도 있나
        //idea url 분석해서 위경도 주소로 바꿔주는 geocoder 이라는 클래스를 이용해보자
    }

    public void onStart()
    {
        String addr;

        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}
