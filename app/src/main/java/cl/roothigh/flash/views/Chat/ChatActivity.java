package cl.roothigh.flash.views.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import cl.roothigh.flash.R;
import cl.roothigh.flash.models.Chat;
import cl.roothigh.flash.views.main.chatList.ChatListFragment;

public class ChatActivity extends AppCompatActivity implements  CreateMessageCallback {
    private EditText userInput;
    private ImageButton sendBtn;
    private String chatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

         chatId = getIntent().getStringExtra(ChatListFragment.CHAT_ID);
        Toast.makeText(this,chatId,Toast.LENGTH_SHORT).show();
        String otherName = getIntent().getStringExtra(ChatListFragment.OTHER_USER);
        getSupportActionBar().setTitle(otherName);

        userInput = (EditText) findViewById(R.id.messageTv);
        sendBtn = (ImageButton) findViewById(R.id.sendBtn);
        setSendBtn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new UpdateChat(chatId).send();
    }

    private void setSendBtn(){
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = userInput.getText().toString();
                new CreateMessage(chatId,message, ChatActivity.this).send();

            }
        });
    }

    @Override
    public void clear() {
        userInput.setText("");
        if (userInput.getError() != null) {
            userInput.setError(null);
        }
    }

    @Override
    public void error(String error) {
        userInput.setError(error);
    }
}
