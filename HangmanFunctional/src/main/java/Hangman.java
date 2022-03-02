import jdk.jshell.execution.Util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Hangman {
    private ArrayList<String> artList = new ArrayList<>();
    private Set<String> guessedLetters = new HashSet<>();
    private Set<String> missedLetters = new HashSet<>();
    private String name;
    private int score;
    private int highScore;
    private int numWrong;
    private String word;
    private boolean isStillPlaying = true;
    private Scanner sc = new Scanner(System.in);

    public Hangman(String name) {
        this.name = name;
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/hangmanArt.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.artList = (ArrayList<String>) ois.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.score = 0;
        this.numWrong = 0;
        this.highScore = Utils.getHighScore(name);
        this.word = Utils.getRandomWord();
    }



    private String displayGallows(int numWrong){
        return this.artList.get(numWrong);
    }

    private String getMissedLetters(){
        String status = "Missed Letters: ";
        String missedLettersString = this.missedLetters.stream().reduce("", (last, next) -> last + " " + next);
        status += missedLettersString;
        return status;
    }

    private String getWordString() {
        String wordString = word.chars().mapToObj(c -> (char) c).map(c -> c.toString())
                .map(c -> (this.guessedLetters.contains(c)) ? c : "_").reduce("", (last, next) -> last + next);
        return wordString;
    }

    private void wipeGame() {
        this.guessedLetters.clear();
        this.missedLetters.clear();
        this.numWrong = 0;
        this.word = Utils.getRandomWord();
    }

    private void requestReplay() {
        System.out.println("Do you want to play again? (y or n)");
        String choice = sc.nextLine();
        if(!choice.equals("y") && !choice.equals("n")){
            throw new IllegalArgumentException("You must input 'y' or 'n' ");
        }
        else if(choice.equals("y")){
            this.wipeGame();
        }
        else {
            Utils.saveHighScore(this.name, this.highScore, (this.score > this.highScore) ? this.score : this.highScore);
            this.isStillPlaying = false;
        }
    }

    private void handleCorrect(String guess) throws IOException{
        boolean wordIsComplete = word.chars().mapToObj(c -> (char) c).map(c -> c.toString())
                .map(c -> this.guessedLetters.contains(c)).reduce(true, (last, next) -> last && next);
        if(wordIsComplete){
            ++this.score;
            System.out.println("Good job! You guessed the number correctly!");
            this.requestReplay();
        }
    }

    private void handleIncorrect(String guess) throws IOException{
        ++this.numWrong;
        this.missedLetters.add(guess);
        if(this.missedLetters.size() == 6){
            System.out.println(String.format("Too bad! You couldn't guess the word! It was %s", this.word));
            this.requestReplay();
        }
    }

    private void handleInput(String guess) throws IOException {
        if(this.guessedLetters.add(guess)){
            if(this.word.contains(guess)){
                this.handleCorrect(guess);
            }
            else {
                this.handleIncorrect(guess);
            }
        }
        else {
            System.out.println("You already guessed this letter, guess again");
        }
    }

    public void play() {
        while(this.isStillPlaying){
            System.out.println(this.displayGallows(this.numWrong));
            System.out.println(this.getMissedLetters());
            System.out.println(this.getWordString());
            System.out.println("Guess a letter");
            try{
                String guess = this.sc.nextLine();
                if(guess.length() == 1 && (Character.isLetter(guess.charAt(0)))){
                    this.handleInput(guess);
                }
                else {
                    throw new IllegalArgumentException("You must input a single alphabetical character");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
