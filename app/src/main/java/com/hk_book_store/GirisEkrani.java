package com.hk_book_store;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GirisEkrani extends AppCompatActivity {

    private EditText girisEmail,girisParola;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_giris_ekrani );
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null) startActivity(new Intent(GirisEkrani.this,MainEkrani.class));
        girisEmail = findViewById(R.id.girisEmail);
        girisParola = findViewById(R.id.girisParola);
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser() != null) startActivity(new Intent(GirisEkrani.this,MainEkrani.class));
    }

    public void girisButtonClick(View btn) {
        String email = girisEmail.getText().toString();
        final String parola = girisParola.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Lütfen emailinizi giriniz", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(parola)) {
            Toast.makeText(getApplicationContext(), "Lütfen parolanızı giriniz", Toast.LENGTH_SHORT).show();
            return;
        }
       
        auth.signInWithEmailAndPassword(email, parola)
                .addOnCompleteListener(GirisEkrani.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(GirisEkrani.this,MainEkrani.class));}
                        else {
                            Toast.makeText(getApplicationContext(), "Giriş Hatasi", Toast.LENGTH_SHORT).show();
                        }}
                });}
    public void uyeOlButtonClick(View v){
        startActivity(new Intent(GirisEkrani.this, KayitEkrani.class));}
    public void yeniSifreButtonClick(View btn){
        startActivity(new Intent(GirisEkrani.this, YeniParolaEkrani.class));}}
