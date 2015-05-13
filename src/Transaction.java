import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Transaction implements Serializable {
    private int id;
    private String type;
    private BigDecimal amount;
    private String depositId;

    public Transaction(){}

    public Transaction(int id, String type, BigDecimal amount, String depositId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.depositId = depositId;
    }

    public String getType(){
        return this.type;
    }
    public BigDecimal getAmount(){return this.amount;}
    public int getId() {return  this.id;}
    public String getDepositId() {return  this.depositId;}

    public BigDecimal doTransaction(String type,BigDecimal amount)
    {
        Deposit deposit = new Deposit();
        BigDecimal balance = deposit.getInitialBalance();

        if (type.equals("deposit")) {
            balance = balance.add(amount);
        }
        if(type.equals("withdraw")){
            balance = balance.subtract(amount);
        }
        return balance;
    }
}
