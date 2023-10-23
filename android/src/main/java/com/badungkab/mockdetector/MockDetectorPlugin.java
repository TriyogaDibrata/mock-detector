package com.badungkab.mockdetector;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "MockDetector")
public class MockDetectorPlugin extends Plugin {

    private LocationManager lm;
    private boolean mock = false;
    private String msg = "";
    
    @Override
    public void load() {
        try {
            lm = (LocationManager) this.bridge.getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        } catch (Exception e) {
            msg = "error: " + e.getMessage();
            e.printStackTrace();
        }
    }
    
    @PluginMethod
    public void detectMock(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("isMock", mock);
        ret.put("message", msg);
        call.resolve(ret);
    }

    protected void showCurrentLocation() {
        try {
            if (ActivityCompat.checkSelfPermission(this.bridge.getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.bridge.getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                msg = "no permission";
                return;
            }
            @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                mock = isMockLocationOn(location);
                msg = "location detected";
            }else{
                msg = "location not detected";
            }
        } catch (Exception e) {
            msg = "error: " + e.getMessage();
        }
    }

    public boolean isMockLocationOn(Location location) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return location.isMock();
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return location.isFromMockProvider();
        } else {
            String mockLocation = "0";
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    mockLocation = Settings.Secure.getString(this.bridge.getActivity().getApplicationContext().getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return !mockLocation.equals("0");
        }
    }
}
