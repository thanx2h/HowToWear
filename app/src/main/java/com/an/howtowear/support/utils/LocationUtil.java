package com.an.howtowear.support.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.an.howtowear.HTWApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationUtil {

    private static LocationUtil locationUtil;
    private static LocationManager locationManager;

    private LocationUtil(){}

    public static synchronized LocationUtil getInstance() {
        if (locationUtil == null) {
            locationUtil = new LocationUtil();
        }

        return locationUtil;
    }

    private synchronized LocationManager getLocationManager() {
        if(locationManager == null) {
            locationManager = (LocationManager) HTWApp.getContext().getSystemService(Context.LOCATION_SERVICE);
        }
        return locationManager;
    }

    public boolean isLocationProviderEnabled() {
        boolean isGPSEnabled = getLocationManager().isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = getLocationManager().isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        return (isGPSEnabled || isNetworkEnabled) ? true : false;
    }

    public void requestLocationUpdates(LocationListener locationListener) {
        try{
            getLocationManager().requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } catch (SecurityException e){
            HTWLog.e(e.getMessage());
        }
    }

    public String requestLocationManually() {
        double lng = 0, lat = 0;

        try{
            String locationProvider = LocationManager.GPS_PROVIDER;
            Location lastKnownLocation = getLocationManager().getLastKnownLocation(locationProvider);
            if (lastKnownLocation != null) {
                lng = lastKnownLocation.getLatitude();
                lat = lastKnownLocation.getLatitude();
            }
        } catch (SecurityException e) {
            HTWLog.e(e.getMessage());
        }

        return lng + ":" + lat;
    }

    public void removeLocationUpdates(LocationListener locationListener) {
        try{
            getLocationManager().removeUpdates(locationListener);
            locationManager = null;
        } catch (SecurityException e){
            HTWLog.e(e.getMessage());
        }
    }

    public Address getAddressFromLatLon(double latitude, double longitude){

        Geocoder geocoder = new Geocoder(HTWApp.getContext(), Locale.KOREA);
        Address address = null;

        try {
            address = geocoder.getFromLocation(latitude, longitude, 1).get(0);
            HTWLog.i("latitude : " + latitude + " longitude : " + longitude + " address : " + address.toString());
        } catch (Exception e){
            HTWLog.e(e.getMessage());
            address = new Address(new Locale(Locale.getDefault().getCountry()));
        }

        //addressList == null이 될 수 있으니 방어코드 삽입 필요
        return address;
    }
}
