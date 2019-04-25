package pe.torganizagroup.easyhotelapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    //Variables para inicio de sesion con google
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public static final int SIGN_INT_CODE = 777;
    //Variables para inicio de sesion con facebook
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    //variables para inicio de sesion tanto con facebook como con google
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private ProgressDialog pdDialog;

    private Button phone;
    private TextView txt1;
    private TextView txt2;
    private String direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        phone = (Button) findViewById (R.id.btnPhoneAuth);
        direccion="";

        phone.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent siguiente = new  Intent (LoginActivity.this, PhoneAuthActivity.class);
                startActivity (siguiente);
            }
        });

        Thread thread = new Thread (new Runnable() {
            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences (getBaseContext ());
                boolean isFirstStart = getPrefs.getBoolean ("firstStart", true);
                if (isFirstStart){
                    startActivity (new Intent (LoginActivity.this, MyIntro.class));
                    SharedPreferences.Editor e = getPrefs.edit ();
                    e.putBoolean ("firstStart",false);
                    e.apply ();
                }
            }
        });

        thread.start ();


        //Metodo callback manager para
        callbackManager = CallbackManager.Factory.create ();
        //asignar metodo callbackmanager al boton
        loginButton = (LoginButton) findViewById (R.id.loginButton);
        //Formas de pedir permisos al usuario para que vea que tipos de datos extraemos
        loginButton.setReadPermissions (Arrays.asList ("email"));
        //Metodo para manejar los eventos al inicio de sesion
        loginButton.registerCallback (callbackManager, new FacebookCallback<LoginResult> () {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Ingreso exitoso
                //goMainScreen ();
                handleFacebookAccessToken(loginResult.getAccessToken ());
                Toast.makeText (getApplicationContext (), getString(R.string.success_login), Toast.LENGTH_SHORT).show ();
            }

            @Override
            public void onCancel() {
                //Proceso cancelado
                Toast.makeText (getApplicationContext (), R.string.cancel_login, Toast.LENGTH_SHORT).show ();
            }

            @Override
            public void onError(FacebookException error) {
                //error al realizar la conexion a facebook
                Toast.makeText (getApplicationContext (), R.string.error_login, Toast.LENGTH_SHORT).show ();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken (getString (R.string.default_web_client_id))
                .requestEmail ()
                .build();

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton = findViewById(R.id.signInButton);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setColorScheme(SignInButton.COLOR_DARK);

        signInButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent (googleApiClient);
                startActivityForResult (intent,SIGN_INT_CODE);
            }
        });

        //el AuthListener se ejecuta cuando el login fue exitoso
        //metodo que se ejecuta cuando se ejecuta algun cambio en la autenticacion
        firebaseAuth = FirebaseAuth.getInstance ();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener () {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser ();
                if (user != null){
                    goMainScreen ();
                }
            }
        };
    }

    //esta funcion se ejecuta cuando se completa el proceso
    //metodo que se ejecuta cuando termina todo el proceso
    private void handleFacebookAccessToken(AccessToken accessToken) {
//        progressBar.setVisibility (View.VISIBLE);
        pdDialog = ProgressDialog.show (LoginActivity.this, "Iniciando sesión","Comprobando credenciales...", false, false);
        loginButton.setVisibility (View.GONE);

        AuthCredential credential = FacebookAuthProvider.getCredential (accessToken.getToken ());
        firebaseAuth.signInWithCredential (credential).addOnCompleteListener (this, new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful ()){
                    Toast.makeText (getApplicationContext (),  getString(R.string.firebase_error_login), Toast.LENGTH_LONG).show ();
                }
//                progressBar.setVisibility (View.GONE);|
                loginButton.setVisibility (View.VISIBLE);
            }
        });
    }

    //metodo de firebase con google
    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
        //Progress bar dialog
        pdDialog = ProgressDialog.show (LoginActivity.this, "Iniciando sesión","Comprobando credenciales...", false, false);
        signInButton.setVisibility (View.GONE);
        AuthCredential credential = GoogleAuthProvider.getCredential (signInAccount.getIdToken (), null);
        firebaseAuth.signInWithCredential (credential).addOnCompleteListener (this, new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful ()){
                    Toast.makeText (getApplicationContext (), getString(R.string.not_firebase_auth),Toast.LENGTH_SHORT).show ();
                }
                signInButton.setVisibility (View.VISIBLE);
            }
        });
    }

    //metodo para manejar el resultado de inicio de sesion en google
    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess ()){
            goMainScreen();

            firebaseAuthWithGoogle(Objects.requireNonNull (result.getSignInAccount ()));
            pdDialog = ProgressDialog.show (LoginActivity.this, "Iniciando sesión","Comprobando credenciales...", false, false);

        }else {
            Toast.makeText (this,R.string.not_log_in,Toast.LENGTH_SHORT).show ();
        }
    }

    //el AuthListener empieza su funcion en esta clase
    @Override
    protected void onStart() {
        super.onStart ();
        //Añade el listener
        firebaseAuth.addAuthStateListener (firebaseAuthListener);
        //PARA INICIAR EL MODO SIN LOGEO EN LA APP AL SALIR Y VOLVER A ENTRAR
        goMainScreen ();
    }

    //el AuthListener termina su funcion en esta clase
    @Override
    protected void onStop() {
        super.onStop ();
        //
        if (firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener (firebaseAuthListener);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //funcion para redireccionar a la activity principal
    private void goMainScreen() {
        Intent intent = new Intent (this, DrawableActivity.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult (requestCode, resultCode, data);
        super.onActivityResult (requestCode, resultCode, data);

        if(requestCode == SIGN_INT_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent (data);
            handleSignInResult(result);
        }

    }


}
