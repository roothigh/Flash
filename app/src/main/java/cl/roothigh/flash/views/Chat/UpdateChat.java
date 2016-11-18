package cl.roothigh.flash.views.Chat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;

import cl.roothigh.flash.data.FirebaseRef;
import cl.roothigh.flash.data.UserData;

/**
 * Created by SebastianVP on 17-11-2016.
 */

public class UpdateChat {
    private String chatid;

    public UpdateChat(String chatid) {
        this.chatid = chatid;
    }
    public void  send(){
        String uid = new UserData().user().getUid();
        DatabaseReference reference = new FirebaseRef().chatlist().child(uid).child(chatid);
        reference.child("notification").setValue(false);
        reference.child("timestamp").setValue(ServerValue.TIMESTAMP);
    }
}
