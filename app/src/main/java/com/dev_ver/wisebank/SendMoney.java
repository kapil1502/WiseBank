package com.dev_ver.wisebank;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Database.DatabaseManagement;
import Database.Transaction;

public class SendMoney extends AppCompatActivity {

    int senderAccNo;
    int recieverAccNo;
    DatabaseManagement newDb;

    public void confirmTransaction(View view) {
            Toast.makeText(this, "Payment Processing...", Toast.LENGTH_LONG).show();
            int senderBalance = getIntent().getIntExtra("sender balance", 0);
            int recieveBalance = 0;
            EditText amountGets = findViewById(R.id.amount);
            int amount = Integer.parseInt(amountGets.getText().toString());

            Cursor c = newDb.getReadableDatabase().rawQuery(" SELECT * FROM users_Table WHERE accno=" + String.valueOf(recieverAccNo), null);
            c.moveToFirst();
            recieveBalance = Integer.parseInt(c.getString(3));
            c.close();

            if (senderBalance >= amount) {
                newDb.sendMoney(senderAccNo, recieverAccNo, amount, senderBalance, recieveBalance);
                String transactionID=generateID();
                Transaction transact = new Transaction();
                transact.setTransactionID(Integer.parseInt(transactionID));
                transact.setCredit(getIntent().getStringExtra("reciever name"));
                transact.setDebit(getIntent().getStringExtra("senderName"));
                transact.setTransactAmount(amount);
                newDb.addTransaction(transact);
                Toast.makeText(this, "Transaction Successfull with unique id "+transactionID, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Available balance is less than you want to send.", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        String recieverName = getIntent().getStringExtra("reciever name");
        String senderName = getIntent().getStringExtra("senderName");
        senderAccNo = getIntent().getIntExtra("sender accno", 0);
        recieverAccNo = getIntent().getIntExtra("accountNo", 0);
        newDb = new DatabaseManagement(this);
        TextView sendName = findViewById(R.id.senderName);
        sendName.setText(senderName);
        TextView recieveName = findViewById(R.id.recieverName);
        recieveName.setText(recieverName);
        TextView sendAccNo = findViewById(R.id.senderAccountNo);
        sendAccNo.setText("Account no:" + senderAccNo);
        TextView recieveAccNo = findViewById(R.id.recieverAccountNo);
        recieveAccNo.setText("Account no:" + recieverAccNo);
    }
    public static String generateID(){
    Calendar myCalendar = new GregorianCalendar();
    String transactID = myCalendar.get(Calendar.MILLISECOND) +myCalendar.get(Calendar.MINUTE) + myCalendar.get(Calendar.DAY_OF_MONTH) + String.valueOf(myCalendar.get(Calendar.HOUR)) + myCalendar.get(Calendar.MONTH) + myCalendar.get(Calendar.SECOND);
       return transactID;
    }
}