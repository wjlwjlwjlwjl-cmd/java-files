package JavaEE.Net;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.InetAddress;

public class TCPClient{
    public static void main(String[] args){
        try{
            run();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    private static void run() throws IOException{
        String serverIP = "127.0.0.1";
        Socket serverSocket = new Socket(InetAddress.getByName(serverIP), 8081);
        try(InputStream in = serverSocket.getInputStream(); OutputStream out = serverSocket.getOutputStream()){
            PrintWriter writer = new PrintWriter(out);
            Scanner scanner = new Scanner(in);
            int cnt = 10;
            while(cnt-- != 0){
                String msg = "hello";
                writer.println(msg);
                writer.flush();
                String resp = scanner.nextLine();
                System.out.println(resp);
                Thread.sleep(1000);
            }
            scanner.close();
        }        
        catch (Exception e){
            System.out.println(e);
        }
        serverSocket.close();
    }
}