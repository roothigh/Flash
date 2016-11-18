package cl.roothigh.flash.views.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;

import cl.roothigh.flash.R;
import cl.roothigh.flash.views.main.MainActivity;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class FullscreenActivity extends AppCompatActivity implements LoginCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        new LoginValidation(this).init();


    }

    @Override
    public void loged() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void singUp() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(
                                AuthUI.EMAIL_PROVIDER)
                        .setTheme(R.style.FullscreenTheme)
                        .build(),

                RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            loged();
        }
    }
}

