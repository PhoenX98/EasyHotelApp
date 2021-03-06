package pe.torganizagroup.easyhotelapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FullscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_fullscreen);

        new Handler ().postDelayed (new Runnable () {
            @Override
            public void run() {
                Intent i = new Intent (FullscreenActivity.this, LoginActivity.class);
                i.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity (i);
            }
        }, 3500);
    }
}
