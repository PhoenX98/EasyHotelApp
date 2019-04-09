package pe.torganizagroup.easyhotelapp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroBase;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.ISlidePolicy;

import pe.torganizagroup.easyhotelapp.Fragment.Intro1;
import pe.torganizagroup.easyhotelapp.Fragment.Intro2;
import pe.torganizagroup.easyhotelapp.Fragment.Intro3;
import pe.torganizagroup.easyhotelapp.Fragment.lista_hoteles_fragment;

public class MyIntro extends AppIntro2 implements ISlidePolicy {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        Intro1 fragment1 = new Intro1 ();
        Intro2 fragment2 = new Intro2 ();
        Intro3 fragment3 = new Intro3 ();
        addSlide (fragment1);
        addSlide (fragment2);
        addSlide (fragment3);

        showStatusBar (false);
        showSkipButton (false);
        setFadeAnimation ();
        setBarColor (Color.TRANSPARENT);
//        askForPermissions (new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, 3);

//        isPolicyRespected ();
    }

    @Override
    public void onDonePressed(){
        startActivity (new Intent (this, DrawableActivity.class));
        finish ();
    }

    @Override
    public boolean isPolicyRespected() {

        return false;
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
