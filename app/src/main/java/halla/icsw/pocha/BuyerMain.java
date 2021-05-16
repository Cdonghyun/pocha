package halla.icsw.pocha;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BuyerMain extends AppCompatActivity
        implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private boolean isCameraAnimated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); //타이틀바 숨기기

        MapFragment mapFragment =(MapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
//    @Override
    public void onCameraIdle() { //위치 불러오기
        if (isCameraAnimated) {
            LatLng mapCenter = naverMap.getCameraPosition().target;
            fetchStoreSale(mapCenter.latitude, mapCenter.longitude, 5000);
        }
    }


    private void fetchStoreSale(double lat, double lng, int m) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://8oi9s0nnth.apigw.ntruss.com").addConverterFactory(GsonConverterFactory.create()).build();
//
//        maskApi.getStoresByGeo(lat, lng, m).enqueue(new Callback<StoreSaleResult>() {
//            @Override
//            public void onResponse(Call<StoreSaleResult> call, Response<StoreSaleResult> response) {
//                if (response.code() == 200) {
//                    StoreSaleResult result = response.body();
//                    updateMapMarkers(result);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<StoreSaleResult> call, Throwable t) {
//
//            }
//        });
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

    }

}
