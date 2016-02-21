package com.team15.menyu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;
import java.util.List;

public class Menyu extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private static final String LOG_TAG = "PlacesAPIActivity";
    public static List<String> possible_places = new ArrayList<String>();
    public static int possible_places_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient.Builder(Menyu.this)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .build();

        getCurrentPlaces();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                next();
            }
        }, 2000);


    }
    private void next() {
        Intent intent = new Intent(this, ScrollListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    private void getCurrentPlaces() throws SecurityException {
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(mGoogleApiClient, null);
        Log.i(LOG_TAG, "hi1");
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                Log.i(LOG_TAG, "hi");
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    if (placeLikelihood.getLikelihood() > 0.1 || placeLikelihood.getPlace().getPlaceTypes().contains(38)) {
                        possible_places.add(placeLikelihood.getPlace().getName().toString());
                        Log.i(LOG_TAG, String.format("Place '%s' with type: num '%d'",
                                placeLikelihood.getPlace().getName(), possible_places_count));
                        possible_places_count += 1;

                    }
                }
                likelyPlaces.release();

            }

        });
    }
}
