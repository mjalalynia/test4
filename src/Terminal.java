//import java.net.*;
//import java.io.*;
//class MyClient{
//    public static void main(String args[])throws Exception{
//        Socket s=new Socket("localhost",3333);
//        DataInputStream din=new DataInputStream(s.getInputStream());
//        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//
//        String str="",str2="";
//        while(!str.equals("stop")){
//            str=br.readLine();
//            dout.writeUTF(str);
//            dout.flush();
//            str2=din.readUTF();
//            System.out.println("Server says: "+str2);
//        }
//
//        dout.close();
//        s.close();
//    }}
import org.xml.sax.SAXException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionListener;


public class Terminal extends Thread{

    private  ObjectOutputStream output;
    private  ObjectInputStream input;
    private  Socket client;
    private  int serverPort;
    private  String serverIp;
    XmlParser xmlParser;
    Transaction[] transaction;
    private static final int MAX_OF_TRANSACTION = 2;



    public Terminal() throws IOException, SAXException, ParserConfigurationException {
        xmlParser = new XmlParser();
        transaction = new Transaction[MAX_OF_TRANSACTION];
        transaction = xmlParser.parser();
        serverPort = xmlParser.getport();
        serverIp = xmlParser.getIp();
    }

    public void runClient() throws Exception
    {
        try{
            System.out.print("serverIp: "+ serverIp + "port " + serverPort);
            connectToServer();
            getStreams();
            //processConnection();
        }
        catch(EOFException exe)
        {
            //System.out.println("\nClient terminated connection");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //closeConnection();
        }
    }

    private void connectToServer() throws IOException
    {
        client=new Socket(serverIp, serverPort);
        //System.out.println("Connected to: "+client.getInetAddress().getHostName());
    }
    private void getStreams()throws IOException
    {
        //OutputStream ss ;
//        ss = (OutputStream) xmlParser.getTerminalId();
//        FileOutputStream fos = new FileOutputStream("t.txt");
//        output=new ObjectOutputStream(fos);
//        output.write(1212121);
//        output.flush();
//        input=new ObjectInputStream(client.getInputStream());
        ObjectOutputStream clientOutputStream = new
                ObjectOutputStream(client.getOutputStream());
//        ObjectInputStream clientInputStream = new
//                ObjectInputStream(client.getInputStream());

        clientOutputStream.writeObject(transaction);

        // joe= (Employee)clientInputStream.readObject();
//
        System.out.println("transaction= "
                + transaction[0].getType());
//        System.out.println("employeeName= "
//                + transaction .getEmployeeName());

        clientOutputStream.close();
//        clientInputStream.close();


//        System.out.println("\nGot I/O streams\n");
    }
//    private static void processConnection()throws IOException
//    {
//        String message="Connection Successfully";
//        sendData(message);
//        do{
//            try {
//                message = (String) input.readObject();
////                System.out.println("\n"+message);
//            }catch(ClassNotFoundException e)
//            {
////                System.out.println("\n Unknown object type recived");
//            }
//        }while(message.equals("Server >> TERMINATE"));
//
//    }
//    private static void closeConnection()
//    {
////        System.out.println("\nTerminating connection\n");
//        try
//        {
//            output.close();
//            input.close();
//            client.close();
//        }
//        catch(IOException exe)
//        {
//            exe.printStackTrace();
//        }
//    }
//    private static void sendData(Transaction transaction)
//    {
//
//        try{
//            output.writeObject("Client >> "+messg);
//            output.flush();
////            System.out.println("\nClient >> "+messg);
//        }
//        catch(IOException exe)
//        {
////            System.out.println("\nError writeing object");
//        }
//    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Terminal terminal =new Terminal();
        try {
            terminal.runClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}