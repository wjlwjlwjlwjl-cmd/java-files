import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;

public class TCPServer{
    private static ServerSocket serverSocket = null;
    private static int port = 8081;
    public static void main(String[] args){
        try{
            run();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    private static void run() throws IOException{
        serverSocket = new ServerSocket(port);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        while(true){
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(()->{
                try{
                    process(clientSocket);
                    clientSocket.close(); //关闭监听端口获得的线程
                }
                catch(IOException e){
                    System.out.println(e);
                }
            });
        }
    }

    public static void process(Socket clientSocket) throws IOException{
        try(InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()){
            Scanner scanner = new Scanner(in);
            PrintWriter writer = new PrintWriter(out);
            while(true){
                if(!scanner.hasNextLine()){
                    break;
                }
                String request = scanner.nextLine();
                String response = "[Server's response]: " + request;
                writer.println(response);
                writer.flush();
                System.out.printf("Debug: %s\n", response);
            }
            scanner.close();
        }        
        catch (Exception e){
            System.out.println(e);
        }
    }
}
