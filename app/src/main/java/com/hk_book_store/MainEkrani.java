package com.hk_book_store;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainEkrani extends AppCompatActivity implements Communicator {
    DatabaseReference dbRef, kullaniciIDRef;
    FirebaseAuth user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ekrani);
        dbRef = new FireBasedb().referansEdilen("bagislanankitaplar");
        user = FirebaseAuth.getInstance();


        kullaniciIDRef = new FireBasedb().referansEdilen("Kullanicilar");
        kullaniciIDRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String user1 = user.getCurrentUser().getUid();
                for (DataSnapshot gelen : dataSnapshot.getChildren()) {

                    Kullanici gln = gelen.getValue(Kullanici.class);

                    if (user1.trim().equals(gln.getKullaniciKey().trim())) {
                        TextView tv = findViewById(R.id.kitapistiyorum);
                        tv.setTag(gln.getIl());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void kitabKaydet(int tip, String kitapAdi, String yazarAdi, String paylasanKisi, String paylasimTarihi, String paylasilanYer) {
        dbRef.push().setValue(new KitapBagisi(tip, kitapAdi, yazarAdi, paylasanKisi, paylasimTarihi, paylasilanYer));
    }

    TextView tx;

    public void btn_secim(View v) {
        tx = findViewById(R.id.kitapistiyorum);
        LinearLayout lnr_bir = findViewById(R.id.lnr_kitapbagis);
        LinearLayout lnr_iki = findViewById(R.id.lnr_kitapistiyorum);
        if (lnr_bir.getVisibility() == View.VISIBLE) {
            lnr_bir.setVisibility(View.INVISIBLE);
            lnr_iki.setVisibility(View.INVISIBLE);
        } else {
            lnr_bir.setVisibility(View.VISIBLE);
            lnr_iki.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void btn_kitapbagis(View v) {
        OrtakAlan(0);
    }

    public void btn_kitapistiyorum(View v) {
        OrtakAlan(1);
    }

    public void OrtakAlan(final int tip) {
        final Dialog dialog = new Dialog(MainEkrani.this);
        dialog.setContentView(R.layout.istek_bagis_penceresi);
        dialog.setTitle("Custom Dialog");
        final EditText ed_kitapadi = dialog.findViewById(R.id.kitapAdi);
        final EditText ed_yazaradi = dialog.findViewById(R.id.yazarAdi);
        final EditText ed_sehir = dialog.findViewById(R.id.kullaniciSehir);

        final TextView txtHata = dialog.findViewById(R.id.txtHata);

        txtHata.setText("");
        txtHata.setVisibility(View.INVISIBLE);


        if (tx.getTag() != null)
            ed_sehir.setText(tx.getTag().toString());
        Button btn = dialog.findViewById(R.id.btn);
        dialog.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtHata.setText("");
                txtHata.setVisibility(View.INVISIBLE);

                if ((ed_kitapadi.getText().toString()).trim().equals("")) {
                    txtHata.setText("Kitap Adı boş geçilemez.");
                    txtHata.setVisibility(View.VISIBLE);
                } else {
                    String formatlitarih = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
                    kitabKaydet(tip, ed_kitapadi.getText().toString(), ed_yazaradi.getText().toString(), user.getCurrentUser().getEmail(), formatlitarih, ed_sehir.getText().toString());
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void respond(int data) {
        FragmentManager mng = getFragmentManager();
        FragmentB b = (FragmentB) mng.findFragmentById(R.id.fragment2);
        b.degistir(data);
    }
}