import java.util.Timer;
import java.util.TimerTask;

public class TestTimer{
    public static void main(String[] args){
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                System.out.println("timer waiting...");
            }
        }, 10);
        System.out.println("stop timer");
    }
}