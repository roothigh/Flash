package cl.roothigh.flash.data;

import android.provider.DocumentsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by SebastianVP on 15-11-2016.
 */

public class FirebaseRef {

    private DatabaseReference root(){
        return FirebaseDatabase.getInstance().getReference();


    }

    public DatabaseReference users(){
        return root().child("users");
    }
    public DatabaseReference chatlist(){
        return root().child("chat_list");
    }
    public DatabaseReference mesages() {
        return root().child("messages");
    }
}
