package cl.roothigh.flash.views.main.createChat;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.ybq.android.spinkit.SpinKitView;

import cl.roothigh.flash.R;

/**
 * Created by SebastianVP on 15-11-2016.
 */

public class DialogCreateChat  extends DialogFragment implements  VerificationCallback{
    private EditText userInput;
    private ImageButton button;
    private SpinKitView loading;

    public static DialogCreateChat newInstance(){
        return new DialogCreateChat();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userInput = (EditText) view.findViewById(R.id.inputTv);
        button = (ImageButton) view.findViewById(R.id.sendBtn);
        loading= (SpinKitView) view.findViewById(R.id.loadingChat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInput.setVisibility(View.GONE);
                button.setVisibility(view.GONE);
                loading.setVisibility(View.VISIBLE);
                getDialog().setCancelable(false);
                String email = userInput.getText().toString();
                new CreateChat(getContext(), DialogCreateChat.this, email).init();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
    }

    @Override
    public void invalid(String error) {
        error(error);

    }

    @Override
    public void self(String error) {
        error(error);
    }

    @Override
    public void notFound(String error) {
        error(error);

    }
    private void error (String error ){
        loading.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        userInput.setVisibility(View.VISIBLE);
        userInput.setError(error);
        getDialog().setCancelable(true);
    }

    @Override
    public void success() {
        dismiss();

    }
}
