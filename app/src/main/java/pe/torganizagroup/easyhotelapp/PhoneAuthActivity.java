package pe.torganizagroup.easyhotelapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
        if(TextUtils.isEmpty (phoneNumber))
            return;
        PhoneAuthProvider.getInstance ().verifyPhoneNumber (
                phoneNumber, 60, TimeUnit.SECONDS, PhoneAuthActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks () {
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
