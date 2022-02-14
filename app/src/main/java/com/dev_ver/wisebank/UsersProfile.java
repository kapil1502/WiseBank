package com.dev_ver.wisebank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DatabaseManagement;

public class UsersProfile extends AppCompatActivity {

    String selectedUserName;
    int senderAccNo;
    int senderBalance;

    public void transfer(View view)
    {   EditText account=findViewById(R.id.editTextPhone);
        int accountID=Integer.parseInt(account.getText().toString());
        Intent sendMoney=new Intent(getApplicationContext(),SendMoney.class);
        int position=getIntent().getIntExtra("position clicked",0);
        sendMoney.putExtra("sender accno",senderAccNo);
        EditText nameEdit=findViewById(R.id.editTextTextPersonName);
        String recieverName=nameEdit.getText().toString();
        sendMoney.putExtra("reciever name",recieverName);
        sendMoney.putExtra("accountNo",accountID);
        sendMoney.putExtra("senderName",selectedUserName);
        sendMoney.putExtra("sender balance",senderBalance);
        startActivity(sendMoney);
       /* DatabaseManagement db=new DatabaseManagement(this);
       try {
           db.sendMoney(senderAccNo,accountID,2000,senderBalance,12000);
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
        ArrayList<User> updated=database.getAllUsers();
        RecyclerView recyclerView=findViewById(R.id.usersList);
        UsersListAdapter adapter=(UsersListAdapter) recyclerView.getAdapter();
        adapter.updateList(updated);*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);
        DatabaseManagement database=new DatabaseManagement(this);
        ArrayList<User> userList=database.getAllUsers();
        User clicked=userList.get(getIntent().getIntExtra("position clicked",0));
        TextView name=findViewById(R.id.textView);
        selectedUserName=clicked.getName();
        name.setText("A/C Holder Name -> "+selectedUserName);
        TextView email=findViewById(R.id.textView2);
        email.setText("Email ID -> "+ clicked.getEmail());
        TextView balance=findViewById(R.id.textView3);
        senderBalance=clicked.getBalance();
        balance.setText("A/C Balance INR -> Rs "+senderBalance);
        TextView accno=findViewById(R.id.textView4);
        senderAccNo=clicked.getAccNo();
        accno.setText("A/C Number -> "+senderAccNo);
    }
}