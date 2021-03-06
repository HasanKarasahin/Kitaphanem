package com.hk_book_store;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<Mesaj> mesajList;
    FirebaseUser fUser;

    public CustomAdapter(Activity activity, ArrayList<Mesaj> mesajList, FirebaseUser fUser) {
        this.mesajList = mesajList;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fUser = fUser;
    }

    @Override
    public int getCount() {
        return mesajList.size();
    }

    @Override
    public Object getItem(int position) {
        return mesajList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satir = null;
        Mesaj mesaj = mesajList.get(position);
        if (mesaj.getGonderen().equals(fUser.getEmail())) {
            satir = layoutInflater.inflate(R.layout.custom_sag, null);
            TextView mailim = satir.findViewById(R.id.textViewBen);
            mailim.setText(mesaj.getGonderen());
            TextView mesajim = satir.findViewById(R.id.textViewMesajim);
            mesajim.setText(mesaj.getMesaj());
            TextView zamanim = satir.findViewById(R.id.textViewZamanim);
            zamanim.setText(mesaj.getZaman());
        } else {
            satir = layoutInflater.inflate(R.layout.custom_sol, null);
            TextView gonderenMail = satir.findViewById(R.id.textViewGonderenKisi);
            gonderenMail.setText(mesaj.getGonderen());
            TextView mesaji = satir.findViewById(R.id.textViewMesaji);
            mesaji.setText(mesaj.getMesaj());
            TextView zamani = satir.findViewById(R.id.textViewZamani);
            zamani.setText(mesaj.getZaman());
        }
        return satir;
    }
}