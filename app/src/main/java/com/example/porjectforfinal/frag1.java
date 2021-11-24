package com.example.porjectforfinal;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class frag1 extends Fragment implements OnMapReadyCallback {

    private ViewGroup view;
    private MapView mapView;

    private int mType = 1;

    public float frag1_lat;
    public float frag1_lon;
    public String frag1_address;

    //private FragmentManager fragmentManager;
    //private MapFragment mapFragment;
    // private  GoogleMap mMap;

    private LatLng startLatLng = new LatLng(0, 0);
    private LatLng endLatLng = new LatLng(0, 0);


    //String[] polylines;  //경로 표시위해
    //float[] polylines_f;
    //List<Polyline>polylines_draw =new ArrayList<>();  //경로 표시위해
    ///
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

        Bundle extra = getArguments();
        //Bundle extra = getIntent().getExtras();
        Log.v("태그", "위"+String.valueOf(extra));
        if(extra != null) {
            frag1_lat = Float.parseFloat(extra.getString("coordinates_lat"));
            frag1_lon = Float.parseFloat(extra.getString("coordinates_lon"));
            frag1_address = extra.getString("address");
            // polylines = extra.getStringArray("path");
           /* polylines_f = new float[polylines.length];
            for(int i=0; i<polylines.length; i++) {
                polylines_f[i] = Float.parseFloat(polylines[i]);
                if(i % 2 == 0){

                }
            }보류*/
        }


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
        //LatLng location = new LatLng(37.62750839348369, 126.93566219279107); //우리집
        LatLng location = new LatLng(frag1_lat, frag1_lon);
        Log.v("태그",frag1_lat+","+frag1_lon);

        String address_Spilt[] = frag1_address.split(" ");

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("주변 명소 검색!!");
        markerOptions.snippet("현위치 : " +address_Spilt[1]+ " "+address_Spilt[2]+" "+address_Spilt[3]);
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,16)); //크게 보려면 큰숫자를
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16)); //카메라가 애니처럼움직이며 줌

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW); // 암시적 인텐트
                intent.setData(Uri.parse("https://www.diningcode.com/list.php?query="+address_Spilt[1]+ " "+address_Spilt[2]+" "+address_Spilt[3]));
                startActivity(intent);
            }
        }); //이걸로 근처? 갈곳으로 이어주는 그런거 구현 하면 좋을듯 위도 경도 중심으로 맛집 찾는거도 있나
        //idea url 분석해서 위경도 주소로 바꿔주는 geocoder 이라는 클래스를 이용해보자

        //mMap = googleMap;
        // drawPath();
    }

//    private void drawPath(){
//        //polyline을 그려주는 메소드 경로를 그려 준다.
//        PolylineOptions options = new PolylineOptions().add(startLatLng).add(endLatLng).width(15).color(Color.BLACK).geodesic(true);
//        polylines_draw.add(mMap.addPolyline(options));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng, 18));
//    }



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
