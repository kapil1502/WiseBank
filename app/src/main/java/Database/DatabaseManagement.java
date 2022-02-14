package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev_ver.wisebank.User;

import java.util.ArrayList;

public class DatabaseManagement extends SQLiteOpenHelper {
    public DatabaseManagement(Context context) {
        super(context,"Banking_DB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable="CREATE TABLE users_Table(accno INTEGER PRIMARY KEY,Name TEXT,Email TEXT,CurrentBal INTEGER)";
        String createTransactionsTable="CREATE TABLE transactions_Table(transId INTEGER PRIMARY KEY,credit TEXT,debit TEXT,transDetail INTEGER)";
        db.execSQL(createUserTable);
        db.execSQL(createTransactionsTable);
       }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUserDetails(User user)
    {SQLiteDatabase usersDb=this.getWritableDatabase();
        ContentValues userDetail= new ContentValues();
        userDetail.put("accno",user.getAccNo());
        userDetail.put("Name",user.getName());
        userDetail.put("Email",user.getEmail());
        userDetail.put("CurrentBal",user.getBalance());
        usersDb.insert("users_Table",null,userDetail);
        usersDb.close();
    }

    public void addTransaction(Transaction trans)
    {
        SQLiteDatabase transDb=this.getWritableDatabase();
        ContentValues transDetail= new ContentValues();
        transDetail.put("transId",trans.getTransactionID());
        transDetail.put("credit",trans.getCredit());
        transDetail.put("debit",trans.getDebit());
        transDetail.put("transDetail",trans.getTransactAmount());
        transDb.insert("transactions_Table",null,transDetail);
        transDb.close();
    }

    public ArrayList<User> getAllUsers()
    { SQLiteDatabase usersDb=this.getReadableDatabase();  //Extracts the database in the readable form for Us.
        String selectAll="SELECT * FROM users_Table";
        ArrayList<User> usersList=new ArrayList<>();
        Cursor c=usersDb.rawQuery(selectAll,null); //It acts as a pointer which point towards the Database.
        // c.moveToFirst() Moves the pointer to the first Record.

        // Note : When cursor reaches the end of the database towards the null.
        if(c.moveToFirst()) {
            do {
                User user;
                user = new User();
                user.setAccNo(Integer.parseInt(c.getString(0)));
                user.setName(c.getString(1)); // Returns the String on the X column of cursor.
                user.setEmail(c.getString(2));
                user.setBalance(Integer.parseInt(c.getString(3)));
                usersList.add(user);
                //  c.moveToNext() Then points towards the next records in our Database.
            }
            while (c.moveToNext());
        }
        c.close(); //Cleans or delete the cursor and frees up the Memory space occupied.
        usersDb.close();
        return usersList;
    }

    public ArrayList<Transaction> getAllTransactions()
    {
        SQLiteDatabase transDb=this.getReadableDatabase();
        String selectAll="SELECT * FROM transactions_Table";
        ArrayList<Transaction> transactions=new ArrayList<>();
        Cursor c=transDb.rawQuery(selectAll,null);
        if(c.moveToFirst()) {
            do {
                Transaction transaction;
                transaction = new Transaction();
                transaction.setTransactionID(Integer.parseInt(c.getString(0)));
                transaction.setCredit(c.getString(1)); // Returns the String on the X column of cursor.
                transaction.setDebit(c.getString(2));
                transaction.setTransactAmount(Integer.parseInt(c.getString(3)));
                transactions.add(transaction);
            } while (c.moveToNext());
        }
        c.close();
        transDb.close();
        return transactions;
    }

    public void sendMoney(int senderAccNo,int recieverAccNo,int amount,int senderBalance,int recieverBalance)
    { SQLiteDatabase myWriteDb=this.getWritableDatabase();
      String query1="UPDATE users_Table SET CurrentBal="+(senderBalance-amount)+" WHERE accno= "+senderAccNo;
      myWriteDb.execSQL(query1);
      String query2="UPDATE users_Table SET CurrentBal="+(recieverBalance+amount)+" WHERE accno= "+recieverAccNo;
      myWriteDb.execSQL(query2);
      myWriteDb.close();
    }

}
