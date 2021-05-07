package com.hk_book_store;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;

import android.os.Bundle;

import androidx.annotation.RequiresApi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentB extends Fragment {


    DatabaseReference myRef;
    final List<Kitap> kisiler = new ArrayList<Kitap>();
    ListView listemiz;
    Kitap ktp;
    Communicator comm;
    private LayoutInflater mInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View gorunum = inflater.inflate(R.layout.activity_fragment_b, container, false);
        return gorunum;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();
        listemiz = getActivity().findViewById(R.id.liste);
        myRef = new FireBasedb().referansEdilen("bagislanankitaplar");
        TumVeriListele();
        mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView kitap = view.findViewById(R.id.tvKitapAdi);
                Intent odaIntent = new Intent(getActivity(), ChatEkrani.class);
                odaIntent.putExtra("odaKey", kitap.getText().toString());
                startActivity(odaIntent);
            }
        });
    }

    public void TumVeriListele() {
        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kisiler.clear();
                for (DataSnapshot gelen : dataSnapshot.getChildren()) {
                    ktp = gelen.getValue(Kitap.class);
                    kisiler.add(new Kitap(ktp.getTip(), ktp.getKitapAdi(), ktp.getYazarAdi(), ktp.getPaylasanKisi(), ktp.getPaylasimTarihi(), ktp.getPaylasilanYer()));
                }
                listemiz.setAdapter(new OzelAdapter(getActivity(), kisiler));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void KitapTalebVeriListele() {
        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kisiler.clear();
                for (DataSnapshot gelen : dataSnapshot.getChildren()) {
                    ktp = gelen.getValue(Kitap.class);
                    int tip = ktp.getTip();
                    if (tip == 1) {
                        kisiler.add(new Kitap(ktp.getTip(), ktp.getKitapAdi(), ktp.getYazarAdi(), ktp.getPaylasanKisi(), ktp.getPaylasimTarihi(), ktp.getPaylasilanYer()));
                    }
                }
                listemiz.setAdapter(new OzelAdapter(getActivity(), kisiler));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void BugunVeriListele() {
        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String formatlitarih = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                kisiler.clear();
                for (DataSnapshot gelen : dataSnapshot.getChildren()) {
                    ktp = gelen.getValue(Kitap.class);
                    String[] paylasimtarihi = (ktp.getPaylasimTarihi()).split(" ");
                    if (paylasimtarihi[0].equals(formatlitarih)) {
                        kisiler.add(new Kitap(ktp.getTip(), ktp.getKitapAdi(), ktp.getYazarAdi(), ktp.getPaylasanKisi(), ktp.getPaylasimTarihi(), ktp.getPaylasilanYer()));
                    }
                }
                listemiz.setAdapter(new OzelAdapter(getActivity(), kisiler));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void degistir(int secilenBaslik) {
        if (secilenBaslik == 0) TumVeriListele();
        else if (secilenBaslik == 1) BugunVeriListele();
        else if (secilenBaslik == 2) KitapTalebVeriListele();
    }
}