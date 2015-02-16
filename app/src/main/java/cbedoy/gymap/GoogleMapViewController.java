package cbedoy.gymap;

import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

import cbedoy.gymap.artifacts.ApplicationLoader;
import cbedoy.gymap.artifacts.Memento;
import cbedoy.gymap.assambly.MainAssambly;
import cbedoy.gymap.interfaces.IMementoHandler;
import cbedoy.gymap.interfaces.INotificationMessages;
import cbedoy.gymap.services.GPService;

public class GoogleMapViewController extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback
{

    private GoogleMap mMap;
    private IMementoHandler mementoHandler;
    private INotificationMessages notificationMessages;

    private float[] randomHue;

    public void setMementoHandler(IMementoHandler mementoHandler) {
        this.mementoHandler = mementoHandler;
    }

    public void setNotificationMessages(INotificationMessages notificationMessages) {
        this.notificationMessages = notificationMessages;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        MainAssambly.getInstance().provideInstances(this);
        setUpMapIfNeeded();
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
            }
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
    }

    private void setUpMap()
    {
        Memento topMemento                                      = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData                     = topMemento.getMementoData();
        ArrayList<HashMap<String, Object>> selected_locations   = (ArrayList<HashMap<String, Object>>) mementoData.get("selected_locations");
        int max                                                 = randomHue.length;
        int index                                               = 0;
        for(HashMap<String, Object> location : selected_locations){
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
                                                                    "" + country + " | " +
                                                                    "" + company + " | " ;
            String snippet                                      = full_name + " | " + phone;
            markerOptions.position(latLng);
            markerOptions.title(description);
            markerOptions.snippet(snippet);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(randomHue[index++]));
                    mMap.addMarker(markerOptions);
            index = index == max ? 0 : index;
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*MarkerOptions markerOptions = new MarkerOptions();

        LocationManager locationManager = new LocationManager();
        locationManager.get
        markerOptions.position(new LatLng(location.getLatitude(), location.getLongitude()));
        String username = ApplicationLoader.getUsername();
        markerOptions.title(username.length() > 0 ? username : "Desconcido");

        mMap.addMarker(markerOptions);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.getLatitude(), location.getLongitude()), 16));*/

        GPService gpService = new GPService(GoogleMapViewController.this);

        // check if GPS enabled
        if(gpService.canGetLocation()){

            double latitude = gpService.getLatitude();
            double longitude = gpService.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpService.showSettingsAlert();
        }
    }
}
