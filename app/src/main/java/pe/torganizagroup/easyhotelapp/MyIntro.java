package pe.torganizagroup.easyhotelapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.ISlidePolicy;

import pe.torganizagroup.easyhotelapp.Fragment.Intro1;
import pe.torganizagroup.easyhotelapp.Fragment.Intro2;
import pe.torganizagroup.easyhotelapp.Fragment.Intro3;

public class MyIntro extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate (savedInstanceState);

        Intro1 fragment1 = new Intro1 ();
        Intro2 fragment2 = new Intro2 ();
        Intro3 fragment3 = new Intro3 ();

        addSlide (fragment1);
        addSlide (fragment2);
        addSlide (fragment3);

        showStatusBar (false);
        showSkipButton (false);
        showDoneButton (true);
        setFadeAnimation ();
        setBarColor (Color.TRANSPARENT);
        askForPermissions (new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},3);

    }

    @Override
    public void onDonePressed(){
        startActivity (new Intent (this, LoginActivity.class));
        finish ();
    }

    @Override
    public void onNextPressed() {
        super.onNextPressed ();
    }

//    @Override
//    public boolean isPolicyRespected() {
//        return false;
//    }
//
//    @Override
//    public void onUserIllegallyRequestedNextPage() {
//
//    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
