package com.dev_ver.wisebank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Database.Transaction;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionViewHolder> {
    private ArrayList<Transaction> transList;

    public TransactionListAdapter(ArrayList<Transaction> transactionArrayList)
    {
      this.transList=transactionArrayList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View transactview=inflater.inflate(R.layout.transaction,parent,false);
        return new TransactionViewHolder(transactview);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction tempo=transList.get(position);
        holder.credit.setText("Credited:"+tempo.getCredit());
        holder.debit.setText("Debited:"+tempo.getDebit());
        holder.transactDetail.setText("Ref ID:"+String.valueOf(tempo.getTransactionID()));
        holder.transactAmount.setText("Amount:"+String.valueOf(tempo.getTransactAmount()));

    }

    @Override
    public int getItemCount() {
        return transList.size();
    }
}

class TransactionViewHolder extends RecyclerView.ViewHolder{
    TextView debit;
    TextView credit;
    TextView transactDetail;
    TextView transactAmount;

  public TransactionViewHolder(@NonNull View itemView)
  {
      super(itemView);
      debit=itemView.findViewById(R.id.debited);
      credit=itemView.findViewById(R.id.credited);
      transactDetail=itemView.findViewById(R.id.transactId);
      transactAmount=itemView.findViewById(R.id.transamount);
  }


}