import java.io.File;
import java.util.Scanner;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileSearch{
    public static void main(String[] args){
        new SearchName().run();        
    }
}

class SearchName{
    private Scanner scanner = new Scanner(System.in);
    public void run(){
        System.out.println("请输入要查找的路径");
        File path = new File(scanner.nextLine());
        if(!path.exists()){
            System.out.println("不合法的路径");
            scanner.close();
            return;
        }

        System.out.println("请输入要查找的关键词");
        String target = scanner.nextLine();
        range(path, target);
        scanner.close();
    }

    public void range(File path, String target){
        File[] files = path.listFiles();
        if(files.length == 0){
            return;
        }
        for(int i = 0; i < files.length; i++){
            if(files[i].isFile()){
                deal(files[i], target);
            }
            else{
                range(files[i], target);
            }
        }
    }

    public void deal(File file, String target){
        String name = file.toString();
        if(name.contains(target)){
            System.out.printf("查找到目标（文件名匹配）：%s\n", name);
        }
        else{
            StringBuffer sb = new StringBuffer();

            try(InputStream in = new FileInputStream(file)){
                while(true){
                    int n = in.read();
                    if(n == -1){
                        break;
                    }
                    sb.append((char)n);
                }
            } 
            catch(IOException e){
                System.out.println(e);
            }

            if(sb.toString().contains(target)){
                System.out.printf("查找到目标（文件内容匹配）%s\n", name);
            }
        }
    }
}

class SearchToDelete{
    private Scanner scanner = new Scanner(System.in);
    public void run(){
        System.out.println("请输入要查找的路径");
        File path = new File(scanner.nextLine());
        if(!path.exists()){
            System.out.println("不合法的路径");
            scanner.close();
            return;
        }

        System.out.println("请输入要查找的文件名");
        String target = scanner.nextLine();
        range(path, target);

        scanner.close();
    }

    public void range(File path, String target){
        File[] files = path.listFiles();
        if(files.length == 0){
            return;
        }
        for(int i = 0; i < files.length; i++){
            if(files[i].isFile()){
                deal(files[i], target);
            }
            else{
                range(files[i], target);
            }
        }
    }

    public void deal(File file, String target){
        String name = file.getName();
        if(name.contains(target)){
            System.out.printf("查找到目标：%s\n，是否删除？[y/n]", name);
            System.out.println(name);
            char op = scanner.nextLine().charAt(0);
            if(op == 'y'){
                if(!file.exists()){
                    System.out.println("未知原因，文件不存在");
                    return;
                }
                file.delete();
                return;
            }
        }
    }
}