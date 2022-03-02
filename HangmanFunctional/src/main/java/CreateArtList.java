import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateArtList {
    private static ArrayList<String> list = new ArrayList<>();

    public static void create(){
        list.add("""
                +----+
                     |
                     |
                     |
                     |
                  =======+
                
            """);
        list.add("""
                +----+
                O    |
                     |
                     |
                     |
                  =======+
                
            """);
        list.add("""
                +----+
                O    |
                |    |
                |    |
                     |
                  =======+
                
            """);
        list.add("""
                +----+
                O    |
                |--  |
                |    |
                     |
                  =======+
                
            """);
        list.add("""
                +----+
                O    |
              --|--  |
                |    |
                     |
                  =======+
                
            """);
        list.add("""
                +----+
                O    |
              --|--  |
                |    |
               /     |
                  =======+
                
            """);
        list.add("""
                +----+
                O    |
              --|--  |
                |    |
               / \\  |
                  =======+
                
            """);

        try{
            FileOutputStream fos = new FileOutputStream("src/main/resources/hangmanArt.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
