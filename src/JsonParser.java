import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
    // private static String outLog;
    protected static long port;
    protected static String customer = null;
    protected static String depositId = null;
    protected static BigDecimal initialBalance = null;
    protected static BigDecimal upperBound = null;
    private static final int MAX_OF_DEPOSITS = 2;
    public JsonParser(){
    }
    //    public  JsonParser()
    protected long getPort()
    {
        return port;
    }
    public static Deposit[] Parser() {
        Deposit[] deposits = new Deposit[MAX_OF_DEPOSITS];

        try {
            // parse the json file
            String fileName = "core.json";
            FileReader reader = new FileReader(fileName);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            port = (Long) jsonObject.get("port");
            System.out.println("The port is: " + port);

            JSONArray deposit= (JSONArray) jsonObject.get("deposit");
//            for(int i=0; i<deposit.size(); i++){
//                System.out.println("The " + i + " element of the array: " + deposit.get(i));
//            }
            int counter = 0 ;
            Iterator i = deposit.iterator();
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                customer = (String) innerObj.get("customer");
                depositId = (String) innerObj.get("id");
                initialBalance = new BigDecimal((String) innerObj.get("initialBalance"));
                upperBound = new BigDecimal((String) innerObj.get("upperBound"));
                deposits[counter] = new Deposit(customer,  depositId,  initialBalance,  upperBound);
                counter ++;
            }
            String outLog = (String) jsonObject.get("outLog");
        } catch (IOException ex)  {
            ex.printStackTrace();
        }catch (ParseException ex){            ex.printStackTrace();
        }
        catch ( NullPointerException ex){            ex.printStackTrace();
        }
//        Deposit deposit = new Deposit(customer,  depositId,  initialBalance,  upperBound);
        return deposits;
    }
}
