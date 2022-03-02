import java.util.Scanner;

public class driver {
    public static void main(String[] args){
        CreateArtList art = new CreateArtList();
        art.create();
        System.out.println("What is your name?");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Hangman game = new Hangman(name);
        game.play();
    }
}
