package com.dev_ver.wisebank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsersListAdapter extends RecyclerView.Adapter<UserViewHolder>{
    ArrayList<User> users_Array;
    UserViewHolder.UserClicked userClickedListener;
    public UsersListAdapter(ArrayList<User> users, UserViewHolder.UserClicked userClickedListen)
    {
        users_Array=users;
        userClickedListener=userClickedListen;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater viewInflator= LayoutInflater.from(parent.getContext());
        View user_View=viewInflator.inflate(R.layout.user,parent,false);

        return new UserViewHolder(user_View,userClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User new_User=users_Array.get(position);
        holder.name.setText(new_User.getName());
        holder.balanceamount.setText("Rs "+new_User.getBalance());
        holder.accno.setText("A/C no:"+new_User.getAccNo());
    }

    public void updateList(ArrayList<User> updatedList)
    {
        users_Array.clear();
        users_Array.addAll(updatedList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users_Array.size();
    }
}

 class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView name;
    TextView balanceamount;
    TextView accno;
    UserClicked userClicked;

    public UserViewHolder(@NonNull View itemView,UserClicked userClickedParam) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        balanceamount = itemView.findViewById(R.id.balanceamount);
        accno = itemView.findViewById(R.id.accno);
        this.userClicked=userClickedParam;
        itemView.setOnClickListener(this);
    }

     @Override
     public void onClick(View v) {
       userClicked.onUserClicked(getAdapterPosition());
     }

     public interface UserClicked{
         public void onUserClicked(int position);
     }

}

