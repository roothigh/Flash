package cl.roothigh.flash.views.Chat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import cl.roothigh.flash.R;
import cl.roothigh.flash.data.FirebaseRef;
import cl.roothigh.flash.data.UserData;
import cl.roothigh.flash.models.Message;
import cl.roothigh.flash.views.main.chatList.ChatListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MesaggesFragment extends Fragment {


    public MesaggesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mesagges, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = (RecyclerView) view;
        String chatId= getActivity().getIntent().getStringExtra(ChatListFragment.CHAT_ID);

        DatabaseReference reference = new FirebaseRef().mesages().child(chatId);

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Message, MessageHolder>(
                Message.class, R.layout.list_item_message, MessageHolder.class, reference
        ) {
            @Override
            protected void populateViewHolder(MessageHolder viewHolder, Message model, int position) {
                viewHolder.setMsg(model);

            }
        };
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

    }

    public static class MessageHolder extends RecyclerView.ViewHolder {

        private TextView current, other;

        public  MessageHolder(View itemView){
            super(itemView);
            current = (TextView) itemView.findViewById(R.id.rightTv);
            other = (TextView) itemView.findViewById(R.id.leftTv);
        }
        public  void setMsg (Message message){
            String owner = message.owner;
            String currentemail = new UserData().email();
            String msg = message.message;

            if ( owner.equals(currentemail)){
                current.setVisibility(View.VISIBLE);
                other.setVisibility(View.GONE);
                current.setText(msg);

            }else {
                current.setVisibility(View.GONE);
                other.setVisibility(View.VISIBLE);
                other.setText(msg);

            }

        }

    }
}
