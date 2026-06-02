package JavaEE.Net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient{
    private static DatagramSocket sock = null;
    private static String serverIP = "127.0.0.1";
    private static int serverPort = 8080;
    public static void main(String[] args){
        try{
            sock = new DatagramSocket();
            String message = "hello";
            DatagramPacket requestPacket = new DatagramPacket(message.getBytes(), 0, message.length(), 
                InetAddress.getByName(serverIP), serverPort);
            sock.send(requestPacket);

            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            sock.receive(responsePacket);
            String response = new String(responsePacket.getData(), 0, responsePacket.getData().length);
            System.out.println(response);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}