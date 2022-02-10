import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DragonCave {
    static Random rand = new Random();
    static Scanner sc  = new Scanner(System.in);
    static boolean isEaten = false;
    private static int correctChoice = rand.nextInt(2) + 1;

    public static String printIntro() {
        return("""
                You are in a land full of dragons. In front of you,
                you see two caves. In one cave, the dragon is friendly
                and will share his treasure with you. The other dragon
                is greedy and hungry and will eat you on sight
                Which cave will you go into? (1 or 2)
                """);
    }

    public static String handleEaten() {
        return("""
                You approach the cave...
                It is dark and spooky...
                A large dragon jumps out in front of you! He opens his jaws and...
                Gobbles you down in one bite!""");
    }

    public static String handleShared() {
        return("""
                You approach the cave...
                It is dark and spooky...
                A large dragon jumps out in front of you! He opens his jaws and...
                Tells you that he is feeling generous...
                And will share his gold with you today!
                He gives you some of his gold
                And you continue on your journey""");
    }



    public static void handleChoice(int choice) {
        System.out.println(choice);
        if(choice == correctChoice){
            System.out.println(handleShared());
            correctChoice = rand.nextInt(2) + 1;
        }
        else {
            System.out.println(handleEaten());
            isEaten = true;
        }
    }

    public static void main(String[] args){
        while(!isEaten){
            System.out.println(printIntro());
            try {
                int choice = sc.nextInt();
                handleChoice(choice);
            }
            catch(InputMismatchException e) {
                System.out.println("Caught Exception: Input Mismatch Exception (You must input an integer value)");
                return;
            }

        }
    }
}
