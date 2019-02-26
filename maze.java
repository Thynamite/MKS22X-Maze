import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class maze{



  public static void main(String[] args) {
    try {
      File maze = new File(args[0]);

    }
    catch (FileNotFoundException e){

    }
  }

  public static String readFileMaze(File f) throws FileNotFoundException {
    String mazes = "";
    Scanner s = new Scanner(f);


    

    return mazes;
  }
}
