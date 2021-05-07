package com.hk_book_store;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hasan on 25.12.2017.
 */

public class FireBasedb {
    private FirebaseDatabase database;
    private DatabaseReference dbRef;

    public DatabaseReference referansEdilen(String ref) {
        database = FirebaseDatabase.getInstance();
        this.setDbRef(database.getReference(ref));
        return getDbRef();
    }

    public DatabaseReference getDbRef() {
        return dbRef;
    }

    private void setDbRef(DatabaseReference dbRef) {
        this.dbRef = dbRef;
    }
}
