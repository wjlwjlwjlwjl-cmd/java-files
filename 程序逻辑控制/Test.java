// public class Test{
//     public static void main(String[] args){
//         int n = 3;
//         int ret = 0;
//         for(int i = 1; i <= n; i++)
//         {
//             int mid = 1;
//             for(int j = 1; j <= i; j++)
//             {
//                 mid *= j;
//             }
//             ret += mid;
//         }
//         System.out.println(ret);
//         System.out.print(ret);
//         System.out.printf("the result is %d\n", ret);
//         //switch语句：
//         //1、多个case后的常量值不得重复
//         //2、switch括号中的类型只能是：
//         //（1）除了long以外的整型类型
//         //（2）引用类型：String，枚举类型
//     }
// }

// import java.util.Scanner;

// public class Test{
//     public static void main(String[] args){
//         Scanner sc = new Scanner(System.in);
//         System.out.println("请输入您的姓名:");
//         String name = sc.nextLine();
//         System.out.println("请输入您的年龄:");
//         int age = sc.nextInt();
//         System.out.println("请输入您的工资：");
//         float salary = sc.nextFloat();

//         System.out.println("您的相关信息是：");
//         System.out.printf("姓名：%s 年龄：%d 工资：%.2f", name, age, salary);

//         sc.close();
//     }
// }

// import java.util.Scanner;
// public class Test{
//     public static void main(String[] args){
//         int sub = 0;
//         int num = 0;
//         int ret;
//         Scanner sc = new Scanner(System.in);
//         while(sc.hasNextInt())
//         {
//             int val = sc.nextInt();
//             sub += val;
//             num++;
//         }
//         ret = sub / num;
//         System.out.println(ret);
//     }
// }

//随机数的生成
import java.util.Random;
import java.util.Scanner;
public class Test{
    public static void main(String[] args)
    {
        Random rd = new Random();//默认种子是系统时间
        Scanner sc = new Scanner(System.in);
        int target = rd.nextInt(100);
        while(true)
        {
            System.out.println("请输入您想要猜的数字");
            int num = sc.nextInt();
            if(num < target){
                System.out.println("猜小了");
            }
            else if(num > target){
                System.out.println("猜大了");
            }
            else {
                System.out.println("猜对了");
                break;
            }
        }
        sc.close();
    }
}
