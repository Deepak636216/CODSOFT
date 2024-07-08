import java.util.Random;
import java.util.Scanner;

public class Task1 {
    static int generaterandom(int min,int max){
        Random rand = new Random();
        return rand.nextInt((max-min)+1)+min;
    }
    static boolean prompt(int random_number,int attemptsleft){
        Scanner s=new Scanner(System.in);
        int guess=s.nextInt();
        System.out.println("Enter the number: "+guess);
        if(random_number==guess){
            System.out.println("You guessed correctly!");
        }
        else if (random_number>guess)
        {
            System.out.println("It's too low!");
        }
        else{
            System.out.println("It's too high!");
        }
        System.out.println("Attempts remaining: " + (attemptsleft - 1));
        return false;
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        boolean playagain=true;
        int totalrounds=0;
        int totalscore=0;
        while(playagain){
            totalrounds++;
            int randomnumber=generaterandom(1,100);
            int maxattempts=5;
            boolean guessedCorrectly=false;

            System.out.println("Round "+totalrounds+" start!");
            for(int attempts=0;attempts<maxattempts;attempts++){
                if(prompt(randomnumber,maxattempts-attempts)){
                    guessedCorrectly=true;
                    totalscore+=(maxattempts-attempts);
                    break;
                }
            }
            if(!guessedCorrectly){
                System.out.println("Sorry, you've used all attempts! The correct number was: " + randomnumber);
            }
            System.out.println("Do you want to play again? (y/n)");
            playagain=s.next().equalsIgnoreCase("y");
        }
        System.out.println("Game over! You played "+totalrounds+ " Total Score: "+totalscore+" .");
    }
}
