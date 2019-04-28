package pe.torganizagroup.easyhotelapp;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public static final int SIGN_INT_CODE = 777;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
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

        callbackManager = CallbackManager.Factory.create ();
        loginButton = (LoginButton) findViewById (R.id.loginButton);
        loginButton.setReadPermissions (Arrays.asList ("email"));
        loginButton.registerCallback (callbackManager, new FacebookCallback<LoginResult> () {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken ());
                Toast.makeText (getApplicationContext (), getString(R.string.success_login), Toast.LENGTH_SHORT).show ();
            }

            @Override
            public void onCancel() {
                Toast.makeText (getApplicationContext (), R.string.cancel_login, Toast.LENGTH_SHORT).show ();
            }

            @Override
            public void onError(FacebookException error) {
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

    private void handleFacebookAccessToken(AccessToken accessToken) {
        pdDialog = ProgressDialog.show (LoginActivity.this, "Iniciando sesión","Comprobando credenciales...", false, false);
        loginButton.setVisibility (View.GONE);

        AuthCredential credential = FacebookAuthProvider.getCredential (accessToken.getToken ());
        firebaseAuth.signInWithCredential (credential).addOnCompleteListener (this, new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful ()){
                    Toast.makeText (getApplicationContext (),  getString(R.string.firebase_error_login), Toast.LENGTH_LONG).show ();
                }
                loginButton.setVisibility (View.VISIBLE);
            }
        });
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
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

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess ()){
            goMainScreen();

            firebaseAuthWithGoogle(Objects.requireNonNull (result.getSignInAccount ()));
            pdDialog = ProgressDialog.show (LoginActivity.this, "Iniciando sesión","Comprobando credenciales...", false, false);

        }else {
            Toast.makeText (this,R.string.not_log_in,Toast.LENGTH_SHORT).show ();
        }
    }

    @Override
    protected void onStart() {
        super.onStart ();
        firebaseAuth.addAuthStateListener (firebaseAuthListener);
    }


    @Override
    protected void onStop() {
        super.onStop ();
        if (firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener (firebaseAuthListener);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

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
