import java.util.Random;
import java.util.Scanner;


public class DragonCave {

    static Random rand = new Random();
    static Scanner sc  = new Scanner(System.in);
    static boolean isEaten = false;
    private static int correctChoice = rand.nextInt(2) + 1;

    public static void printIntro() {
        System.out.print("You are in a land full of dragons. In front of you,\n" +
                "you see two caves. In one cave, the dragon is friendly\n" +
                "and will share his treasure with you. The other dragon\n" +
                "is greedy and hungry and will eat you on sight\n" +
                "Which cave will you go into? (1 or 2)\n");
    }

    public static void handleEaten() {
        System.out.print("You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Gobbles you down in one bite!");
    }

    public static void handleShared() {
        System.out.print("You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Tells you that he is feeling generous...\n" +
                "And will share his gold with you today!\n" +
                "He gives you some of his gold\n" +
                "And you continue on your journey\n");
        correctChoice = rand.nextInt(2) + 1;
    }



    public static void handleChoice(int choice) {
        System.out.println(choice);
        double rand = Math.random();
        if(choice == correctChoice){
            handleShared();
        }
        else {
            handleEaten();
            isEaten = true;
        }
    }

    public static void main(String[] args){
        while(!isEaten){
            printIntro();
            int choice = sc.nextInt();
            handleChoice(choice);
        }
    }
}
