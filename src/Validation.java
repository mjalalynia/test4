import java.math.BigDecimal;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Validation {
    BigDecimal initBalance ;
    BigDecimal upperBound;
    Deposit deposit;
    Transaction transaction;
    String type;
    BigDecimal amount;
    public Validation(Deposit deposit,Transaction transaction){
        //deposit = new Deposit();
        initBalance = deposit.getInitialBalance();
        upperBound = deposit.getUpperBound();
        //transaction = new Transaction();
        type = transaction.getType();
        amount = transaction.getAmount();
    }
    //    public boolean validationOfBalance()
//    {
//
//    }
    public boolean validationOfValues(){
        if(initBalance.doubleValue()>0 && upperBound.doubleValue()>0)
            return  true;
        else
            return  false;
    }
    public boolean validationOfBound(){
        BigDecimal balance = transaction.doTransaction(type, amount);

        if (balance.compareTo(upperBound)>0 | balance.doubleValue()<0)
            return false;
        else
            return  true;
    }
}
