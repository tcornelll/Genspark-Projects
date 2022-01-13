import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    static boolean isCorrect = false;
    static String name = "";
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static int number = rand.nextInt(20) + 1;
    static int numGuesses = 0;

    public static void getName(){
        System.out.println("Hello! What is your name?");
        name = sc.nextLine();
    }

    public static void handleGuess(int guess){
        ++numGuesses;
        if(!(guess > 0 && guess < 21)){
            System.out.println("Remember, I'm thinking of a number between 1 and 20!");
        }
        else {
            if(guess < number){
                System.out.println("Your guess is too low");
            }
            else if(guess > number){
                System.out.println("Your guess is too high");
            }
            else {
                isCorrect = true;
                handleCorrect();
            }
        }
    }

    public static void reset(){
        number = rand.nextInt(21);
        isCorrect = false;
        numGuesses = 0;
    }

    public static void handleCorrect() {
        System.out.println("Good job, " + name + "! You guessed my number in " + numGuesses + " guesses!");
        System.out.println("Would you like to play again? (y or n)");
        String choice = sc.nextLine();
        if(choice.equals("y")){
            reset();
        }
    }


    public static void main(String[] args){
        if(name.equals("")){
            getName();
        }
        System.out.println("Well " + name + ", I am thinking of a number between 1 and 20" );
        while(!isCorrect){
            System.out.println("Take a guess");
            int guess = sc.nextInt();
            sc.nextLine();
            handleGuess(guess);
        }
    }
}
