package com.dev_ver.wisebank;

public class User {
    private int accNo;
    private String Name;
    private String email;
    private int balance;
    public User()
    {

    }
    public User(String a,String b,int c)
    {
        this.Name=a;
        this.email=b;
        this.balance=c;
    }
    public User(int a,String b,String c,int d)
    {   this.accNo=a;
        this.Name=b;
        this.email=c;
        this.balance=d;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int id) {
        accNo = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
