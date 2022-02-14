package Database;

public class Transaction {
    private String credit;
    private String debit;
    private int transactionID;
    private int transactAmount;

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getTransactAmount() {
        return transactAmount;
    }

    public void setTransactAmount(int transactAmount) {
        this.transactAmount = transactAmount;
    }
}
