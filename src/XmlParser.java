import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class XmlParser {
    private static int port = 0;
    private static String ip = null;
    private static String terminalId = null;
    private static String terminalType = null;
    private static String  path = null;
    private static final int MAX_OF_TRANSACTION = 5;
    public XmlParser() {
    }
    public int getport() {
        return port;
    }

    public String getIp() {
        return ip;
    }
    public String getTerminalId()
    {return  terminalId;}
    public static Transaction[] parser() throws ParserConfigurationException,
            SAXException, IOException {
        Transaction[] transaction = new Transaction[MAX_OF_TRANSACTION];
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("terminal.xml"));

        Element terminalNode = document.getDocumentElement();
        NodeList nodeList = terminalNode.getChildNodes();
        if (terminalNode.getNodeType() == Node.ELEMENT_NODE) {
            terminalId = terminalNode.getAttributes().getNamedItem("id").getNodeValue();
            terminalType = terminalNode.getAttributes().getNamedItem("type").getNodeValue();
        }
        Node serverNode = nodeList.item(1);
        if (serverNode.getNodeType() == Node.ELEMENT_NODE) {
            ip = serverNode.getAttributes().getNamedItem("ip").getNodeValue();
            port = Integer.parseInt(serverNode.getAttributes().getNamedItem("port").getNodeValue());
        }
        Node outLogNode = nodeList.item(3);
        if (outLogNode.getNodeType() == Node.ELEMENT_NODE) {
            path = outLogNode.getAttributes().getNamedItem("path").getNodeValue();
        }
        System.out.println("port " + port + "ip " + ip + "id " + nodeList.getLength());
        NodeList nodeLst1 = document.getElementsByTagName("transaction");

        for (int i = 0; i < nodeLst1.getLength(); i++) {
            serverNode = nodeLst1.item(i);
            if (serverNode.getNodeType() == Node.ELEMENT_NODE) {
                int id = Integer.parseInt(serverNode.getAttributes().getNamedItem("id").getNodeValue());
                String type = serverNode.getAttributes().getNamedItem("type").getNodeValue();
                BigDecimal amount = new BigDecimal( serverNode.getAttributes().getNamedItem("amount").getNodeValue());
                String deposit = serverNode.getAttributes().getNamedItem("deposit").getNodeValue();
                //System.out.println("id " + id + "type " + type + "amount " + amount);
                transaction[i] = new Transaction(id,type,amount,deposit);

            }
        }
        return transaction;
    }

}
