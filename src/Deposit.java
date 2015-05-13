import java.math.BigDecimal;

public class Deposit {

    private  String customer;
    private  String depositId;
    private  BigDecimal initialBalance;
    private  BigDecimal upperBound;
    //    private static String outLog;
    public  Deposit( String customer, String depositId, BigDecimal initialBalance, BigDecimal upperBound){
        this.customer = customer;
        this.depositId= depositId;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
    }
    //    public Deposit(String depositId, BigDecimal initialBalance){
//        this.depositId= depositId;
//        this.initialBalance = initialBalance;
//    }
    public Deposit(){}
    public String getCustomer()
    {
        return customer;
    }
    public String getDepositId()
    {
        return depositId;
    }
    public BigDecimal getInitialBalance()

    {
        return initialBalance;
    }
    public BigDecimal getUpperBound()
    {
        return upperBound;
    }
}
