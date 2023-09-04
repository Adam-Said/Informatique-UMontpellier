package com.hai811i.tp2capteurs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

public class GeolocalizationActivity  extends AppCompatActivity {
    private MapView map;
    private static final int REQUEST_LOCATION = 1;
    private TextView locDisp;
    LocationManager lm;
    String latitude, longitude;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        org.osmdroid.config.Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        setContentView(R.layout.activity_geolocalization);
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK); //utilise le design pattern Factory
        map.setBuiltInZoomControls(true);
        locDisp = findViewById(R.id.locationDisplay);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location currentLocation = null;
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            currentLocation = getLocation();
        }

        if (currentLocation != null) {
            GeoPoint startPoint = new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
            locDisp.setTextSize(14);
            locDisp.setText(getResources().getString(R.string.position) + " : " + currentLocation.getLatitude() + " / " + currentLocation.getLongitude());
            IMapController mapController = map.getController();
            mapController.setCenter(startPoint);
            mapController.setZoom(20.0);
            ArrayList<OverlayItem> items = new ArrayList<>();
            OverlayItem position = new OverlayItem("Votre position", "Actualisée", new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude()));
            Drawable m = position.getMarker(0);
            items.add(position);
            ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<>(getApplicationContext(), items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                @Override
                public boolean onItemSingleTapUp(int index, OverlayItem item) {
                    return true;
                }

                @Override
                public boolean onItemLongPress(int index, OverlayItem item) {
                    return false;
                }
            });

            mOverlay.setFocusItemsOnTap(true);
            map.getOverlays().add(mOverlay);
        } else {
            // Handle the case where currentLocation is null
            Toast.makeText(this, "Could not get location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        map.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        map.onResume();
    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    private Location getLocation() {
        // Check if GPS is enabled
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
            return null;
        }

        // Check if any providers are available
        List<String> providers = lm.getProviders(true);
        if (providers.isEmpty()) {
            Toast.makeText(this, "No location providers available", Toast.LENGTH_SHORT).show();
            return null;
        }

        // Get last known location from available providers
        Location lastKnownLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(GeolocalizationActivity.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GeolocalizationActivity.this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            } else {
                Location location = lm.getLastKnownLocation(provider);
                if (location != null && (lastKnownLocation == null || location.getTime() > lastKnownLocation.getTime())) {
                    lastKnownLocation = location;
                }
            }
        }

        // If no last known location, try to get a new one
        if (lastKnownLocation == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            String bestProvider = lm.getBestProvider(criteria, true);
            if (bestProvider != null) {
                lm.requestLocationUpdates(bestProvider, 0, 0, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        // Remove location updates to save battery
                        lm.removeUpdates(this);
                    }

                    @Override
                    public void onProviderDisabled(String provider) {}

                    @Override
                    public void onProviderEnabled(String provider) {}

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {}
                });
                lastKnownLocation = lm.getLastKnownLocation(bestProvider);
            }
        }

        return lastKnownLocation;
    }


}