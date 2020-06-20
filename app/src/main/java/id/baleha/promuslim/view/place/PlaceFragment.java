package id.baleha.promuslim.view.place;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import id.baleha.promuslim.R;
import id.baleha.promuslim.utils.SkeletonUtils;
import id.baleha.promuslim.view.sample.ui.samplefragmenttoactivity.SampleFragmentToActivityFragment;
import io.supercharge.shimmerlayout.ShimmerLayout;

public class PlaceFragment extends Fragment implements OnMapReadyCallback {

    public final static String CATEGORY = "placeCategory";
    private static final String TAG = PlaceFragment.class.getSimpleName();
    private GoogleMap mMap;
    private String category;

    //the grographical location where the device is currently located. That is, the last-known
    //location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 20;

    private LocationListener locationListener;
    private LocationManager locationManager;

    //place list
    private PlacesClient mPlacesClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    protected RecyclerView recyclerView;

    //skeleton
    public LinearLayout skeletonLayout;
    public ShimmerLayout shimmer;
    public LayoutInflater inflater;

    public static PlaceFragment newInstance() {
        return new PlaceFragment();
    }

    public static PlaceFragment newInstance(String category) {
        PlaceFragment myFragment = new PlaceFragment();

        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_place, container, false);

        category = getArguments().getString(CATEGORY);

        //place list skeleton
        skeletonLayout = root.findViewById(R.id.skeletonLayout);
        shimmer = root.findViewById(R.id.shimmerSkeleton);
        this.inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Initialize the places client
        String apiKey = getString(R.string.app_google_maps_key);
        Places.initialize(requireContext(), apiKey);
        mPlacesClient = Places.createClient(getActivity());
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        //place list
        recyclerView = root.findViewById(R.id.rv_places_list);

        LinearLayoutManager recyclerLayoutManager =
                new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyclerLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //map
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        //place list skeleton
        SkeletonUtils.showSkeleton(getActivity(),
                inflater,
                shimmer,
                skeletonLayout,
                R.layout.item_place_skeleton, (int) getResources().getDimension(R.dimen.place_bottomsheet_height),
                true
        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Prompt the user for permission.
        getLocationPermission();

        pickCurrentPlace();
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        Log.e("RLOG", "invoke getLocationPermission.");
        Log.e("RLOG", "ktegori: "+category);
        mLocationPermissionGranted = false;
        if (ContextCompat.checkSelfPermission(this.getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.e("RLOG", "permission granted.");
            mLocationPermissionGranted = true;
        } else {
            Log.e("RLOG", "permission cant showup/denied.");
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }


    private void pickCurrentPlace() {
        Log.e("RLOG", "invoke pickCurrentPlace");
        if (mMap == null) {
            Log.e("RLOG", "cannot find mMap!!");
            return;
        }

        if (mLocationPermissionGranted) {
            getDeviceLocation();
        } else {
            // The user has not granted permission.
            Log.e("RLOG", "The user did not grant location permission.");

            // Add a default marker, because the user hasn't selected a place.
            mMap.addMarker(new MarkerOptions()
                    .title(getString(R.string.default_info_title))
                    .position(mDefaultLocation)
                    .snippet(getString(R.string.default_info_snippet)));

            // Prompt the user for permission.
            getLocationPermission();
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        Log.e("RLOG", "invoke getDeviceLocation!!");
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            Log.d(TAG, "Latitude: " + mLastKnownLocation.getLatitude());
                            Log.d(TAG, "Longitude: " + mLastKnownLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                            Geocoder gcd = new Geocoder(getContext(), Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = gcd.getFromLocation(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude(), 1);
//                                addresses = gcd.getFromLocation(-6.775869, 107.804995, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (addresses.size() > 0) {
                                System.out.println(addresses.get(0));
                                Log.i("RLOG", "current city: "+addresses.get(0).getAdminArea());
                            }
                            else {
                                // do your stuff
                                Log.i("RLOG", "cannot get current city: ");
                            }

                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                        }

                        getCurrentPlaceLikelihoods();
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getCurrentPlaceLikelihoods() {
        // Use fields to define the data types to return.
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS,
                Place.Field.LAT_LNG);
        Log.d("RLOG", "current location places info");
        final List<Place> placesList = new ArrayList<Place>();

        // Get the likely places - that is, the businesses and other points of interest that
        // are the best match for the device's current location.
        @SuppressWarnings("MissingPermission") final FindCurrentPlaceRequest request =
                FindCurrentPlaceRequest.builder(placeFields).build();
        @SuppressLint("MissingPermission") Task<FindCurrentPlaceResponse> placeResponse = mPlacesClient.findCurrentPlace(request);
        placeResponse.addOnCompleteListener(getActivity(),
                new OnCompleteListener<FindCurrentPlaceResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<FindCurrentPlaceResponse> task) {
                        if (task.isSuccessful()) {
                            FindCurrentPlaceResponse response = task.getResult();
                            // Set the count, handling cases where less than 5 entries are returned.
                            int count;
                            if (response.getPlaceLikelihoods().size() < M_MAX_ENTRIES) {
                                count = response.getPlaceLikelihoods().size();
                            } else {
                                count = M_MAX_ENTRIES;
                            }

                            int i = 0;
//                            mLikelyPlaceNames = new String[count];
//                            mLikelyPlaceAddresses = new String[count];
//                            mLikelyPlaceAttributions = new String[count];
//                            mLikelyPlaceLatLngs = new LatLng[count];

                            for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                                Place currPlace = placeLikelihood.getPlace();
                                mMap.addMarker(new MarkerOptions().position(currPlace.getLatLng()).title(currPlace.getName()));
//                                mLikelyPlaceNames[i] = currPlace.getName();
//                                mLikelyPlaceAddresses[i] = currPlace.getAddress();
//                                mLikelyPlaceAttributions[i] = (currPlace.getAttributions() == null) ?
//                                        null : TextUtils.join(" ", currPlace.getAttributions());
//                                mLikelyPlaceLatLngs[i] = currPlace.getLatLng();

//                                String currLatLng = (mLikelyPlaceLatLngs[i] == null) ?
//                                        "" : mLikelyPlaceLatLngs[i].toString();

                                Log.i("RLOG", String.format("Place " + currPlace.getName()
                                        + " has likelihood: " + placeLikelihood.getLikelihood()
                                        + " at " + currPlace.getLatLng()));

                                i++;
                                if (i > (count - 1)) {
                                    break;
                                }
                                placesList.add(placeLikelihood.getPlace());
                            }

                            Log.e("RLOG", "jumlah list: " + placesList.size());

                            // COMMENTED OUT UNTIL WE DEFINE THE METHOD
                            // Populate the ListView
//                            fillPlacesList();
                            //place list skeleton
//                            SkeletonUtils.animateReplaceSkeleton(getActivity(),
//                                    inflater,
//                                    shimmer,
//                                    skeletonLayout,
//                                    R.layout.item_place_skeleton, (int) getResources().getDimension(R.dimen.place_bottomsheet_height)
//                            );
                            SkeletonUtils.showSkeleton(getActivity(),
                                    inflater,
                                    shimmer,
                                    skeletonLayout,
                                    R.layout.item_place_skeleton, (int) getResources().getDimension(R.dimen.place_bottomsheet_height),
                                    false
                            );
                            recyclerView.setVisibility(View.VISIBLE);
                            PlaceListAdapter recyclerViewAdapter = new
                                    PlaceListAdapter(placesList,
                                    getActivity());
                            recyclerView.setAdapter(recyclerViewAdapter);
//                            recyclerView.setOnTouchListener(new View.OnTouchListener() {
//                                @Override
//                                public boolean onTouch(View view, MotionEvent motionEvent) {
//                                    view.getParent().requestDisallowInterceptTouchEvent(true);
//                                    view.onTouchEvent(motionEvent);
//                                    return true;
//                                }
//                            });

                        } else {
                            SkeletonUtils.animateReplaceSkeleton(getActivity(),
                                    inflater,
                                    shimmer,
                                    skeletonLayout,
                                    R.layout.item_place_skeleton, (int) getResources().getDimension(R.dimen.place_bottomsheet_height)
                            );
                            Exception exception = task.getException();
                            if (exception instanceof ApiException) {
                                ApiException apiException = (ApiException) exception;
                                Log.e("RLOG", "Place not found: " + apiException.getStatusCode());
                            }
                        }
                    }
                });
    }
}