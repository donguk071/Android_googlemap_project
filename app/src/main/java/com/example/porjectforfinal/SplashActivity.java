

//package com.example.porjectforfinal;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.IntentSender;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.util.Log;
//
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.common.api.ResolvableApiException;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationAvailability;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResponse;
//import com.google.android.gms.location.LocationSettingsStatusCodes;
//import com.google.android.gms.location.SettingsClient;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//
//public class SplashActivity extends AppCompatActivity {
//    private static final String TAG = SplashActivity.class.getSimpleName();
//    private static final int GPS_UTIL_LOCATION_PERMISSION_REQUEST_CODE = 100;
//    private static final int GPS_UTIL_LOCATION_RESOLUTION_REQUEST_CODE = 101;
//
//    public static final int DEFAULT_LOCATION_REQUEST_PRIORITY = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY; //구글에서 추천하는 효율좋은 우선순위
//    public static final long DEFAULT_LOCATION_REQUEST_INTERVAL = 20000L;
//    public static final long DEFAULT_LOCATION_REQUEST_FAST_INTERVAL = 10000L;   //10초에서 20초마다 위치 정보를 받는다는 뜻
//    private FusedLocationProviderClient fusedLocationProviderClient;
//    private LocationRequest locationRequest;
//    private double longitude, latitude;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        checkLocationPermission();
//    }
//
//    private void checkLocationPermission() { //권한확인api코드
//        int accessLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
//        if (accessLocation == PackageManager.PERMISSION_GRANTED) {
//            checkLocationSetting();
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_UTIL_LOCATION_PERMISSION_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);//내가 추가한부분
//        if (requestCode == GPS_UTIL_LOCATION_PERMISSION_REQUEST_CODE) { //여기 아래서 팝업도 일어나니까 주석 잘달기
//            for (int i = 0; i < permissions.length; i++) {
//                if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permissions[i])) {
//                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        checkLocationSetting();
//                    } else {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                        builder.setTitle("위치 권한이 꺼져있습니다.");
//                        builder.setMessage("[권한] 설정에서 위치 권한을 허용해야 합니다.");
//                        builder.setPositiveButton("설정으로 가기", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(); //인텐트 사용
//                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                Uri uri = Uri.fromParts("package", getPackageName(), null);
//                                intent.setData(uri);
//                                startActivity(intent);
//                            }
//                        }).setNegativeButton("종료", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish(); //앱종료
//                            }
//                        });
//                        AlertDialog alert = builder.create();
//                        alert.show();
//                    }
//                    break;
//                }
//            }
//        }
//    }
//
//    private void checkLocationSetting() { //환경이 잘 설정 되어있는지 확인
//        locationRequest = LocationRequest.create();  //필요한 정보 정의
//        locationRequest.setPriority(DEFAULT_LOCATION_REQUEST_PRIORITY);   //우선순위 위에서 정의한 전력효율
//        locationRequest.setInterval(DEFAULT_LOCATION_REQUEST_INTERVAL);   //
//        locationRequest.setFastestInterval(DEFAULT_LOCATION_REQUEST_FAST_INTERVAL);
//
//        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).setAlwaysShow(true);
//        settingsClient.checkLocationSettings(builder.build())
//                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
//                    @Override
//                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//                        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(SplashActivity.this);
//                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                            // TODO: Consider calling
//                            //    ActivityCompat#requestPermissions
//                            // here to request the missing permissions, and then overriding
//                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                            //                                          int[] grantResults)
//                            // to handle the case where the user grants the permission. See the documentation
//                            // for ActivityCompat#requestPermissions for more details.
//                            return;
//                        }
//                        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
//                    }
//                })
//                .addOnFailureListener(SplashActivity.this, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        int statusCode = ((ApiException) e).getStatusCode();
//                        switch (statusCode) {
//                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                                try {
//                                    ResolvableApiException rae = (ResolvableApiException) e;
//                                    rae.startResolutionForResult(SplashActivity.this, GPS_UTIL_LOCATION_RESOLUTION_REQUEST_CODE);
//                                } catch (IntentSender.SendIntentException sie) {
//                                    Log.w(TAG, "unable to start resolution for result due to " + sie.getLocalizedMessage());
//                                }
//                                break;
//                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                                String errorMessage = "location settings are inadequate, and cannot be fixed here. Fix in Settings.";
//                                Log.e(TAG, errorMessage);
//                        }
//                    }
//                });
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == GPS_UTIL_LOCATION_RESOLUTION_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                checkLocationSetting();
//            } else {
//                finish();
//            }
//        }
//    }
//
//    private LocationCallback locationCallback = new LocationCallback() {
//        @Override
//        public void onLocationResult(LocationResult locationResult) {
//            super.onLocationResult(locationResult);
//            longitude = locationResult.getLastLocation().getLongitude();
//            latitude = locationResult.getLastLocation().getLatitude();
//            //fusedLocationProviderClient.removeLocationUpdates(locationCallback); 지속적으로 받기위해
//
//            Intent intent = new Intent(SplashActivity.this, frag2.class);
//            intent.putExtra("latitude", latitude);
//            intent.putExtra("longitude", longitude);
//            startActivity(intent);
//            finish();
//        }
//
//        @Override
//        public void onLocationAvailability(LocationAvailability locationAvailability) {
//            super.onLocationAvailability(locationAvailability);
//            Log.i(TAG, "onLocationAvailability - " + locationAvailability);
//        }
//    };
//}