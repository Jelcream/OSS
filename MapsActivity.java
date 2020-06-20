package com.example.localcurrency4;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URI;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() //layout에 있던 fragment 가져옴
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);//fragment 받음
    }


    //여기서 콜백을 받는다 .
    //구글맵이 준비되면 이 메소드가 호출됨
    //구글맵 객체가 들어오고 필드에서 저장
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //위 경도 나타내는 객체
        LatLng suwonStation = new LatLng(37.267328, 126.999365);
        //위치에 포지션을 하고
        mMap.addMarker(new MarkerOptions().position(suwonStation).title("수원역"));
        //그 위치로 카메라를 움직임
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(suwonStation, 16.0f));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01039096030"));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });
    }
}