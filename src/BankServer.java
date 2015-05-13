
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {

    private static ObjectOutputStream serverOutput;
    private static ObjectInputStream serverInput;
    private ServerSocket server;
    private static Socket connection;
    private static long port;
    private static Deposit[] deposit;
    private static DepositChange[] depositChange;
    private static Transaction[] transaction;
    private static final int MAX_OF_TRANSACTION = 2;
    private static final int MAX_OF_DEPOSIT = 2;


    public BankServer() {
        JsonParser jsonParse = new JsonParser();
        deposit = new Deposit[MAX_OF_DEPOSIT];
        deposit = jsonParse.Parser();
        port = jsonParse.getPort();
        transaction = new Transaction[MAX_OF_TRANSACTION];
        depositChange = new DepositChange[MAX_OF_TRANSACTION];

    }

    public void runServer() throws Exception {
        try {
            server = new ServerSocket((int) port);

            while (true) {
                try {
                    waitForConnection();
                    receiveData();
                    processRequest();
                } catch (EOFException eof) {
                    //System.out.println("\nServer terminated connection");
                } finally {
                    closeConnection();
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void waitForConnection() throws Exception {
        System.out.println("Waite For Connection...\n");
        connection = server.accept();
        System.out.println("Connection " + "received from: " + server.toString());
    }

    private static void receiveData() throws IOException {
        serverInput = new
                ObjectInputStream(connection.getInputStream());
        try {
            transaction = (Transaction[]) serverInput.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("transaction " + transaction[0].getType());
    }

    private static void processRequest() throws IOException {
        BigDecimal newBalance;
        int counter = 0;
        for (int i = 0; i < transaction.length; i++)
            for (int j = 0; j < deposit.length; j++) {
                if (transaction[i].getDepositId() == deposit[j].getDepositId()) {
                    Validation validation = new Validation(deposit[j], transaction[i]);
                    if (validation.validationOfValues() && validation.validationOfBound()) {
                        counter++;
                        newBalance = transaction[i].doTransaction(transaction[i].getType(), transaction[i].getAmount());
                        depositChange[counter] = new DepositChange(transaction[i].getDepositId(), newBalance);

                    } else
                        System.out.println("Validation for " + transaction[i].getId() + "is failed!");
                }
            }
        sendData(depositChange);
    }

    private static void sendData(DepositChange[] depositChange) throws IOException {

        serverOutput = new
                ObjectOutputStream(connection.getOutputStream());
        serverOutput.writeObject(depositChange);


    }

    private static void closeConnection() {
        System.out.println("\nTerminating connection\n");
        try {
            serverOutput.close();
            serverInput.close();
            connection.close();
        } catch (IOException exe) {
            exe.printStackTrace();
        }
    }

    //    private static void sendData(String messg)
//    {
//        try{
//            output.writeObject("Server >> " + messg);
//            output.flush();
//        }
//        catch(IOException exe)
//        {
//        }
//    }
    public static void main(String[] args) throws Exception {
        BankServer bankServer = new BankServer();
        bankServer.runServer();
    }
}