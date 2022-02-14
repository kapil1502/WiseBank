package com.dev_ver.wisebank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import Database.DatabaseManagement;
import Database.Transaction;


public class ViewAllCustomers extends AppCompatActivity implements UserViewHolder.UserClicked {


    public void showAllCustomers()
    {

        ArrayList<User> usersArray=new ArrayList<>();
        DatabaseManagement database=new DatabaseManagement(this);
        usersArray=database.getAllUsers();
        UsersListAdapter userAdapter=new UsersListAdapter(usersArray,this);
        RecyclerView usersList=findViewById(R.id.usersList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        usersList.setLayoutManager(linearLayoutManager);
        usersList.setAdapter(userAdapter);
    }

    public void showAllTransactions()
    {
        ArrayList<Transaction> transactionArray=new ArrayList<>();
        DatabaseManagement database=new DatabaseManagement(this);
        transactionArray=database.getAllTransactions();
        TransactionListAdapter transactionAdapter=new TransactionListAdapter(transactionArray);
        RecyclerView usersList=findViewById(R.id.usersList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        usersList.setLayoutManager(linearLayoutManager);
        usersList.setAdapter(transactionAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_customers);
        int check=getIntent().getIntExtra("check",1);
        if(check==1)
        {
            showAllCustomers();
        }
           else
        {
            showAllTransactions();
        }
    }

    @Override
    public void onUserClicked(int position) {
        Intent userProfile=new Intent(getApplicationContext(),UsersProfile.class);
        userProfile.putExtra("position clicked",position);
        startActivity(userProfile);
    }
}