package id.baleha.promuslim.model;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class GetPlaces extends AsyncTask<String, Void, String>{

    private boolean updateFinished;

    public boolean isUpdateFinished() {
        return updateFinished;
    }

    public void setUpdateFinished(boolean updateFinished) {
        this.updateFinished = updateFinished;
    }


    @Override
    protected String doInBackground(String... placesURL) {
        //fetch markerOptions
        updateFinished = false;
        StringBuilder placesBuilder = new StringBuilder();
        for (String placeSearchURL : placesURL) {
            try {

                URL requestUrl = new URL(placeSearchURL);
                HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = null;

                    InputStream inputStream = connection.getInputStream();
                    if (inputStream == null) {
                        return "";
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {

                        placesBuilder.append(line + "\n");
                    }

                    if (placesBuilder.length() == 0) {
                        return "";
                    }

                    Log.d("test", placesBuilder.toString());
                } else {
                    Log.i("test", "Unsuccessful HTTP Response Code: " + responseCode);
                    return "";
                }
            } catch (MalformedURLException e) {
                Log.e("test", "Error processing Places API URL", e);
                return "";
            } catch (IOException e) {
                Log.e("test", "Error connecting to Places API", e);
                return "";
            }
        }
        return placesBuilder.toString();
    }

    //process data retrieved from doInBackground
//    protected void onPostExecute(String result) {
//        //parse place data returned from Google Places
//        //remove existing markers
//        if (markers != null) {
//            for (int pm = 0; pm < markers.length; pm++) {
//                if (markers[pm] != null)
//                    markers[pm].remove();
//            }
//        }
//        try {
//            //parse JSON
//
//            //create JSONObject, pass stinrg returned from doInBackground
//            JSONObject resultObject = new JSONObject(result);
//            //get "results" array
//            JSONArray placesArray = resultObject.getJSONArray("results");
//            //marker options for each place returned
//            markerOptions = new MarkerOptions[placesArray.length()];
//            //loop through markerOptions
//
//
//
//            for (int p = 0; p < placesArray.length(); p++) {
//                //parse each place
//                //if any values are missing we won't show the marker
//                boolean missingValue = false;
//                LatLng placeLL = null;
//                String placeName = "";
//                String vicinity = "";
//
//                try {
//                    //attempt to retrieve place data values
//                    missingValue = false;
//                    //get place at this index
//                    JSONObject placeObject = placesArray.getJSONObject(p);
//                    //get location section
//                    JSONObject loc = placeObject.getJSONObject("geometry")
//                            .getJSONObject("location");
//                    //read lat lng
//                    placeLL = new LatLng(Double.valueOf(loc.getString("lat")),
//                            Double.valueOf(loc.getString("lng")));
//                    //get types
//                    JSONArray types = placeObject.getJSONArray("types");
//                    //loop through types
//                    for (int t = 0; t < types.length(); t++) {
//                        //what type is it
//                        String thisType = types.get(t).toString();
//                        //check for particular types - set icons
//                        if (thisType.contains("mosque")) {
//                            //			currIcon = masjidIcon;
//                            break;
//                        } else if (thisType.contains("health")) {
//                            //	currIcon = drinkIcon;
//                            break;
//                        } else if (thisType.contains("doctor")) {
//                            //	currIcon = shopIcon;
//                            break;
//                        }
//                    }
//                    //vicinity
//                    vicinity = placeObject.getString("vicinity");
//                    //name
//                    placeName = placeObject.getString("name");
//                } catch (JSONException jse) {
//                    Log.v("PLACES", "missing value");
//                    Toast.makeText(MainActivity.this,"Could not fetch data from server", Toast.LENGTH_LONG).show();
//                    missingValue = true;
//                    jse.printStackTrace();
//                }
//                //if values missing we don't display
//                if (missingValue) markerOptions[p] = null;
//                else
//                    markerOptions[p] = new MarkerOptions()
//                            .position(placeLL)
//                            .title(placeName)
//                            .snippet(vicinity).icon(BitmapDescriptorFactory.fromResource(R.drawable.mosq));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(MainActivity.this,"Could not fetch data from server", Toast.LENGTH_LONG).show();
//        }
//        if (markerOptions != null && markers != null) {
//
//
//            for (int p = 0; p < markerOptions.length && p < markers.length; p++) {
//                //will be null if a value was missing
//
//                if (markerOptions[p] != null) {
//
//                    markers[p] = googleMap.addMarker(markerOptions[p]);
//
//                }
//            }
//
//
//        }
//
//        updateFinished=true;
//    }


}
