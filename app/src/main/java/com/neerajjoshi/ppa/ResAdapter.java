package com.neerajjoshi.ppa;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_ADD_BUTTON = 1;

//     List<Pair<String,String>> webPasslist;

     ArrayList<WebInstance> webpassList;

    //private List<String> passlist;


    public ResAdapter(ArrayList webpaslist) {
        this.webpassList = webpaslist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_button, parent, false);
            return new AddButtonViewHolder(view);
        }


       // return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            WebInstance instance = webpassList.get(position);

            // Bind data to item view holder as before
            itemViewHolder.webnameTextView.setText(instance.getWebsite_name());
            // ...
        } else if (holder instanceof AddButtonViewHolder) {
            AddButtonViewHolder addButtonViewHolder = (AddButtonViewHolder) holder;
            // Handle the "Add" button view holder
            // Set up the click listener for the "+" button
            addButtonViewHolder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Implement the logic to add a new item here
                    // For example, show a dialog to enter a new fruit
                    v.getContext().startActivity(new Intent(v.getContext(),FormFill.class));
                }
            });
        }


        //Pair<String,String> pair = webPasslist.get(position);


    }

    @Override
    public int getItemCount() {
        return webpassList.size() +1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == webpassList.size()) ? VIEW_TYPE_ADD_BUTTON : VIEW_TYPE_ITEM;
    }


    class AddButtonViewHolder extends RecyclerView.ViewHolder {
        Button addButton;

        AddButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            addButton = itemView.findViewById(R.id.button_add);

        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView webnameTextView;
        CardView cardView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            webnameTextView = itemView.findViewById(R.id.text_webName);
            cardView =itemView.findViewById(R.id.card_view);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){

                        Intent intent =new Intent(view.getContext(),Card_view.class);
                        intent.putExtra("webname",webpassList.get(position).getWebsite_name());
                        intent.putExtra("username",webpassList.get(position).getUser_name());
                        intent.putExtra("passwrd",webpassList.get(position).getPass_word());
                        intent.putExtra("id",webpassList.get(position).getId());


                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);
                        //Toast.makeText(view.getContext(),"Clicked On "+webPasslist.get(position).first,Toast.LENGTH_SHORT).show();


                    }
                }
            });

        }

    }


}
