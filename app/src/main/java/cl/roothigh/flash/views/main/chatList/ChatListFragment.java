package cl.roothigh.flash.views.main.chatList;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import cl.roothigh.flash.R;
import cl.roothigh.flash.data.FirebaseRef;
import cl.roothigh.flash.data.UserData;
import cl.roothigh.flash.models.Chat;
import cl.roothigh.flash.views.Chat.ChatActivity;
import cl.roothigh.flash.views.main.drawer.PhotoData;

import static cl.roothigh.flash.R.id.start;
import static cl.roothigh.flash.R.id.textView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {
    public static  final String CHAT_ID = "CHAT_ID";
    public static  final String OTHER_USER = "OTHER_USER";


    public ChatListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = (RecyclerView) view;
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseReference reference = new FirebaseRef().chatlist().child(new UserData().user().getUid());
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Chat, ChatHolder>(Chat.class, R.layout.list_item_chat, ChatHolder.class, reference) {
            @Override
            protected void populateViewHolder(ChatHolder viewHolder, final Chat model, int position) {
                String currentPhoto = new PhotoData(getContext()).getUrl();
                String photo =  (currentPhoto.equals(model.receiver_photo)) ? model.creator_photo : model.receiver_photo;

                viewHolder.setPhoto(photo);


                String currentEmail = new  UserData().email();
                final String name = (currentEmail.equals(model.receiver)) ? model.receiver : model.receiver;
                viewHolder.setName(name);
                viewHolder.setNotification(model.notification);
                viewHolder.nameTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ChatActivity.class);
                        intent.putExtra(CHAT_ID, model.chat_id);
                        intent.putExtra(OTHER_USER, name);
                        startActivity(intent);
                    }
                });

            }
        };
        recycler.setAdapter(adapter);

    }
    public static class ChatHolder extends RecyclerView.ViewHolder {
        private TextView nameTV;

        public ChatHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.reveiverTv);
        }
        public void  setPhoto(String photo){
            ImageView imageView = (ImageView) itemView.findViewById(R.id.receiverIv);
            Picasso.with(imageView.getContext()).load(photo).into(imageView);


        }
        public void  setName(String name){

            nameTV.setText(name);

        }
        public void setNotification (boolean notification){
            View view = itemView.findViewById(R.id.notificationV);
            if (notification){
                view.setVisibility(View.VISIBLE);
            }else{
                view.setVisibility(View.GONE);

            }

        }

    }

}
