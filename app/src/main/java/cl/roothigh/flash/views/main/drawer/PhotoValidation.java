package cl.roothigh.flash.views.main.drawer;

import android.content.Context;

/**
 * Created by SebastianVP on 14-11-2016.
 */

public class PhotoValidation {

    private Context context ;
    private PhotoValidationCallback callback;

    public PhotoValidation(Context context, PhotoValidationCallback callback) {
        this.context = context;
        this.callback = callback;
    }
    public void init(){
        String url = new PhotoData(context).getUrl();
        if (url== null){
            callback.selfie();
        }else {
            callback.setPhoto(url);

        }

    }


}
