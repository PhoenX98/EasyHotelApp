package pe.torganizagroup.easyhotelapp;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class EasyHotelApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate ();
        //Para activar servicios de SDK Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

}
