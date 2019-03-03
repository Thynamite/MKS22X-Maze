import java.io.*;
public class Driver{
    public static void main(String[]args){
      String filename = "data1.dat";
      String filename2 = "data2.dat";
      String filename3 = "data3.dat";
      try{
        Maze f;
        Maze f2;
        Maze f3;
        f = new Maze(filename);//true animates the maze.
        f2 = new Maze(filename2);
        f3 = new Maze(filename3);

        System.out.println(f);
        System.out.println(f2);
        System.out.println(f3);


        f.setAnimate(true);
        f2.setAnimate(true);
        f3.setAnimate(true);

        System.out.println(f.solve());
        System.out.println(f2.solve());
        System.out.println(f3.solve());


        System.out.println(f);
        System.out.println(f2);
        System.out.println(f3);
        
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }
    }
}
