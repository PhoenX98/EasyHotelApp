package pe.torganizagroup.easyhotelapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneAuthActivity extends Activity {

    private EditText txtTelefono;
    private EditText txtCodigo;
    private String idVerificacion;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Button acceder;
    private ImageView imgLogo;
    private TextView count,sentence;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_phone_auth);

        txtTelefono = (EditText) findViewById (R.id.txtTelefono);
        txtCodigo = (EditText) findViewById (R.id.txtCodigo);
        imgLogo = (ImageView) findViewById (R.id.imgLogo);
        count = (TextView) findViewById (R.id.txtCount);
        sentence = (TextView) findViewById (R.id.txtSentence);
        acceder = (Button) findViewById (R.id.btnEnviar);

        txtTelefono.requestFocus ();
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        assert imm != null;
//        imm.showSoftInput (txtTelefono, InputMethodManager.SHOW_IMPLICIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        txtTelefono.addTextChangedListener (phoneWatcher);

        Glide.with (getApplicationContext ())
                .load (R.drawable.logo_easy_hotel_min)
                .into (imgLogo);

        mAuth = FirebaseAuth.getInstance ();
        mAuthStateListener = new FirebaseAuth.AuthStateListener (){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Toast.makeText(PhoneAuthActivity.this, getString(R.string.now_logged_in), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PhoneAuthActivity.this, DrawableActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        txtCodigo.addTextChangedListener (codeWatcher);

    }

    private final TextWatcher phoneWatcher = new TextWatcher () {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length () == 9){
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtTelefono.getWindowToken(), 0);
            }
        }
    };

    private  final TextWatcher codeWatcher = new TextWatcher () {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length () == 6){
                String code = txtCodigo.getText().toString();

                if (TextUtils.isEmpty(code))
                    return;

                Intent intent = new Intent(PhoneAuthActivity.this, DrawableActivity.class);
                startActivity(intent);
                signInWithCredential(PhoneAuthProvider.getCredential(idVerificacion, code));
                Toast.makeText (PhoneAuthActivity.this, "Verificando...",Toast.LENGTH_LONG).show ();
            }
        }
    };

    public void requestCode(View view){
        String codigoPais = "+51";
        String Telefono = txtTelefono.getText ().toString ();
        String phoneNumber = codigoPais + Telefono;

        setCountDown();

        int res = 60;
        if(TextUtils.isEmpty (phoneNumber))
            return;
        PhoneAuthProvider.getInstance ().verifyPhoneNumber (

                phoneNumber, res, TimeUnit.SECONDS, PhoneAuthActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks () {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        signInWithCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(PhoneAuthActivity.this, "Verificacion Fallida : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verificationId, forceResendingToken);
                        Toast.makeText (PhoneAuthActivity.this, "Enviando codigo...",Toast.LENGTH_LONG).show ();
                        idVerificacion = verificationId;

                    }

                    @Override
                    public void onCodeAutoRetrievalTimeOut(String verificationId) {
                        super.onCodeAutoRetrievalTimeOut(verificationId);
                        Toast.makeText(PhoneAuthActivity.this, "Se agoto el tiempo de respuesta :" + verificationId, Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }

    private void setCountDown() {

        int res = 60;
        count.setText (res+"");

        count.setVisibility (View.VISIBLE);
        sentence.setVisibility (View.VISIBLE);
        txtCodigo.setVisibility (View.VISIBLE);
        txtCodigo.requestFocus ();
        Tiempo regresivo = new Tiempo (61000,1000);
        regresivo.start ();
        acceder.setEnabled (false);
    }
    public class Tiempo extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public Tiempo(long millisInFuture, long countDownInterval) {
            super (millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            count.setText ("" + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            Toast.makeText (getApplicationContext (),"Se acabo el tiempo de espera, vuelva a pedir el codigo por favor",Toast.LENGTH_SHORT).show ();
            acceder.setEnabled (true);
            count.setText (60+"");
            count.setVisibility (View.INVISIBLE);
            sentence.setVisibility (View.INVISIBLE);
        }
    }

    private void signInWithCredential(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PhoneAuthActivity.this, R.string.signed_success, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PhoneAuthActivity.this, getString(R.string.sign_credential_fail)
                                    + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



}
