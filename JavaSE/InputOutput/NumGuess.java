package JavaSE.InputOutput;

import java.util.Scanner;
import java.util.Random;

public class NumGuess{
    public static void main(String[] args){
        System.out.println("please guess a number between 1~100, input nothing to exit");
        Random rand = new Random();
        int answer = rand.nextInt(100);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int guess = sc.nextInt();
            if(guess < answer){
                System.out.println("smaller than the answer! Guess again?");
            }
            else if(guess > answer){
                System.out.println("bigger than the answer! Guess again?");
            }
            else{
                System.out.println("correct");
                break;
            }
        }
        sc.close();
    }
}