import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.io.Reader;
import java.io.FileReader;
import java.io.Writer;

public class IO{
    public static void main(String[] args) throws InterruptedException{
        new TestRW().testR();
    }
}

class TestFile{
    public void test(){
        File file = new File("/home/diinki/Git/java-files/JavaEE/IO/testdir1/subdir1/subdir2");
        System.out.println(file.getName());
        System.out.println(file.getParent());
        file.deleteOnExit(); //删除文件

        System.out.println(Arrays.toString(file.list()));
        System.out.println(Arrays.toString(file.listFiles()));

        file.mkdir();
        file.mkdirs();
    }
}

//字节流：以字节为单位读取
class TestInputStream{
    public void test() throws IOException{
        try(InputStream in = new FileInputStream("./test.txt")){
            int data = 0;
            while(true){
                data = in.read();
                if(data == -1){
                    break;
                }
                System.out.printf("0x%x\n", data);
            }
        }
        System.out.println("=================");
        try(InputStream in2 = new FileInputStream("./test2.txt")){
            byte[] buf = new byte[3];
            while(true){
                int n = in2.read(buf);
                if(n == -1){
                    break;
                }
                for(int i = 0; i < n; i++){
                    System.out.printf("0x%x\n", buf[i]);
                }
                System.out.println("=================");
            }
        }
    }
}

class TestRW{
    public void testR(){
        try(Reader reader = new FileReader("./test2.txt")){
            char[] buf = new char[3];
            while(true){
                int n = reader.read(buf);
                if(n == -1){
                    break;
                }
                System.out.println(buf);
                System.out.println("=============");
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}