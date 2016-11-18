package cl.roothigh.flash.views.main.createChat;

/**
 * Created by SebastianVP on 15-11-2016.
 */

public interface VerificationCallback {
    void invalid(String error);
    void self(String error);
    void notFound(String error);
    void success();
}
