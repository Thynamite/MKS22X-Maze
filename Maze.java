import java.util.*;
import java.io.*;
public class Maze{

    private char[][]maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public Maze(String filename) throws FileNotFoundException{
      String mazey = "";
      File f = new File(filename);
      int rows = 0;
      int cols = 0;
      animate = false;
      Scanner s = new Scanner(f);

      while(s.hasNextLine()){
        mazey+= s.nextLine() + '\n';
        rows++;
        //System.out.println(mazey);
      }
      cols = mazey.length() / rows;

      if (count(mazey,'S') != 1 || count(mazey,'E') != 1) {
        throw new IllegalStateException("missing or extra start and exit");
      }
      maze = new char[rows][cols];
      int index = 0;
      for (int x = 0; x < rows; x++) {
        for (int y = 0; y < cols; y++) {
          maze[x][y] = mazey.charAt(index);
          index++;
        }
      }
    }

    private int count(String mazes, char E){
      int total = 0;
      for (int x = 0; x < mazes.length(); x++) {
        if (mazes.charAt(x) == E) {
          total++;
        }
      }
      return total;
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }





   /*Return the string that represents the maze.
     It should look like the text file with some characters replaced.
    */
    public String toString(){
      String mazey = "";
      for (int x = 0; x < maze.length; x++) {
        for (int y = 0; y < maze[x].length; y++){

            mazey += maze[x][y];

        }
      }
      return mazey;
    }


    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
            //find the location of the S.
      int rows = 0;
      int cols = 0;
      for (int x = 0; x < maze.length; x++){
        for (int y = 0; y < maze[x].length; y++) {
          if (maze[x][y] == 'S') {
            rows = x;
            cols = y;
          }
        }
      }

            //erase the S
      maze[rows][cols] = '@'; //might need to put this into recursion

            //and start solving at the location of the s.
            //return solve(???,???);

      return solve(rows,cols);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */

    private int solve(int row, int col){ //you can add more parameters since this is private

        //automatic animation! You are welcome.
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(50);
        }

        //COMPLETE SOLVE
        int[] moves = {1,0,0,1,-1,0,0,-1};
        int tots = -1;
        for (int x = 0; x < moves.length; x+=2){
          if(maze[row+moves[x]][col+moves[x+1]] == 'E'){
            tots = 0;
            for (int r = 0; r < maze.length; r++) {
              for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == '@') {
                  tots++;
                }
              }
            }
            return tots;
          }
          if (place(row+moves[x],col+moves[x+1])){
            tots = solve(row+moves[x],col+moves[x+1]);
            if (tots != -1) {
              return tots++;
            }
            else {
              remove(row+moves[x],col+moves[x+1]);
            }
          }

        }
        return tots; //so it compiles
    }


    private boolean place(int row, int col){
      if (maze[row][col] != ' '){
        return false;
      }
      maze[row][col] = '@';
      return true;
    }
    private boolean remove(int row, int col){
      if (maze[row][col] != '@' ){
        return false;
      }
      maze[row][col] = '.';
      return true;
    }
    private boolean check(int row, int col){
      char character = maze[row][col];

      if (character == '#' || character == '.') {
        return false;
      }
      //might need more later
      return true;
    }
}
