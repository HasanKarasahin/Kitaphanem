package com.hk_book_store;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class KayitEkrani extends AppCompatActivity {

    private EditText uyeEmail, uyeParola;
    private Button yeniUyeButton, uyeGirisButton;
    private FirebaseAuth auth;
    private Spinner spn_il;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekrani);

        auth = FirebaseAuth.getInstance();
        uyeEmail = findViewById(R.id.uyeEmail);
        uyeParola = findViewById(R.id.uyeParola);
        yeniUyeButton = findViewById(R.id.yeniUyeButton);
        uyeGirisButton = findViewById(R.id.uyeGirisButton);
        spn_il = findViewById(R.id.iller);

        yeniUyeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = uyeEmail.getText().toString();
                String parola = uyeParola.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Lütfen emailinizi giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(parola)) {
                    Toast.makeText(getApplicationContext(), "Lütfen parolanızı giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (parola.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Parola en az 6 haneli olmalıdır", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spn_il.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "İl Seç", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.createUserWithEmailAndPassword(email, parola)
                        .addOnCompleteListener(KayitEkrani.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(KayitEkrani.this, "Yetkilendirme Hatası",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    String str = "Kullaniciler";
                                    DatabaseReference db = new FireBasedb().referansEdilen("Kullanicilar/" + auth.getCurrentUser().getUid());
                                    db.setValue(new Kullanici(auth.getCurrentUser().getEmail().toString(), auth.getCurrentUser().getUid().toString(), spn_il.getSelectedItem().toString()));
                                    startActivity(new Intent(KayitEkrani.this, MainEkrani.class));
                                    finish();
                                }
                            }
                        });
            }
        });
        uyeGirisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KayitEkrani.this, GirisEkrani.class));
                finish();
            }
        });
    }
}
