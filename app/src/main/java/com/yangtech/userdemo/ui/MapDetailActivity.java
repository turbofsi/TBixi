package com.yangtech.userdemo.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yangtech.userdemo.R;
import com.yangtech.userdemo.model.BixiStation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MapDetailActivity extends FragmentActivity implements GoogleMap.OnMapLoadedCallback{

    public static final String TAG = MainActivity.class.getSimpleName();
    private GoogleMap mGoogleMap;
    private BixiStation[] mBixiStations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isPlayServicesAvailable()) {
            setContentView(R.layout.activity_map_detail);
            try {
                FragmentManager myFM = this.getSupportFragmentManager();
                final SupportMapFragment myMAPF = (SupportMapFragment) myFM
                        .findFragmentById(R.id.map);
                mGoogleMap = myMAPF.getMap();
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setCompassEnabled(true);
                mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);


                CameraPosition position = new CameraPosition.Builder().target(new LatLng(43.769228,  -79.412025)).zoom(11).build();
                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String bixiURL = "http://www.bikesharetoronto.com/stations/json";
        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(bixiURL).build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e(TAG, "Exception caught: ", e);
                }

                @Override
                public void onResponse(Response response) throws IOException {

                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mBixiStations = getBixiData(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    for (BixiStation station : mBixiStations) {
                                        MarkerOptions k = new MarkerOptions()
                                                .position(new LatLng(station.getLat(), station.getLon()))
                                                .title(station.getStationName())
                                                .draggable(true)
                                                .snippet(station.getAvailableBikeNum() + " Available");
                                        mGoogleMap.addMarker(k);
                                    }
                                }
                            });
                        } else {
                            showAlertDialog();
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "Error: ", e);
                    }
                }
            });
        }


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (info != null && info.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    protected boolean isPlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (status == ConnectionResult.SUCCESS) {
            return (true);
        } else if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
            // deal with error
        } else {
            // maps is not available
        }

        return (false);
    }

    @Override
    public void onMapLoaded() {
        mGoogleMap.addMarker(new MarkerOptions()
            .position(new LatLng(0, 0))
            .title("hello"));
    }

    private void showAlertDialog() {
        AlertMessageDialog dialog = new AlertMessageDialog();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    private BixiStation[] getBixiData(String jsonData) throws JSONException {
        JSONObject mainJson = new JSONObject(jsonData);

        JSONArray data = mainJson.getJSONArray("stationBeanList");
        BixiStation[] bixiStations = new BixiStation[data.length()];
        for (int i = 0; i < data.length(); i++) {
            BixiStation bixiStation = new BixiStation();
            JSONObject bixiStationObj = data.getJSONObject(i);
            bixiStation.setStationName(bixiStationObj.getString("stationName"));
            bixiStation.setLat(bixiStationObj.getDouble("latitude"));
            bixiStation.setLon(bixiStationObj.getDouble("longitude"));
            bixiStation.setAvailableBikeNum(bixiStationObj.getInt("availableBikes"));
            bixiStations[i] = bixiStation;
        }



        return bixiStations;
    }
}
