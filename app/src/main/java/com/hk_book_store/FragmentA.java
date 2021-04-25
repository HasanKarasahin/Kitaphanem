package com.hk_book_store;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class FragmentA extends Fragment {

    TextView[] butonlar=new TextView[3];
    Button[] borderlar=new Button[3];
    Communicator comm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View gorunum = inflater.inflate( R.layout.activity_fragment,container ,false);
        return gorunum;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        comm= (Communicator) getActivity();

        butonlar[0]=getActivity().findViewById( R.id.btntikla );
        butonlar[1]=getActivity().findViewById( R.id.btntikla1 );
        butonlar[2]=getActivity().findViewById( R.id.btntikla2 );

        borderlar[0]=getActivity().findViewById( R.id.border1 );
        borderlar[1]=getActivity().findViewById( R.id.border2 );
        borderlar[2]=getActivity().findViewById( R.id.border3 );

        butonlar[0].setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecilenButonBorder(0);
                comm.respond( 0 );
            }
        } );
        butonlar[1].setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecilenButonBorder(1);
                comm.respond( 1 );
            }
        } );
        butonlar[2].setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecilenButonBorder(2);
                comm.respond( 2 );
            }
        } );
    }
    public void SecilenButonBorder(int indis){
        int sayac=0;
        for (Button btn:borderlar) {
            if(indis!=sayac)
                btn.setVisibility(View.INVISIBLE );
            else
                btn.setVisibility(View.VISIBLE );
            sayac++;
        }
    }
}
