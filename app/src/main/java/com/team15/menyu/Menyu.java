package com.team15.menyu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
    private List<String> possible_places = new ArrayList<String>();
    private int possible_places_count = 0;
    private static final int LOCATION_PERMISSION = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient.Builder(Menyu.this)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .build();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
        } else {
            new PostTask().execute("DAMN SON ASYNC TASK HERE");
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    new PostTask().execute("DAMN SON ASYNC TASK HERE");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    finish();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void next() {

        Intent intent = new Intent(this, LocationActivity.class);
        intent.putExtra("PLACES_COUNT", possible_places_count);
        intent.putStringArrayListExtra("PLACES", (ArrayList<String>) possible_places);
        startActivity(intent);
        finish();
    }

    // The definition of our task class
    private class PostTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Finding your location...", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url=params[0];
            getCurrentPlaces();

            while(possible_places_count <= 0) {
                try {
                    Thread.sleep(50);
                    Log.v(LOG_TAG, "waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Done";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            next();
        }
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
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                //Log.i(LOG_TAG, "hi");
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    if (placeLikelihood.getLikelihood() > 0.1 || placeLikelihood.getPlace().getPlaceTypes().contains(38)) {
                        possible_places.add(placeLikelihood.getPlace().getName().toString());
                        //Log.i(LOG_TAG, String.format("Place '%s' with type: num '%d'",
                        //        placeLikelihood.getPlace().getName(), possible_places_count));
                        possible_places_count += 1;

                    }
                }
                likelyPlaces.release();

            }

        });

    }
}
