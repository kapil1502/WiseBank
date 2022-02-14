package com.dev_ver.wisebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    

    public void showAllUsers(View view)
    {
        Intent viewCustomers=new Intent(getApplicationContext(),ViewAllCustomers.class);
        viewCustomers.putExtra("check",1);
        startActivity(viewCustomers);
    }

    public void showAllTransactions(View view)
    { Intent viewTransaction=new Intent(getApplicationContext(),ViewAllCustomers.class);
        viewTransaction.putExtra("check",2);
        startActivity(viewTransaction);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}