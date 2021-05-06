package com.hk_book_store;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by hasan on 23.12.2017.
 */

public class OzelAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Kitap> mKitapBagisiListesi;

    public OzelAdapter(Activity activity, List<Kitap> kisiler) {
        Collections.sort(kisiler, (d1, d2) -> {
            return (int) (d2.getID() - d1.getID());
        });
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mKitapBagisiListesi = kisiler;}

    @Override
    public int getCount() {
        return mKitapBagisiListesi.size();
    }

    @Override
    public Kitap getItem(int position) {
        return mKitapBagisiListesi.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = mInflater.inflate(R.layout.satir_layout, null);
        TextView tvKitapAdi = satirView.findViewById(R.id.tvKitapAdi);
        TextView tvYazarAdi = satirView.findViewById(R.id.tvYazarAdi );
        TextView tvPaylasimTarihi = satirView.findViewById(R.id.tvPaylasimTarihi);
        ImageView list_image = satirView.findViewById(R.id.list_image);
        Kitap kitap = mKitapBagisiListesi.get(position);

        int gelen_tip=kitap.getTip();
        if (gelen_tip==1) list_image.setImageResource(R.drawable.notebook_istek);
        tvKitapAdi.setText( kitap.getKitapAdi());
        tvYazarAdi.setText( kitap.getYazarAdi());
        tvPaylasimTarihi.setText(kitap.getPaylasilanYer()+" / "+kitap.getPaylasimTarihi());
        return satirView;
    }}