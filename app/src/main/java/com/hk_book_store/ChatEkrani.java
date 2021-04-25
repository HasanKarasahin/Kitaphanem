package com.hk_book_store;

import java.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
public class ChatEkrani extends AppCompatActivity {
    TextView tvBaslik;
    EditText et_mesaj;
    Button buttonGonder;
    ListView lv_chatyap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ekrani);

        tvBaslik=  findViewById(R.id.textView3);
        et_mesaj=findViewById(R.id.editTextMesaj);
        buttonGonder=  findViewById(R.id.buttonMesajGonder);
        lv_chatyap=  findViewById(R.id.listViewChatYap);
        lv_chatyap.setDivider(null); 

        String oda= getIntent().getStringExtra("odaKey");
        tvBaslik.setText(oda);

        final ArrayList<Mesaj> mesajList=new ArrayList<Mesaj>();
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference mesajRef=new FireBasedb().referansEdilen( "chats/"+oda );


        buttonGonder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String gonderen=firebaseUser.getEmail();
                String mesaj=et_mesaj.getText().toString();
                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
                String zaman=sdf.format(new Date());

                mesajRef.push().setValue(new Mesaj(gonderen,mesaj,zaman));
                et_mesaj.setText("");
            }
        });

        final CustomAdapter adapter=new CustomAdapter(this,mesajList,firebaseUser);

        mesajRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mesajList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    mesajList.add(ds.getValue(Mesaj.class));
                }
                lv_chatyap.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}