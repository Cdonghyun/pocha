package halla.icsw.pocha;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.util.FusedLocationSource;

public class SellerMain extends AppCompatActivity
        implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private boolean isCameraAnimated = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller);

        MapFragment mapFragment =(MapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.sellmap);
        mapFragment.getMapAsync(this);

        AssetManager assetManager = getResources().getAssets();//asset폴더 가져오기

    }

    public void shopStart(View v){

        Intent i = new Intent(getApplicationContext(),Selling.class);
        startActivity(i);

    }



    //    @Override
    public void onCameraChange(int reason, boolean animated) {
        isCameraAnimated = animated;
    }

    //    @Override
    public void onCameraIdle() {
        if (isCameraAnimated) {
            LatLng mapCenter = naverMap.getCameraPosition().target;
            fetchStoreSale(mapCenter.latitude, mapCenter.longitude, 5000);
        }
    }



    private void fetchStoreSale(double lat, double lng, int m) {
       /* Retrofit retrofit = new Retrofit.Builder().baseUrl("https://8oi9s0nnth.apigw.ntruss.com").addConverterFactory(GsonConverterFactory.create()).build();
        MaskApi maskApi = retrofit.create(MaskApi.class);
        maskApi.getStoresByGeo(lat, lng, m).enqueue(new Callback<StoreSaleResult>() {
            @Override // 호출이 성공했을때
            public void onResponse(Call<StoreSaleResult> call, Response<StoreSaleResult> response) {
                if (response.code() == 200) {
                    StoreSaleResult result = response.body();
                    updateMapMarkers(result);
                }
            }

            @Override // 호출이 실패 했을때.
            public void onFailure(Call<StoreSaleResult> call, Throwable t) {

            }
        });*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {//추가 여기
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Face);//위치 방향 등등
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);//현위치 버튼
        uiSettings.setTiltGesturesEnabled(true);//기울기
        uiSettings.setRotateGesturesEnabled(true);//회전
        uiSettings.setScrollGesturesEnabled(true);//스크롤


//        naverMap.addOnCameraChangeListener((NaverMap.OnCameraChangeListener) this);
//        naverMap.addOnCameraIdleListener((NaverMap.OnCameraIdleListener) this);
    }

}

