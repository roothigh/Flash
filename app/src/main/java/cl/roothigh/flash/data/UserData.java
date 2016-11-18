package cl.roothigh.flash.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by SebastianVP on 14-11-2016.
 */

public class UserData {

    public FirebaseUser user() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }
    public String email() {
        return user().getEmail();
    }
}
