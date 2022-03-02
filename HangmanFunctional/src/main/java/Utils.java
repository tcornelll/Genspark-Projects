import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {
    public static String getRandomWord() {
        List<String> list = new ArrayList<>();
        String word = "habitual";
        try {
            list = Files.readAllLines(Paths.get("src/main/resources/words.txt"));
            word = list.get(new Random().nextInt(list.size()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return word;
    }

    public static int getHighScore(String name){
        List<String> list = new ArrayList<String>();
        int highScore = 0;
        try {
            list = Files.readAllLines(Paths.get("src/main/resources/score_file.txt"));
            List<String> scoreList = list.stream().filter(line -> line.contains(name)).collect(Collectors.toList());
            if(scoreList.size() == 1){
                highScore = Integer.parseInt(scoreList.get(0).split(" ")[1]);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return highScore;
    }

    public static void saveHighScore(String name, int oldHighScore, int newHighScore){
        List<String> list = new ArrayList<String>();
        try {
            list = Files.readAllLines(Paths.get("src/main/resources/score_file.txt"));
            List<String> scoreList = list.stream().filter(line -> line.contains(name)).collect(Collectors.toList());
            if(scoreList.size() == 1){
                int userIndex = list.indexOf(String.format("%s %d", name, oldHighScore));
                list.set(userIndex, String.format("%s %d", name, newHighScore));
            }
            else {
                list.add(String.format("%s %d", name, newHighScore));
            }
            String fileOutput = list.stream()
                    .map(entry -> String.format("%s %d%n", entry.split(" ")[0], Integer.parseInt(entry.split(" ")[1])))
                    .reduce("", (last, next) -> last + next);
            FileWriter fw = new FileWriter("src/main/resources/score_file.txt", false);
            fw.write(fileOutput);
            fw.flush();
            fw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
