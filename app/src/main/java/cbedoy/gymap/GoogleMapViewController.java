package cbedoy.gymap;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Display;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cbedoy.gymap.artifacts.ApplicationLoader;
import cbedoy.gymap.services.GMapV2Direction;
import cbedoy.gymap.services.GPService;
import cbedoy.gymap.services.GetDirectionsAsyncTask;
import cbedoy.gymap.services.LogService;
import cbedoy.gymap.widgets.LocationDialog;

import static cbedoy.gymap.widgets.LocationDialog.*;
import static com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;

public class GoogleMapViewController extends FragmentActivity implements OnMyLocationButtonClickListener, ILocationDialogCallback {

    private GoogleMap mMap;
    private Polyline newPolyline;

    private float[] randomHue;
    private LatLngBounds latlngBounds;
    private int width;
    private int height;
    private LatLng mPosition;
    private LatLng mDestiny;
    private LatLng mLongPressed;
    private ProgressDialog mProgressDialog;
    private HashMap<String, Object> mInformation;
    private LocationDialog mLocationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mProgressDialog = new ProgressDialog(this);
        randomHue = new float[]{
                BitmapDescriptorFactory.HUE_AZURE,
                BitmapDescriptorFactory.HUE_BLUE,
                BitmapDescriptorFactory.HUE_CYAN,
                BitmapDescriptorFactory.HUE_GREEN,
                BitmapDescriptorFactory.HUE_MAGENTA,
                BitmapDescriptorFactory.HUE_ORANGE,
                BitmapDescriptorFactory.HUE_RED,
                BitmapDescriptorFactory.HUE_ROSE,
                BitmapDescriptorFactory.HUE_VIOLET,
                BitmapDescriptorFactory.HUE_YELLOW
        };

        setUpMapIfNeeded();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng markerSelected = marker.getPosition();
                if (markerSelected.latitude != mPosition.latitude && markerSelected.longitude != mPosition.longitude) {
                    mProgressDialog.setTitle("Loading...");
                    mProgressDialog.setMessage("Please wait while trace the optimal route from your location to marker selected");
                    mProgressDialog.show();
                    mDestiny = markerSelected;
                    findDirections(
                            mPosition.latitude,
                            mPosition.longitude,
                            mDestiny.latitude,
                            mDestiny.longitude,
                            GMapV2Direction.MODE_DRIVING
                    );
                }
                return false;
            }
        });
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng longPressed) {
                if(mLocationDialog == null)
                    mLocationDialog = new LocationDialog(GoogleMapViewController.this);
                mLocationDialog.setCallback(GoogleMapViewController.this);
                mLocationDialog.show();
                mLongPressed = longPressed;
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(this);
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                mLocationDialog = new LocationDialog(this);

            }

        }
    }

    private void setUpMap() {
        mInformation                                            = (HashMap<String, Object>) getIntent().getSerializableExtra("dataInApp");
        ArrayList<HashMap<String, Object>> selected_locations   = (ArrayList<HashMap<String, Object>>) mInformation.get("selected_locations");
        int max = randomHue.length;
        int index = 0;
        for (HashMap<String, Object> location : selected_locations)
        {
            MarkerOptions markerOptions                         = new MarkerOptions();
            double latitude                                     = Double.parseDouble(location.get("latitude").toString());
            double longitude                                    = Double.parseDouble(location.get("longitude").toString());
            String city                                         = location.get("city").toString();
            String country                                      = location.get("country").toString();
            String company                                      = location.get("company").toString();
            String full_name                                    = location.get("full_name").toString();
            String phone                                        = location.get("phone").toString();
            LatLng latLng                                       = new LatLng(latitude, longitude);
            String description                                  =   "" + city + " | " +
                                                                    "" + country + " | ";
            String snippet                                      = company + " \n" + full_name + " \n " + phone;
            markerOptions.position(latLng);
            markerOptions.title(description);
            markerOptions.snippet(snippet);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(randomHue[index++]));
            mMap.addMarker(markerOptions);
            index = index == max ? 0 : index;
        }

        GPService gpService = new GPService(GoogleMapViewController.this);

        if (gpService.canGetLocation()) {
            double latitude = gpService.getLatitude();
            double longitude = gpService.getLongitude();

            mPosition = new LatLng(latitude, longitude);

            HashMap<String, Object> login_response = (HashMap<String, Object>) mInformation.get("login_response");
            HashMap<String, Object>  login_data = (HashMap<String, Object>) login_response.get("login_data");
            String username = login_data.get("username").toString();
            String first_name = login_data.get("first_name").toString();
            String last_name = login_data.get("last_name").toString();

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(mPosition);
            String googleUser = ApplicationLoader.getUsername();
            markerOptions.title((googleUser.length() > 0 ? googleUser : "Desconcido") + " | " + username );
            markerOptions.snippet(first_name + " " + last_name);

            mMap.addMarker(markerOptions);

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(latitude, longitude), 16));
        } else {
            gpService.showSettingsAlert();
        }

        getSreenDimanstions();

        mProgressDialog.hide();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }


    public void handleGetDirectionsResult(ArrayList<LatLng> directionPoints) {
        PolylineOptions rectLine = new PolylineOptions().width(5).color(Color.parseColor("#FAFAFA"));

        for (int i = 0; i < directionPoints.size(); i++) {
            rectLine.add(directionPoints.get(i));
        }
        if (newPolyline != null) {
            newPolyline.remove();
        }
        newPolyline = mMap.addPolyline(rectLine);

        latlngBounds = createLatLngBoundsObject(mPosition, mDestiny);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latlngBounds, width, height, 150));

        mProgressDialog.hide();

    }



    private void getSreenDimanstions()
    {
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
    }


    private LatLngBounds createLatLngBoundsObject(LatLng firstLocation, LatLng secondLocation)
    {
        if (firstLocation != null && secondLocation != null)
        {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(firstLocation).include(secondLocation);

            return builder.build();
        }
        return null;
    }

    private void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat, double toPositionDoubleLong, String mode)
    {
        Map<String, String> map = new HashMap<>();
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(fromPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(fromPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(toPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(toPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, mode);

        GetDirectionsAsyncTask asyncTask = new GetDirectionsAsyncTask(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, map);
        }
        else
        {
            asyncTask.execute(map);
        }
    }

    @Override
    public void run(String... values) {

        if(mLongPressed != null)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(mLongPressed);
            markerOptions.title(values[0] + " " + values[1]);
            markerOptions.snippet(values[2] + " " + values[2]);
            mMap.addMarker(markerOptions);
            LogService.e("Location Saved");
        }
    }
}
