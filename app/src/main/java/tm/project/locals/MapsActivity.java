package tm.project.locals;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;


import java.util.ArrayList;

import static android.Manifest.permission;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    public static MapsActivity map;
    private GoogleMap mMap;
    FusedLocationProviderClient mfusedLocationClient;
    private static final int REQUEST_CODE_PERMISSIONS = 1000;

    public static ArrayList<LocalShop>[] LS;
    public static int index;
    public static int index2;

    public static Location mlocation;
    protected LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() //layout에 있던 fragment 가져옴
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);//fragment 받음
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        map = this;
        //현재 위치얻고 여러 위치정보 관련 기능 제공하는 객체 얻음
        mfusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        GPSCheck();
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
        //mMap.addMarker(new MarkerOptions().position(suwonStation).title("수원역"));
        //그 위치로 카메라를 움직임
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(suwonStation, 16.0f));

        //title 클릭했을 때 전화를 걸수 있도록 함=> 상세정보 보여주는 걸로 변경할 것임
        //현재위치 등의 기능 사용직전에 체크를 해줘야 함
        if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //권한이 없으면 permission 요청->다이얼로그가 뜰 것임 , 사용자가 권한 승낙 거부 할 수 있음
            ActivityCompat.requestPermissions(this,
                    new String[]{permission.ACCESS_FINE_LOCATION, permission
                            .ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);
            //return;
        }
        //권한이 있으면 이 메소드 호출-good
        //mfusedLocationClient에서 마지막 위치를 가져오는 것을 성공하면 OnSuccessListener가 동작하면
        mfusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            //이 메소드 호출
            public void onSuccess(Location location) {
                //location이 null일 수 있으니 null 체크는 해줘야 함
                if (location != null) {
                    //location에 위도와 경도가 들어있다.
                    Log.v("tagging", "로케이션 테스트");
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());//위도 경도 잡아주고
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    mMap.addMarker(markerOptions
                            .position(mylocation)
                            .title("현재 위치"));//위도 경도를 맵에 표시
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                    mlocation = location;
                    settingMark();
                }
            }
        });
        //분류에 따라 음식점 위치 마커 뿌리는 메소드 호출하기
        //데이터 확인하고 이름이 같은 데이터 데려오기
    }

    //버튼 삭제
    /*
    //버튼 눌렀을 때 현재위치를 나타내기-1
    public void onLastLocationClicked(View view) {
        //현재위치 등의 기능 사용직전에 체크를 해줘야 함
        if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //권한이 없으면 permission 요청->다이얼로그가 뜰 것임 , 사용자가 권한 승낙 거부 할 수 있음
            ActivityCompat.requestPermissions(this,
                    new String[] {permission.ACCESS_FINE_LOCATION, permission
                            .ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);
            return;
        }
        //권한이 있으면 이 메소드 호출-good
        //mfusedLocationClient에서 마지막 위치를 가져오는 것을 성공하면 OnSuccessListener가 동작하면
        mfusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            //이 메소드 호출
            public void onSuccess(Location location) {
                //location이 null일 수 있으니 null 체크는 해줘야 함
                if(location != null){
                    //location에 위도와 경도가 들어있다.
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());//위도 경도 잡아주고
                    mMap.addMarker(new MarkerOptions()
                    .position(mylocation)
                    .title("현재 위치"));//위도 경도를 맵에 표시
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                }
            }
        });
        //분류에 따라 음식점 위치 마커 뿌리는 메소드 호출하기
        //데이터 확인하고 이름이 같은 데이터 데려오기
    }
     */
    //사용자 요청 처리, 권한요청수락하면 여기서 받아서 다시 체크-2
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                //권한 승인 떨어졌는지 한번 더 체크
                if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager
                        .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission
                        .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //권한이 없다면
                    Toast.makeText(this, "권한 체크 거부됨", Toast.LENGTH_SHORT).show();
                }
                mfusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    //이 메소드 호출
                    public void onSuccess(Location location) {
                        //location이 null일 수 있으니 null 체크는 해줘야 함
                        if (location != null) {
                            //location에 위도와 경도가 들어있다.
                            LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());//위도 경도 잡아주고
                            mMap.addMarker(new MarkerOptions()
                                    .position(mylocation)
                                    .title("현재 위치"));//위도 경도를 맵에 표시
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                        }
                    }
                });

        }
    }

    public void settingMark() {
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01039096030"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        double distance;
        //다중 마커
        //Log.v("settingMark","checking mark");
        mMap.clear();
        if(index == 2) Log.i("#########", LS[index].get(1).getCategory1());
        for (int i = 0; i < LS[index].size(); i++) {
            distance = mlocation.distanceTo(LS[index].get(i).getLocal());
            Log.v("@@@@","거리 구하기 "+distance);
            if(distance/1000 <= 3 && (index != 2 || LS[index].get(i).getCategory2().equals(ClickedItemActivity.LIST_MENU4[index2]))) {
                MarkerOptions makerOptions = new MarkerOptions();
                makerOptions.position(LS[index].get(i).getLocation()).title(LS[index].get(i).getName());
                makerOptions.snippet(LS[index].get(i).getCategory2());
                if(LS[index].get(i).getExemplary()) makerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                if(LS[index].get(i).getKind()) makerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                mMap.addMarker(makerOptions);
            }
        }
        //mMap.clear(); // 마커 지우기
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(LS.get(LS.size()-1).getLocation()));
    }

    public void makerEvent() {
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

            }
        });
    }

    public void GPSCheck() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //GPS 설정화면으로 이동
            Toast myToast = Toast.makeText(this.getApplicationContext(),"GPS를 켜주세요",Toast.LENGTH_SHORT);
            myToast.show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        }
    }

}