import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberWithExceptions {
    static boolean isCorrect = false;
    static String name = "";
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static int number = rand.nextInt(20) + 1;
    static int numGuesses = 0;

    public static void getName(){
        System.out.println("Hello! What is your name?");
        try{
            name = sc.nextLine();
        }
        catch(Throwable e){
            throw e;
        }
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
                try {
                    handleCorrect();
                }
                catch(IllegalArgumentException e){
                    throw e;
                }
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
        if(choice != "y" && choice != "n"){
            throw new IllegalArgumentException (
                "Must input 'y' or 'n', " + name + ". Exiting program"
            );
        }
        if(choice.equals("y")){
            reset();
        }
    }


    public static void main(String[] args){
        if(name.equals("")){
            try {
                getName();
            }
            catch(Throwable e){
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("Well " + name + ", I am thinking of a number between 1 and 20" );
        while(!isCorrect){
            System.out.println("Take a guess");
            try {
                int guess = sc.nextInt();
                sc.nextLine();
                handleGuess(guess);
            }
            catch(InputMismatchException e) {
                System.out.println("Caught exception: Input mismatch exception (You must input an integer value)");
                break;
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
