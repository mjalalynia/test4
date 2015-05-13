import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class DepositChange implements Serializable {
    private BigDecimal newBalance;
    private String depositId;

    public DepositChange(String depositId, BigDecimal newBalance)
    {
        this.depositId = depositId;
        this.newBalance = newBalance;
    }
    public DepositChange(){};
    public String getDepositId() {return depositId;}
    public BigDecimal getNewBalance() {return newBalance;}
    public void setDepositId(String id) {depositId = id;}
    public void setNewBalance(BigDecimal nb){newBalance = nb;}
}
