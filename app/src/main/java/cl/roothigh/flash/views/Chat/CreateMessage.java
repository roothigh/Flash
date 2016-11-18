package cl.roothigh.flash.views.Chat;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

import cl.roothigh.flash.data.FirebaseRef;
import cl.roothigh.flash.data.UserData;
import cl.roothigh.flash.models.Message;

import static android.R.attr.id;

/**
 * Created by SebastianVP on 17-11-2016.
 */

public class CreateMessage {

    private String chatId;

    private Message msg ;

    private CreateMessageCallback callback;

    public CreateMessage(String chatId, String message, CreateMessageCallback callback) {
        this.chatId = chatId;
        this.callback = callback;
        String userEmail = new UserData().email();
        msg =  new Message(message, userEmail);
    }
    public void send() {
        if (msg.message.trim().length() > 0){new FirebaseRef().mesages().child(chatId).push().setValue(msg).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callback.clear();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.error("Nose pudo enviar , intentalo mas tarde");

            }
        });


        }


    }

}
