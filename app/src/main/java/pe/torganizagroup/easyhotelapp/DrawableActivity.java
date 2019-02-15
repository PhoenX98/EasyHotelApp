package pe.torganizagroup.easyhotelapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import pe.torganizagroup.easyhotelapp.Fragment.busqueda_fragment;
import pe.torganizagroup.easyhotelapp.Fragment.lista_hoteles_fragment;
import pe.torganizagroup.easyhotelapp.Fragment.mapa_fragment;
import pe.torganizagroup.easyhotelapp.Fragment.menu_inicio_fragment;

import static pe.torganizagroup.easyhotelapp.R.drawable.user;

public class DrawableActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    Location location;
    LocationManager locationManager;
    LocationListener locationListener;
    AlertDialog alert = null;

    private static final int REQUEST_ACCESS_FINE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_drawable);

        Toolbar toolbar = findViewById (R.id.toolbar);

        if (ActivityCompat.checkSelfPermission (this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions (this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_FINE);
        }

//        /****Mejora****/
//        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
//            AlertNoGps();
//        }
//        /********/

        setSupportActionBar (toolbar);
        Objects.requireNonNull (getSupportActionBar ()).setLogo (R.drawable.logo_easy_miniatura);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        DrawerLayout drawer = findViewById (R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener (toggle);
        toggle.syncState ();

        NavigationView navigationView = findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);

        FragmentManager fragmentManager=getSupportFragmentManager ();
        fragmentManager.beginTransaction ().replace (R.id.contenedor, new mapa_fragment ()).commit ();

        @SuppressLint("CutPasteId")
        View header = ((NavigationView)findViewById (R.id.nav_view)).getHeaderView (0);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail ()
                .build ();

        googleApiClient = new GoogleApiClient.Builder (this)
                .enableAutoManage (this, this)
                .addApi (Auth.GOOGLE_SIGN_IN_API, gso)
                .build ();

        //firebase google
        firebaseAuth = FirebaseAuth.getInstance ();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener () {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser ();
                //Cuando se arregle el error de firebase, raconectar el codigo de abajo de vuelta
                //solucion temporal de acceso al menu

                if (user != null) {
                    setUserData (user);
                } else {
                    goLogInScreen ();
                }
            }
        };
    }

    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent (android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }

    private void setUserData(FirebaseUser user) {

        View header = ((NavigationView)findViewById (R.id.nav_view)).getHeaderView (0);
        FirebaseAuth.getInstance ().getCurrentUser ();

        if (user != null) {
            String phone = user.getPhoneNumber ();
            String name = user.getDisplayName ();
            String email = user.getEmail ();
            Uri photoUrl = user.getPhotoUrl ();

            ((TextView) header.findViewById (R.id.txtPhone)).setText (phone);
            ((TextView) header.findViewById(R.id.nameTextView)).setText(name);
            ((TextView) header.findViewById(R.id.emailTextView)).setText(email);
            ((TextView) header.findViewById(R.id.emailTextView)).setText(email);

            if( photoUrl == null ){
                Glide.with (this)
                        .load (R.drawable.user)
                        .into ((ImageView) header.findViewById (R.id.photoImageView));
            } else {
                Glide.with (this)
                        .load (photoUrl)
                        .into ((ImageView) header.findViewById (R.id.photoImageView));
            }

            if (name == null ){
                ((TextView) header.findViewById(R.id.nameTextView)).setText("Anónimo");
            } else {
                ((TextView) header.findViewById(R.id.nameTextView)).setText(name);
            }


        } else {
            goLogInScreen ();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult (requestCode, permissions, grantResults);
        //Solicita los permisos de ubicacion en la app
        if(requestCode == REQUEST_ACCESS_FINE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText (this, "Permiso Otorgado", Toast.LENGTH_SHORT).show ();

            else
                Toast.makeText (this, "Permiso denegado", Toast.LENGTH_SHORT).show ();

        }

    }

    @Override
    protected void onStart() {
        super.onStart ();
        firebaseAuth.addAuthStateListener (firebaseAuthListener);
    }

    private void goLogInScreen() {
        Intent intent = new Intent (this, LoginActivity.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity (intent);
    }

    public void logOut(MenuItem item) {
        //funcion para cerrar sesion en facebook
        FirebaseAuth.getInstance ().signOut ();
        LoginManager.getInstance ().logOut ();
        //funcion para cerrar sesion en google
        firebaseAuth.signOut ();
        Auth.GoogleSignInApi.signOut (googleApiClient).setResultCallback (new ResultCallback<Status> () {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess ()) {
                    finish ();
                } else {
                    Toast.makeText (getApplicationContext (), getString (R.string.not_close_session), Toast.LENGTH_SHORT).show ();
                }
            }
        });

    }

    public void revoke(MenuItem item) {
        firebaseAuth.signOut ();

        Auth.GoogleSignInApi.revokeAccess (googleApiClient).setResultCallback (new ResultCallback<Status> () {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess ()) {
                    goLogInScreen ();
                } else {
                    Toast.makeText (getApplicationContext (), getString (R.string.not_revoke), Toast.LENGTH_SHORT).show ();
//                    signInButton.setVisibility (View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById (R.id.drawer_layout);
        if (drawer.isDrawerOpen (GravityCompat.START)) {
            drawer.closeDrawer (GravityCompat.START);
        } else {
            super.onBackPressed ();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.drawable, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId ()){
//            case R.id.menu_cat:
//                break;
//            case R.id.menu_cerca:
//                break;
//            case R.id.menu_mayor_precio:
//                break;
//            case R.id.menu_menor_precio:
//                break;
//        }
        return super.onOptionsItemSelected (item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId ();
        FragmentManager fragmentManager = getSupportFragmentManager ();

        if (id == R.id.nav_inicio) {
            fragmentManager.beginTransaction ().replace (R.id.contenedor, new mapa_fragment ()).commit ();
        } else if (id == R.id.nav_lista_hotel) {
            fragmentManager.beginTransaction ().replace (R.id.contenedor, new lista_hoteles_fragment ()).commit ();
        } else if (id == R.id.nav_busqueda_avanzada) {
            fragmentManager.beginTransaction ().replace (R.id.contenedor, new busqueda_fragment ()).commit ();
        } else if (id == R.id.nav_mapa) {
            fragmentManager.beginTransaction ().replace (R.id.contenedor, new mapa_fragment ()).commit ();
        } else if (id == R.id.nav_revocar) {
            finish ();
        } else if (id == R.id.nav_cerrar_sesion) {
            finish ();
        }

        DrawerLayout drawer = findViewById (R.id.drawer_layout);
        drawer.closeDrawer (GravityCompat.START);
        return true;
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    protected void onStop() {
        super.onStop ();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener (firebaseAuthListener);
        }
    }
}
