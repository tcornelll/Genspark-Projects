import java.util.*;

public class Hangman {

    private static Scanner sc = new Scanner(System.in);
    private static boolean stillPlaying = true;
    private static ArrayList<Character> missedLetters = new ArrayList<>();
    private static ArrayList<Character> guessedLetters = new ArrayList<>();
    private static String word = "mitochondria";
    static StringBuilder hangMan = new StringBuilder("""
                +----+
                     |
                     |
                     |
                     |
                  =======+
                
            """);
    private static final int headIndex = 15;
    private static final int[] leftArmIndices = {24, 25};
    private static final int[] rightArmIndices = {27, 28};
    private static final int[] bodyIndices = {26, 37};
    private static final int leftLegIndex = 47;
    private static final int rightLegIndex = 49;

    public ArrayList<Character> getMissedLetters(){
        return missedLetters;
    }

    public ArrayList<Character> getGuessedLetters() {
        return guessedLetters;
    }
    public String getWord() {
        return word;
    }

    public void setMissedLetters(ArrayList<Character> letters){
        missedLetters = letters;
    }

    public void setGuessedLetters(ArrayList<Character> letters){
        guessedLetters = letters;
    }

    public void setWord(String newWord){
        word = newWord;
    }

    private static void wipeBody() {
        //erasing the left arm, right arm, and body
        for(int i = 0; i < 2; i++){
            hangMan.replace(leftArmIndices[i], leftArmIndices[i]+1, " ");
            hangMan.replace(rightArmIndices[i], rightArmIndices[i] + 1, " ");
            hangMan.replace(bodyIndices[i], bodyIndices[i] + 1, " ");
        }
        //erasing the head
        hangMan.replace(headIndex, headIndex + 1, " ");
        //erasing the left and right leg
        hangMan.replace(leftLegIndex, leftLegIndex + 1, " ");
        hangMan.replace(rightLegIndex, rightLegIndex + 1, " ");
    }

    private static void addHead() {
        hangMan.replace(headIndex, headIndex + 1, "O");
    }

    private static void addBody() {
        for(int i = 0; i < 2; i++){
            hangMan.replace(bodyIndices[i], bodyIndices[i] + 1, "|");
        }
    }

    private static void addLeftArm() {
        for(int i = 0; i < 2; i++){
            hangMan.replace(leftArmIndices[i], leftArmIndices[i] + 1, "-");
        }
    }

    private static void addRightArm() {
        for(int i = 0; i < 2; i++){
            hangMan.replace(rightArmIndices[i], rightArmIndices[i] + 1, "-");
        }
    }

    private static void addLeftLeg() {
        hangMan.replace(leftLegIndex, leftLegIndex + 1, "/");
    }

    private static void addRightLeg() {
        hangMan.replace(rightLegIndex, rightLegIndex + 1, "\\");
    }

    private static void displayMissedLetters() {
        System.out.print("Missed Letters: ");
        for(char letter : missedLetters){
            System.out.print(letter + " ");
        }
        System.out.print("\n\n");
    }

    private static void displayWord() {
        for(int i = 0; i < word.length(); i++){
            if(guessedLetters.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
            }
            else{
                System.out.print("_");
            }
        }
        System.out.print("\n\n");
    }

    private static void display() {
        System.out.print(hangMan);
        displayMissedLetters();
        displayWord();
    }

    private static void getNewWord() {
        word = "harassment";
    }

    private static void handleMissed() {
        int numMissed = missedLetters.size();
        switch(numMissed){
            case(1) -> addHead();
            case(2) -> addBody();
            case(3) -> addLeftArm();
            case(4) -> addRightArm();
            case(5) -> addLeftLeg();
            case(6) -> {
                addRightLeg();
                System.out.println("Oof! You couldn't guess the word correctly! The word was " + word + "!");
                System.out.println("Do you want to play again? (yes or no)");
                String response = "";
                while(!response.equals("yes") && !response.equals("no")) {
                    try {
                        response = sc.nextLine();
                        if (response.equals("yes")) {
                            missedLetters.clear();
                            guessedLetters.clear();
                            wipeBody();
                            getNewWord();
                        } else if(response.equals("no")) {
                            stillPlaying = false;
                        }
                        else {
                            throw new IllegalArgumentException("Invalid Response. Must Reply either yes or no");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private static void handleCorrect() {
        char[] wordCharArray = word.toCharArray();
        ArrayList<Character> wordArrList = new ArrayList<>();
        for(char c : wordCharArray){
            wordArrList.add(c);
        }
        if(guessedLetters.containsAll(wordArrList)){
            System.out.println("Yes! The secret word is \"" + word + "\"! You have won!");
            System.out.println("Would you like to play again? (yes or no)");
            String response = "";
            while(!response.equals("yes") && !response.equals("no")) {
                try {
                    response = sc.nextLine();
                    if (response.equals("yes")) {
                        missedLetters.clear();
                        guessedLetters.clear();
                        wipeBody();
                        getNewWord();
                    } else if(response.equals("no")) {
                        stillPlaying = false;
                    }
                    else {
                        throw new IllegalArgumentException("Invalid Response. Must Reply either yes or no");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void handleInput(char c) {
        while(guessedLetters.contains(c)){
            System.out.println("You have already guessed that letter. Guess again.");
            try {
                c = sc.next().charAt(0);
                sc.nextLine();
                if(!Character.isLetter(c)){
                    throw new IllegalArgumentException("You must input a single character");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        guessedLetters.add(c);
        if(!word.contains(String.valueOf(c))){
            missedLetters.add(c);
            handleMissed();
        }
        else {
            handleCorrect();
        }
    }

    public static void main(String[] args){
        System.out.println("HANGMAN");
        while(stillPlaying){
            display();
            System.out.println("Guess a letter.");
            try{
               char guessedChar = sc.next().charAt(0);
               sc.nextLine();
               if(!Character.isLetter(guessedChar)){
                   throw new IllegalArgumentException("You must input a single character");
               }
               handleInput(guessedChar);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}