package licttrainer.ttinnovations.lictmonitor;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,  GoogleMap.OnMapLongClickListener
{
    private ObjectMapper objectMapper;
    private  University[] list;
    private LatLng youarehre;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private LocationRequest mLocationRequest;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private String url="http://27.147.210.136:8080/bht/GetUniversities";
    private  LatLng latlong;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            boolean b = checkLocationPermission();
            if(b)
            {
                Toast.makeText(getApplicationContext(),"Permissions are OK",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Permissions are not OK",Toast.LENGTH_LONG).show();
            }
        }

        String s = null;
        try {
            s = new GetXMLTask().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // String s="[{\"id\":1,\"name\":\"Dhaka_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7315541,\"longitude\":90.3903007},{\"id\":2,\"name\":\"Jagganath_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7084457,\"longitude\":90.4090477},{\"id\":3,\"name\":\"Government_Titumir_College\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":false,\"program_fs\":true,\"latitude\":23.7820286,\"longitude\":90.4014513},{\"id\":4,\"name\":\"Eden_Women_College\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":false,\"program_fs\":true,\"latitude\":23.7276293,\"longitude\":90.384323},{\"id\":5,\"name\":\"Shahjalal_University_for_Science_&_Technology_(SUST)\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":24.9202699,\"longitude\":91.8300247},{\"id\":6,\"name\":\"Chittagong_University_of_Engineering_and_Technology_(CUET)\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":22.4600282,\"longitude\":91.9688062},{\"id\":7,\"name\":\"Chittagong_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":22.4710315,\"longitude\":91.7911092},{\"id\":8,\"name\":\"Carmichael_College_Rangpur\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":25.7245228,\"longitude\":89.2527202},{\"id\":9,\"name\":\"Begum_Rokeya_University_Rangpur\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":25.717928,\"longitude\":89.2569997},{\"id\":10,\"name\":\"Rajshahi_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":24.3687178,\"longitude\":88.6380972},{\"id\":11,\"name\":\"Rajshahi_Univeristy_of_Engineering_and_Technology\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":24.3635732,\"longitude\":88.6261833},{\"id\":12,\"name\":\"Khulna_University_of_Engineering_and_Technology\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":22.9005197,\"longitude\":89.500162},{\"id\":13,\"name\":\"Comilla_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.4194179,\"longitude\":91.134266},{\"id\":14,\"name\":\"Pabna_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":24.0132601,\"longitude\":89.2762113},{\"id\":15,\"name\":\"Nohakali_Science_&_Technology_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":22.7933742,\"longitude\":91.098341},{\"id\":16,\"name\":\"Hajee_Mohammad_Danesh_Science_&_Technology_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":25.69742,\"longitude\":88.6489174},{\"id\":17,\"name\":\"Barisal_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":22.6596956,\"longitude\":90.3601774},{\"id\":18,\"name\":\"Bangabandhu_Sheikh_Mujibur_Rahman_Science_and_Technology_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":22.9661004,\"longitude\":89.8149412},{\"id\":19,\"name\":\"Mawlana_Bhashani_Science_and_Technology_University\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":24.2353426,\"longitude\":89.8896432},{\"id\":20,\"name\":\"Jessore_University_of_Science_and_Technology\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.2334394,\"longitude\":89.1232235},{\"id\":21,\"name\":\"Islamic_Universaity\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.8348177,\"longitude\":90.2950915},{\"id\":22,\"name\":\"Patuakhali_Science_and_Technology_Univetrsity\",\"university_type\":\"public\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":22.4648189,\"longitude\":90.3813736},{\"id\":23,\"name\":\"Daffodil_International_University\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7528373,\"longitude\":90.3754852},{\"id\":24,\"name\":\"East_West_University\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.768649,\"longitude\":90.4233975},{\"id\":25,\"name\":\"BRAC_University\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7801728,\"longitude\":90.4049974},{\"id\":26,\"name\":\"Asia_Pacific_University\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7549804,\"longitude\":90.3870857},{\"id\":27,\"name\":\"North_South_University\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.8150937,\"longitude\":90.4233465},{\"id\":28,\"name\":\"United_International_University\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.753764,\"longitude\":90.3683573},{\"id\":29,\"name\":\"Eastern_University\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7424154,\"longitude\":90.3783927},{\"id\":30,\"name\":\"American_International_University_Bangladesh\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7947001,\"longitude\":90.4003057},{\"id\":31,\"name\":\"Independent_University_Bangladesh\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.8165261,\"longitude\":90.333012},{\"id\":32,\"name\":\"Dhaka_City_College\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.8067264,\"longitude\":90.2823461},{\"id\":33,\"name\":\"University_of_Information_Technology_and_Sciences\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7945107,\"longitude\":90.4224667},{\"id\":34,\"name\":\"University_of_Liberal_Arts_Bangladesh\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7408644,\"longitude\":90.3722765},{\"id\":35,\"name\":\"Stamford_University\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.7823678,\"longitude\":90.3675521},{\"id\":36,\"name\":\"Varendra_University\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":24.3623997,\"longitude\":88.6169003},{\"id\":37,\"name\":\"International_University_of_Business_Agriculture_and_Technology\",\"university_type\":\"private\",\"date\":\"2017-02-03\",\"program_topup\":true,\"program_fs\":true,\"latitude\":23.8882861,\"longitude\":90.3886816}]";
        if(s.length()<4){Toast.makeText(getApplicationContext(),"No Batch Scheduled for the Day",Toast.LENGTH_LONG).show();}
        System.out.println(" Data from Server "+s);
            objectMapper=new ObjectMapper();
        try {
            list = objectMapper.readValue(s, University[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private boolean checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setMinZoomPreference(7);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(getApplicationContext(), "i am Clicked", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
                 }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

              }
        mMap.setMyLocationEnabled(true);
        System.out.println("Total Length -"+list.length);
        for (int i=0;i<list.length;i++){
            System.out.println("Status - "+list[i].getStatus());
            if(list[i].getStatus().equalsIgnoreCase("scheduled")) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(list[i].getLatitude(), list[i].getLongitude())).title(list[i].getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(list[i].getLatitude(), list[i].getLongitude())));
            }
            if(list[i].getStatus().equalsIgnoreCase("Started")) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(list[i].getLatitude(), list[i].getLongitude())).title(list[i].getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(list[i].getLatitude(), list[i].getLongitude())));
            }
            if(list[i].getStatus().equalsIgnoreCase("cancled")) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(list[i].getLatitude(), list[i].getLongitude())).title(list[i].getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(list[i].getLatitude(), list[i].getLongitude())));
            }


            //*/mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(o.getLatitude(),o.getLongitude())));
                //new LatLng(23.8150937, 90.4233465)
                //mMap.addMarker(new MarkerOptions().position(nsu).title("North South University"));
                //  mMap.moveCamera(CameraUpdateFactory.newLatLng(nsu));

        }
      /*  LatLng nsu = new LatLng(23.8150937, 90.4233465);
        LatLng ewu = new LatLng(23.768649, 90.4233975);
        LatLng jnu = new LatLng(23.7084457, 90.4090477);
        LatLng brac = new LatLng(23.7801728, 90.4049974);
        LatLng aiub = new LatLng(23.7947001, 90.4003057);
        LatLng iubat = new LatLng(23.8882861, 90.3886816);
        LatLng iub = new LatLng(23.8165261, 90.333012);
        LatLng dcc = new LatLng(23.8067264, 90.2823461);
        LatLng sub = new LatLng(23.7823678, 90.3675521);
        LatLng du = new LatLng(23.7315541, 90.3903007);*/
        //

        /*mMap.addMarker(new MarkerOptions().position(nsu).title("North South University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nsu));
        mMap.addMarker(new MarkerOptions().position(ewu).title("East West University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ewu));
        mMap.addMarker(new MarkerOptions().position(jnu).title("JaganNath University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jnu));
        mMap.addMarker(new MarkerOptions().position(brac).title("BRAC University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(brac));
        mMap.addMarker(new MarkerOptions().position(aiub).title("American international University of Bangladesh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(aiub));
        mMap.addMarker(new MarkerOptions().position(iubat).title("Independent University of Bangladesh agriculture and Technology"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(iubat));
        mMap.addMarker(new MarkerOptions().position(iub).title("Independent University of Bangladesh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(iub));
        mMap.addMarker(new MarkerOptions().position(dcc).title("Dhaka City College"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dcc));
        mMap.addMarker(new MarkerOptions().position(sub).title("Stamford University of Bangladesh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sub));
        mMap.addMarker(new MarkerOptions().position(du).title("Dhaka University"));*/
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(du));
        mMap.setBuildingsEnabled(true);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    /*protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }*/
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println(location.getLongitude() +" You are Here "+location.getLatitude());
        // youarehre=new LatLng();
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
     // mMap.addMarker(new MarkerOptions().position(youarehre).title("You are Here").flat(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.index)));
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("You are Here");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(7));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      /*  super.onRequestPermissionsResult(requestCode, permissions, grantResults);*/
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

   @Override
    public boolean onMarkerClick(Marker marker) {
        System.out.println("Marker Clicked");
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
      intent.putExtra("data",marker.getTitle());
        startActivity(intent);
        return false;
    }

    @Override
    public void onMapLongClick(LatLng lat) {

        System.out.println("Long Marker Clicked");

        Intent intent=new Intent(getApplicationContext(),TrainerInformation.class);
        //intent.putExtra("")

        startActivity(intent);
    }


}


