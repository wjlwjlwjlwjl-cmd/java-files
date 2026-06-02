package JavaEE.Net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer{
    private static DatagramSocket sock = null;

    private static void start() throws IOException{
        getSocket(8080);
        while(true){
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            sock.receive(requestPacket);

            String request = new String(requestPacket.getData(), 0, requestPacket.getLength());
            System.out.printf("[Server get request]: %s\n", request);

            String response = process(request);
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length, requestPacket.getAddress(), requestPacket.getPort());
            sock.send(responsePacket);

            System.out.println("[Server send response]");
        }
    }

    private static String process(String request){
        String ret = "[Server's response]: " + request;
        return ret;
    }

    private static void getSocket(int port)throws IOException{
        sock = new DatagramSocket(port);
    }

    public static void main (String[] args){
        try{
            start();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}